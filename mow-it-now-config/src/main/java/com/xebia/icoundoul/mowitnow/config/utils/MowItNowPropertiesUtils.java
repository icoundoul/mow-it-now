package com.xebia.icoundoul.mowitnow.config.utils;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class MowItNowPropertiesUtils {

	@Resource(name = "mow-it-now-properties")
	private Properties mowItNowProperties;

	public String getValueByKey(String key) {
		String value = mowItNowProperties.getProperty(key);
		return value;
	}
}