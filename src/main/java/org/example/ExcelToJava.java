package org.example;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.*;


public class ExcelToJava
{
    public static void main(String args[])
    {
        String excelPath="D:\\Kitsw\\Project\\ExcelWithJava\\src\\main\\resources\\Responce.xlsx";

        //connection to JDBC
        String jdbcURL="jdbc:oracle:thin:@localhost:1521:XE";
        String user="SYSTEM";
        String password="rohan";



        //to read the excel file
        try
        {
            FileInputStream inputStream=new FileInputStream(excelPath);

            //get the workbook
            XSSFWorkbook workbook=new XSSFWorkbook(inputStream);

            //get the starting sheet in excel
            XSSFSheet sheet=workbook.getSheetAt(0);

            //Getting the total no of rows so we can itterate to print the rows
            int rows=sheet.getLastRowNum();


            String sql ="INSERT INTO STUDENTS(NAME,ROLLNO,REGISTERED)VALUES(?,?,?)";
            Connection conn = DriverManager.getConnection(jdbcURL,user,password);
            PreparedStatement stmt=conn.prepareStatement(sql);

            int rowCount=0;
            for(int r=1;r<=rows;r++)
            {
                XSSFRow row=sheet.getRow(r);
                if(row==null)
                    continue;
                String name=getCellValue(row.getCell(0));
                String rollno=getCellValue(row.getCell(1));
                String registered=getCellValue(row.getCell(2));

                stmt.setString(1,name);
                stmt.setString(2,rollno);
                stmt.setString(3,registered);

                stmt.executeUpdate();
                rowCount++;
                System.out.println("Row "+rowCount+" Inserted");
            }
            System.out.println("Insertion Completed");

        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    private static String getCellValue(XSSFCell cell) {
        if(cell==null)
            return "";
        return cell.getStringCellValue();
    }
}
