package com.xebia.icoundoul.mowitnow.main;

import java.io.File;
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

	public static void main(String[] args) throws Exception {

		ApplicationContext context = null;
		String filename = null;

		try {
			context = new ClassPathXmlApplicationContext("classpath:spring-context.xml");

			MowItNowMain main = context.getBean(MowItNowMain.class);
			filename = main.getFileName();
			ClassLoader classLoader = main.getClass().getClassLoader();
			File file = new File(classLoader.getResource(filename).getFile());
			InputStream instructionsFile = new FileInputStream(file);

			// Process instructions
			main.processInstructions(instructionsFile, System.out);

		} catch (FileNotFoundException e) {
			throw new Exception("\n\nLe fichier d'insctructions " + filename + " n'exste pas. Veuillez vérifier son emplacement\n", e);
		} catch (Exception e) {
			throw e;
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	private String getFileName() throws Exception {
		return mowItNowPropertiesUtils.getValueByKey(MowItNowConfig.INSTRUCTION_FILE_NAME);
	}

	private void processInstructions(InputStream in, PrintStream out) {
		instructionsScannerService.procesInstructions(in, out);

	}

}
