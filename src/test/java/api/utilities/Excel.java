package api.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel {
    public static final String testDataExcelFileName = "userData.xlsx";
    public static final String currentDir = System.getProperty("user.dir");
    public static String testDataExcelPath = null;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    public static int rowNumber;
    public static int columnNumber;


    public static String getCellData(int RowNum, int ColNum) {
        cell = sheet.getRow(RowNum).getCell(ColNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    public static XSSFRow getRowData(int RowNum) {
        row = sheet.getRow(RowNum);
        return row;
    }

    public static int getRowCount() throws IOException {
        int rowcount = sheet.getLastRowNum();
        return rowcount;
    }

    public static int getCellCount(int rownum) throws IOException {
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        return cellcount;
    }

    public static void setExcelSheet(String sheetName) throws IOException {
        testDataExcelPath = currentDir + "\\src\\test\\resources\\ExcelFiles\\";
        FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
        workbook = new XSSFWorkbook(ExcelFile);
        sheet = workbook.getSheet(sheetName);
    }

    public static void setCellData(String value, int RowNum, int ColNum) throws IOException {
        row = sheet.getRow(RowNum);
        cell = row.getCell(ColNum);
        if (cell == null) {
            cell = row.createCell(ColNum);
        }
        cell.setCellValue(value);
        FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
        workbook.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

}
