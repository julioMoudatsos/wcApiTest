import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @BeforeClass
    public static void init(){
        RestAssured.baseURI ="http://localhost:8001/tasks-backend";
    }

    @Test
    public void tarefasListadas(){
        given().when()
                .get("/todo")
                .then().statusCode(HttpStatus.SC_OK);
    }


    @Test
    public void publicarTarefa(){

        given().
                contentType(ContentType.JSON).
                body("   {\n" +
                        "\n" +
                        "            \"task\": \"julio2\",\n" +
                        "                \"dueDate\": \"2033-09-21\"\n" +
                        "        }")
                .when()
                .post("/todo")
                .then().statusCode(HttpStatus.SC_CREATED);

    }


    @Test
    public void naoPublicarTarefa(){

        given().
                contentType(ContentType.JSON).
                body("   {\n" +
                        "\n" +
                        "            \"task\": \"julio2\",\n" +
                        "                \"dueDate\": \"2020-09-21\"\n" +
                        "        }")
                .when()
                .post("/todo")
                .then().statusCode(400);

    }
}
