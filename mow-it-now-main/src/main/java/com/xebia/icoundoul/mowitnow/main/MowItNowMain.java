package com.xebia.icoundoul.mowitnow.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.xebia.icoundoul.mowitnow.config.utils.MowItNowConfig;
import com.xebia.icoundoul.mowitnow.config.utils.MowItNowPropertiesUtils;
import com.xebia.icoundoul.mowitnow.services.IInstructionService;

/**
 * Execute this to mow with instructions coming from a sample file.
 */
@Component
public class MowItNowMain {

	@Inject
	private MowItNowPropertiesUtils mowItNowPropertiesUtils;

	@Inject
	private IInstructionService instructionsScannerService;

	public static void main(String[] args) throws FileNotFoundException {

		ApplicationContext context = null;

		try {
			context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

			MowItNowMain mowFromFileMain = context.getBean(MowItNowMain.class);
			String filename = mowFromFileMain.getFileName();
			InputStream instructionsFile = new FileInputStream(filename);

			// Process instrucitions
			mowFromFileMain.processStream(instructionsFile, System.out);

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	private String getFileName() {
		return mowItNowPropertiesUtils.getValueByKey(MowItNowConfig.INSTRUCTION_FILE_NAME);
	}

	private void processStream(InputStream in, PrintStream out) {
		instructionsScannerService.procesInstructions(in, out);

	}

}
