package tests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
public class ApiTests extends TestBase {


    @Test
    @DisplayName("REGISTER - SUCCESSFUL")
    void successRegisterTest() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

       given()
               .log().uri()
               .log().body()
               .contentType(JSON)
               .body(requestBody)
               .when()
               .post("register")
               .then()
               .log().status()
               .log().body()
               .statusCode(200)
               .body("id", is(4))
               .body("token", is("QpwL5tke4Pnpja7X4"));
    }
    @Test
    @DisplayName("REGISTER - UNSUCCESSFUL")
    void unsuccessfulRegisterTest() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\"}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }
        @Test
        @DisplayName("LOGIN - SUCCESSFUL")
        void SuccessLoginTest()  {
            String loginBody = "{\n" +
                    "    \"email\": \"eve.holt@reqres.in\",\n" +
                    "    \"password\": \"cityslicka\"\n" +
                    "}";

            given()
                    .log().uri()
                    .log().body()
                    .contentType(JSON)
                    .body(loginBody)
                    .when()
                    .post("login")
                    .then()
                    .log().status()
                    .log().body()
                    .statusCode(200)
                    .body("token", is("QpwL5tke4Pnpja7X4"));


        }
    }


