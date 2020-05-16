package com.arc.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Rough {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Properties or=new Properties();
		Properties config=new Properties();
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\or.properties");
		or.load(fis);
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
		config.load(fis);
		System.out.print(or.getProperty("username_xpath"));
		System.out.print(config.getProperty("browser"));

	}

}
