package com.lawencon.assetsystem.util;

import java.util.Random;

public class GeneratorID {

	public static String generateID(int length) {
		final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		final StringBuilder sb = new StringBuilder();
	    final Random random = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(characters.charAt(random.nextInt(characters
	                .length())));
	    }

	    return sb.toString().toUpperCase();
	}
}
