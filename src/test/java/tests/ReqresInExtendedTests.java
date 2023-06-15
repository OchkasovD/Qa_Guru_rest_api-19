package tests;
import static helpers.CustomAllureListener.withCustomTemplates;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInExtendedTests extends TestBase {

    @Test
    @DisplayName("LOGIN - SUCCESSFUL")
    void successLoginWithLombokModelsTest() {
        LoginBodyLombokModel requestBody = new LoginBodyLombokModel();
        step("Prepare test data" , () -> {
                requestBody.setEmail("eve.holt@reqres.in");
        requestBody.setPassword("cityslicka");
});
        LoginResponseLombokModel loginResponse = step("Make request", () ->
                 given()
                        .log().uri()
                        .log().body()
                        .filter(withCustomTemplates())
                        .contentType(JSON)
                        .body(requestBody)
                        .when()
                        .post("login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseLombokModel.class));
       step("Check response", () ->
        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));

    }

}
