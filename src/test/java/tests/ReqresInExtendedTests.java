package tests;
import models.lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.specs.*;

public class ReqresInExtendedTests extends TestBase {
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
                        .spec(registerRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("register")
                        .then()
                        .spec(registerResponseSpec)
                        .extract().as(RegistrationResponseLombokModel.class));
        step("Check response", () -> {
            assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken());
            assertEquals("4", registrationResponse.getId());
        });
    }

    @Test
    @DisplayName("REGISTER - UNSUCCESSFUL")
    void unsuccessfulRegisterWithLombokModelsTest() {
        RegistrationBodyLombokModel requestBody = new RegistrationBodyLombokModel();
        step("Prepare test data", () -> requestBody.setEmail("eve.holt@reqres.in"));
        RegistrationResponseLombokModel registrationResponse = step("Make request", () ->
                given()
                        .spec(registerRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("register")
                        .then()
                        .spec(unsuccessRegisterResponseSpec)
                        .extract().as(RegistrationResponseLombokModel.class));
        step("Check response", () -> assertEquals("Missing password", registrationResponse.getError()));

    }

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
        step("Check response", () -> assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    @DisplayName("CREATE")
    void createUserLombokModelsTest() {
        UserBodyLombokModel requestBody = new UserBodyLombokModel();
        step("Prepare test data", () -> {
            requestBody.setName("morpheus");
            requestBody.setJob("leader");
        });
        UserResponseLombokModel createResponse = step("Make request", () ->
                given()
                        .spec(createRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("users")
                        .then()
                        .spec(createResponseSpec)
                        .extract().as(UserResponseLombokModel.class));
        step("Check response", () ->
        {
            assertEquals("morpheus", createResponse.getName());
            assertEquals("leader", createResponse.getJob());
        });

    }


    @Test
    @DisplayName("UPDATE")
    void updateUserLombokModelsTest() {
        UserBodyLombokModel requestBody = new UserBodyLombokModel();
        step("Prepare test data", () -> {
            requestBody.setName("morpheus");
            requestBody.setJob("zion resident");
        });
        UserResponseLombokModel updateResponse = step("Make request", () ->
                given()
                        .spec(updateRequestSpec)
                        .body(requestBody)
                        .when()
                        .post("users/2")
                        .then()
                        .spec(updateResponseSpec)
                        .extract().as(UserResponseLombokModel.class));
        step("Check response", () ->
        {
            assertEquals("morpheus", updateResponse.getName());
            assertEquals("zion resident", updateResponse.getJob());
        });

    }
}