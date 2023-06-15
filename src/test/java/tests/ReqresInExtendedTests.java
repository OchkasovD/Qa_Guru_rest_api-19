package tests;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInExtendedTests extends TestBase {

    @Test
    @DisplayName("LOGIN - SUCCESSFUL")
    void successLoginWithPojoModelsTest() {

        LoginBodyModel requestBody = new LoginBodyModel();
        requestBody.setEmail("eve.holt@reqres.in");
        requestBody. setPassword("cityslicka");

        LoginResponseModel loginResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());

    }

}
