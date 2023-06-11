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
    void SuccessLoginTest() {
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

    @Test
    @DisplayName("CREATE")
    void CreateUserTest() {
        String loginBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .body("id", notNullValue())
                .body("createdAt", notNullValue());


    }

    @Test
    @DisplayName("UPDATE")
    void UpdateUserTest() {
        String loginBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .patch("users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", notNullValue());
    }
}


