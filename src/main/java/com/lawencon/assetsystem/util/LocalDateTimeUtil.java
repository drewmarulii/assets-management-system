package com.lawencon.assetsystem.util;

import static com.lawencon.assetsystem.util.ScannerUtil.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
	
	public static LocalDateTime setDueDate() {
	    String str = scannerString("Enter Due Date [yyyy-MM-dd HH:mm:ss]: ", 1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		 
		return dateTime;
	}
	
	public static LocalDateTime formatDate(String date) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
		
		return dateTime;
	}
}
