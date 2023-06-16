package tests;
import static helpers.CustomAllureListener.withCustomTemplates;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.lombok.RegistrationBodyLombokModel;
import models.lombok.RegistrationResponseLombokModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.specs.*;

import io.restassured.builder.ResponseSpecBuilder;

public class ReqresInExtendedTests extends TestBase {

    @Test
    @DisplayName("LOGIN - SUCCESSFUL")
    void successLoginWithLombokModelsTest() {
        LoginBodyLombokModel requestBody = new LoginBodyLombokModel();
        step("Prepare test data", () -> {
            requestBody.setEmail("eve.holt@reqres.in");
            requestBody.setPassword("cityslicka");
});
        LoginResponseLombokModel loginResponse = step("Make request", () ->
                 given()
                         .spec(loginRequestSpec)
                         .body(requestBody)
                         .when()
                         .post("login")
                         .then()
                         .spec(loginResponseSpec)
                         .extract().as(LoginResponseLombokModel.class));
        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));

       }

    @Test
    @DisplayName("REGISTER - SUCCESSFUL")
    void successRegisterWithLombokModelsTest() {
        RegistrationBodyLombokModel requestBody = new RegistrationBodyLombokModel();
        step("Prepare test data", () -> {
            requestBody.setEmail("eve.holt@reqres.in");
            requestBody.setPassword("pistol");
        });
        RegistrationResponseLombokModel registrationResponse = step("Make request", () ->
                given()
                        .spec(loginRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("register")
                        .then()
                        .spec(registerResponseSpec)
                        .extract().as(RegistrationResponseLombokModel.class));
        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken()));
        assertEquals("4", registrationResponse.getId());

    }

    }


