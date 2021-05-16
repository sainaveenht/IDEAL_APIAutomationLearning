package com.qa.codedog.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	
	public static String firstname() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return ("John"+generatedString);
	}

	public static String lastname() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	
	public static String secondlastname() {
		String generatedString = RandomStringUtils.randomNumeric(5);
		return (generatedString);
	}
	
}
