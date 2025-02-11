package com.qa.sound.utility;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ExcelUtil {

    public String File_Path = "";
    public Workbook workbook;
    public Sheet sheet;

    public Object[][] getSheetData(String sheetName) {

        System.out.println("Reading the data from - " + sheetName);
        Object[][] data = null;
        try {
            FileInputStream file = new FileInputStream(File_Path);
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet(sheetName);
            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
            for (int i = 0; i<sheet.getLastRowNum();i++) {
                for (int j = 0; j<sheet.getRow(0).getLastCellNum();j++) {
                    data[i][j] = sheet.getRow(i+1).getCell(j).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


}
