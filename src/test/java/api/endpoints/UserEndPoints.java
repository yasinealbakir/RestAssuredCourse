package api.endpoints;


import api.entities.User;
import api.utilities.Excel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.io.IOException;

import static api.utilities.Routes.*;
import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createSingleUser(XSSFRow row) {
        RestAssured.useRelaxedHTTPSValidation();
        User payload = new User();
        payload.setName(row.getCell(0).toString());
        payload.setGender(row.getCell(1).toString());
        payload.setEmail(row.getCell(2).toString());
        payload.setStatus(row.getCell(3).toString());

        Response response = given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + TOKEN)
                .body(payload)
                .when()
                .post(USER_POST_URL);
        return response;
    }

    public static Response createMultipleUser(User payload) {
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + TOKEN)
                .body(payload)
                .when()
                .post(USER_POST_URL);
        return response;
    }

    public static Response getUser(XSSFRow row) {
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + TOKEN)
                .pathParam("id", row.getCell(4).toString())
                .when()
                .get(USER_GET_URL);
        return response;
    }

    public static Response updateUser(XSSFRow row) {
        RestAssured.useRelaxedHTTPSValidation();
        User payload = new User();
        payload.setName(row.getCell(0).toString());
        payload.setGender(row.getCell(1).toString());
        payload.setEmail(row.getCell(2).toString());
        payload.setStatus(row.getCell(3).toString());
        String userId = row.getCell(4).toString();

        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .headers("Authorization", "Bearer " + TOKEN)
                .body(payload)
                .when()
                .put(USER_PUT_URL);
        return response;

    }

    public static Response deleteUser(XSSFRow row) {
        RestAssured.useRelaxedHTTPSValidation();
        String userId = row.getCell(4).toString();
        Response response = given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .headers("Authorization", "Bearer " + TOKEN)
                .when()
                .delete(USER_DELETE_URL);
        return response;

    }

    public static void setSingleUserId(int row, int col, String id) throws IOException {
        Excel.rowNumber = row;
        Excel.columnNumber = col;
        Excel.setCellData(id, row, col);
    }
}
