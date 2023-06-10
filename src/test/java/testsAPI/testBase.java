package testsAPI;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
public class testBase {
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}

