package testsAPI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
public class apiTests extends testBase {


    @Test
    @DisplayName("REGISTER - SUCCESSFUL")
    void successLoginTest() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }"; // BAD PRACTICE

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
    }

