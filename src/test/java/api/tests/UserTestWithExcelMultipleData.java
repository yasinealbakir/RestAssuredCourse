package api.tests;

import api.endpoints.UserEndPoints;
import api.entities.User;
import api.utilities.DataProviders;
import api.utilities.Excel;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestWithExcelMultipleData {

    @BeforeClass
    public void setup() throws IOException {
        Excel.setExcelSheet("Sheet2");
    }

    @Test(priority = 0, dataProvider = "user-data", dataProviderClass = DataProviders.class)
    public void createMultipleUserTest(String[] userData) throws IOException {
        User user = new User();
        user.setName(userData[0]);
        user.setGender(userData[1]);
        user.setEmail(userData[2]);
        user.setStatus(userData[3]);

        Response response = UserEndPoints.createMultipleUser(user);
        response.then().log().body().statusCode(201);

    }
}
