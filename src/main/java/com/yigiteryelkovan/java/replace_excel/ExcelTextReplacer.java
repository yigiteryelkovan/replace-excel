package com.yigiteryelkovan.java.replace_excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JLabel;

import com.aspose.cells.ReplaceOptions;
import com.aspose.cells.Workbook;

public class ExcelTextReplacer {
	File excelFile;
	File textFile;
	JLabel resultTextField;

	public ExcelTextReplacer(File excel, File text, JLabel resultTextField) {
		super();
		this.excelFile = excel;
		this.textFile = text;
		this.resultTextField = resultTextField;
	}

	public void calistir() {
		HashMap<String, String> donusumList = new HashMap<String, String>();

		try {
			Scanner sc = new Scanner(textFile);
			 
		    while (sc.hasNextLine()) {
		    	String[] arr = sc.nextLine().split("\\|");
		    	donusumList.put(arr[0], arr[1]);
		    }
		    sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			resultTextField.setText(e.getLocalizedMessage());
		}
		

		try {
			Workbook workbook = new Workbook(excelFile.getAbsolutePath());
			ReplaceOptions replace = new ReplaceOptions();
			// Set case sensitivity and text matching options
			replace.setCaseSensitive(false);
			replace.setMatchEntireCellContents(false);
			for (String i : donusumList.keySet()) {
				  workbook.replace(i,donusumList.get(i), replace);
				}
			String newFile = excelFile.getAbsolutePath();
			newFile= newFile.replace(".xlsx", "_updated.xlsx");
			workbook.save(newFile); 
			
			resultTextField.setText("Replaced Succesfully!");
			
		} catch (Exception e) {
			e.printStackTrace();
			resultTextField.setText(e.getLocalizedMessage());
		}
		
	}
}
