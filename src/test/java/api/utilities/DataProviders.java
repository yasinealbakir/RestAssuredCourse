package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "user-data")
    public String[][] getAllUserData() throws IOException {

        Excel excelUtility = new Excel();
        int rownum = excelUtility.getRowCount();
        int colnum = excelUtility.getCellCount(1);

        String payload[][] = new String[rownum][colnum];
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colnum; j++) {
                payload[i - 1][j] = excelUtility.getCellData(i, j);
            }
        }
        return payload;
    }

}
