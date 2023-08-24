package com.lawencon.assetsystem.util;

import java.util.Scanner;

public class ScannerUtil {
	
	public static int scannerInteger(String value, int limit) {
		final Scanner scan = new Scanner(System.in);
		System.out.print(value);
		final int input;
		
		try {
			input = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid Input! Please try again!");
			return scannerInteger(value, limit);
		}
		
		if (limit==0 && input > 0) {
			return input;
		} else if (input>limit) {
			System.out.println("Inputan Melebihi Batas!");
			return scannerInteger(value, limit);
		} else if (input<1) {
			System.out.println("Inputan tidak boleh dibawah 1");
			return scannerInteger(value, limit);
		}
		
		return input;
	}
	
	public static long scannerLong(String value) {
		final Scanner scan = new Scanner(System.in);
		System.out.print(value);
		final long input;
		
		try {
			input = Long.parseLong(scan.nextLine());
		} catch (Exception e) {
			System.out.println("Invalid Input! Please try again!");
			return scannerLong(value);
		}
		
		return input;
	}
	
	public static String scannerString(String value, int filter) {
		final Scanner scan = new Scanner(System.in);
		System.out.print(value);
		String input = null;
		final char[] inputArray;
		
		try {
			input = scan.nextLine().trim();
		} catch (Exception e) {
			e.printStackTrace();
			return scannerString(value, filter);
		}
		
		if (input.isEmpty() || input.equals("")) {
			System.out.println("Don't Leave it Blank");
			return scannerString(value, filter);
		} else if (filter == 0) {
			inputArray = input.toCharArray();
			for(int i=0; i<inputArray.length; i++) {
				if (Character.isDigit(inputArray[i])) {
					System.out.println("Don't input digit");
					return scannerString(value, filter);
				}
			}
		}
		
		return input;
	}
	
	public static byte scannerByte(String value, byte limit) {
		final Scanner scan = new Scanner(System.in);
		System.out.print(value);
		byte input = 0;
		
		try {
			input = Byte.parseByte(scan.nextLine());
		} catch (Exception e) {
			e.printStackTrace();
			return scannerByte(value, limit);
		}
		
		if (limit==0 && input > 0) {
			return input;
		} else if (input>limit) {
			System.out.println("Inputan Melebihi Batas!");
			return scannerByte(value, limit);
		} else if (input<1) {
			System.out.println("Inputan tidak boleh dibawah 1");
			return scannerByte(value, limit);
		}
		
		return input;
	}
	
	public static double scannerDouble(String value) {
		final Scanner scan = new Scanner(System.in);
		System.out.print(value);
		double input = 0;
		
		try {
			input = Double.parseDouble(scan.nextLine());
		} catch (Exception e) {
			e.printStackTrace();
			return scannerDouble(value);
		}
		
		if (input<0) {
			System.out.println("Inputan tidak boleh dibawah 0");
			return scannerDouble(value);
		}
		
		return input;
	}
	

}

