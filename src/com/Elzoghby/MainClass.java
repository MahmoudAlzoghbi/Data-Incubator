package com.Elzoghby;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		
		SectionOne s = new SectionOne();
		//double x = s.getArresteesIn2018();
		//double y = s.getArresteesByArea();
		double y = s.getQuantile();
		System.out.println(y);

	}

}
