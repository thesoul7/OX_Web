package com.getpath;

import java.io.File;

import org.openqa.selenium.WebDriver;

public class GetPathOfTheFile {
	public static String fileName = "Book1";
			
public static void main(String[] args) {
	System.out.println(getPathOfFile(fileName));
}
public static String getPathOfFile(String fileName) {
	String path = null;
	try {
		File file = new File(fileName+".xlsx");
		path = file.getAbsolutePath();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return path;
}
}
