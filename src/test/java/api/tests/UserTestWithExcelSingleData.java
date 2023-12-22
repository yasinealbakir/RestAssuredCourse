package api.tests;

import api.endpoints.UserEndPoints;
import api.utilities.Excel;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;

public class UserTestWithExcelSingleData {

    @BeforeClass
    public void setup() throws IOException {
        Excel.setExcelSheet("Sheet1");
    }

    @Test(priority = 0)
    public void createSingleUserTest() throws IOException {
        Response response = UserEndPoints.createSingleUser(Excel.getRowData(1));
        response.then().log().body();
        String userId = String.valueOf(response.jsonPath().getInt("id"));
        UserEndPoints.setSingleUserId(1, 4, userId);
        UserEndPoints.setSingleUserId(2, 4, userId);
    }

    @Test(priority = 1)
    public void getUserTest() throws IOException {
        Response response = UserEndPoints.getUser(Excel.getRowData(1));
        response.then().statusCode(200).log().body();
    }

    @Test(priority = 2)
    public void updateUserTest() throws IOException {
        Response response = UserEndPoints.updateUser(Excel.getRowData(2));
        response.then().statusCode(200);

        //Checking data after update operation
        Response getResponse = UserEndPoints.getUser(Excel.getRowData(1));
        getResponse.then().
                body("status", equalTo("inactive"))
                .log().body()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteUserTest() {
        Response deleteResponse = UserEndPoints.deleteUser(Excel.getRowData(1));
        deleteResponse.then()
                .log().body()
                .statusCode(204);

        //Checking data after delete operation
        Response getResponse = UserEndPoints.getUser(Excel.getRowData(1));
        getResponse.then().body("message", equalTo("Resource not found"))
                .log().body()
                .statusCode(404);
    }

}
