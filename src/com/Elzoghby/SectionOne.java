package com.Elzoghby;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class SectionOne {

	public double getArresteesIn2018() throws Exception, IOException {
		double NumArrestees = 0;
		File f = new File("Arrest_Data_from_2010_to_Present.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet(0);
		int row = s.getRows();
		int col = s.getColumns();

		List<String> Date = new ArrayList<>();
		List<Integer> Reporting = new ArrayList<>();

		for (int i = 1; i < row; i++) {
			Cell c = s.getCell(1, i);
			Date.add(c.getContents().toString());
			c = s.getCell(5, i);
			Reporting.add(Integer.parseInt(c.getContents().toString()));
		}
		// System.out.println(Date);
		// System.out.println(Reporting);

		for (int i = 0; i < Reporting.size(); i++) {
			if (Date.get(i).contains("2018")) {
				NumArrestees += Reporting.get(i);
			}
		}

		return NumArrestees;
	}

	public double getArresteesByArea() throws Exception, IOException {
		double NumArrestees = 0;
		File f = new File("Arrest_Data_from_2010_to_Present.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet(0);
		int row = s.getRows();
		int col = s.getColumns();

		List<String> Area = new ArrayList<>();
		List<Integer> Reporting = new ArrayList<>();

		for (int i = 1; i < row; i++) {
			Cell c = s.getCell(4, i);
			Area.add(c.getContents().toString());
			c = s.getCell(5, i);
			Reporting.add(Integer.parseInt(c.getContents().toString()));
		}
		// get The Unique Areas
		List<String> UniqueArea = new ArrayList<>();
		List<Integer> NumOfEachArea = new ArrayList<>();
		for (int i = 0; i < Area.size(); i++) {
			if (i == 0) {
				UniqueArea.add(Area.get(i));
			} else if (!UniqueArea.contains(Area.get(i))) {
				UniqueArea.add(Area.get(i));
			}
		}
		System.out.println(UniqueArea);
		// get The Number Of Arrestees in Each Area
		for (int i = 0; i < UniqueArea.size(); i++) {
			int help = 0;
			for (int j = 0; j < Reporting.size(); j++) {
				if (UniqueArea.get(i).equals(Area.get(j))) {
					help += Reporting.get(j);
				}
			}
			NumOfEachArea.add(help);
		}
		System.out.println(NumOfEachArea);
		// Get The Highest Number Of Arrestees
		int help = 0;
		for (int i = 0; i < NumOfEachArea.size(); i++) {
			if (NumOfEachArea.get(i) > help) {
				help = NumOfEachArea.get(i);
			}
		}
		return help;
	}

	public double getQuantile() throws Exception, IOException {
		File f = new File("Arrest_Data_from_2010_to_Present.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet s = wb.getSheet(0);
		int row = s.getRows();
		int col = s.getColumns();

		List<String> Date = new ArrayList<>();
		List<Integer> Age = new ArrayList<>();
		List<String> ChargeGroup = new ArrayList<>();

		for (int i = 1; i < row; i++) {
			Cell c = s.getCell(1, i);
			Date.add(c.getContents().toString());
			c = s.getCell(6, i);
			Age.add(Integer.parseInt(c.getContents().toString()));
			c = s.getCell(10, i);
			if (c.getContents().toString().isEmpty()) {
				ChargeGroup.add("Mahmoud");
			}else {
				ChargeGroup.add(c.getContents().toString());
			}
		}
		//System.out.println(Date);
		//System.out.println(Age);
		//System.out.println(ChargeGroup);
		List<Integer> Age2018 = new ArrayList<>();
		List<String> ChargeGroup2018 = new ArrayList<>();
		for (int i = 0; i < Date.size(); i++) {
			if (Date.get(i).contains("2018")) {
				Age2018.add(Age.get(i));
				ChargeGroup2018.add(ChargeGroup.get(i));
			}
		}
		//System.out.println(Age2018);
		List<Integer> Age2018_2 = new ArrayList<>();
		List<String> ChargeGroup2018_2 = new ArrayList<>();
		for (int i = 0; i < ChargeGroup2018.size(); i++) {
			if (!ChargeGroup2018.get(i).equals("Pre-Delinquency") && !ChargeGroup2018.get(i).equals("Mahmoud")
					&& !ChargeGroup2018.get(i).equals("Non-Criminal Detention")) {
				Age2018_2.add(Age2018.get(i));
				ChargeGroup2018_2.add(ChargeGroup2018.get(i));
			}
		}
		//Get The Total For each Charge Group
		List<String> ChargeGroup2018_3 = new ArrayList<>();
		for (int i = 0; i < ChargeGroup2018_2.size(); i++) {
			if (i == 0) {
				ChargeGroup2018_3.add(ChargeGroup2018_2.get(i));
			} else if (!ChargeGroup2018_3.contains(ChargeGroup2018_2.get(i))) {
				ChargeGroup2018_3.add(ChargeGroup2018_2.get(i));
			}
		}
		System.out.println(ChargeGroup2018_3);
		List<Integer> Age2018_3 = new ArrayList<>();
		for (int i = 0 ; i < ChargeGroup2018_3.size() ; i++) {
			int help = 0;
			for(int j = 0 ; j < Age2018_2.size() ; j++) {
				if (ChargeGroup2018_2.get(i).equals(ChargeGroup2018_2.get(j))) {
					help += Age2018_2.get(i);
				}
			}
			Age2018_3.add(help);
		}
		//System.out.println(Age2018_3);
		double mean = 0 , sum = 0;
		for (int i : Age2018_3) {
			sum += i;
		}
		mean = sum / Age2018_3.size();
		// calculate Standard Deviation
		List<Double>X_Xi = new ArrayList<>();
		double Sum = 0;
		for (int i = 0 ; i < Age2018_3.size() ; i++) {
			X_Xi.add((Age2018_3.get(i)-mean) * (Age2018_3.get(i)-mean));
			Sum += X_Xi.get(i);
		}
		double standard = Math.sqrt(Sum / Age2018_3.size());
		System.out.println(standard);
		return standard;
		/*int[] A2018_2 = new int[Age2018_2.size()];
		for (int i = 0 ; i < Age2018_2.size() ; i++) {
			A2018_2[i] = Age2018_2.get(i);
		}
		Arrays.sort(A2018_2);
		Age2018_2.clear();
		for (int i = 0 ; i < A2018_2.length ; i++) {
			 Age2018_2.add(A2018_2[i]);
		}
		System.out.println(A2018_2.length);
		return (.95 * (Age2018_2.size() + 1));*/
	}

}
