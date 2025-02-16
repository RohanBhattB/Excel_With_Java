package org.example;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;

import java.io.FileInputStream;

public class Main {
    public static void main(String args[])
    {
        String excelPath="D:\\Kitsw\\Project\\ExcelWithJava\\src\\main\\resources\\Responce.xlsx";

        try (FileInputStream inputStream = new FileInputStream(excelPath);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows=sheet.getLastRowNum();

            //Reading The Excel sheet
            for(int r=1;r<=rows;r++)
            {
                XSSFRow row=sheet.getRow(r);
                if(row==null)
                    continue;
                String name = getCellValue(row.getCell(0));
                String rollno = getCellValue(row.getCell(1));
                String registerd = getCellValue(row.getCell(2));


                System.out.println("Row "+r+": "+name+"         "+rollno+"          "+registerd);

            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private static String getCellValue(XSSFCell cell) {
        if(cell==null)
            return "";
        return cell.getStringCellValue().trim();
    }
}
