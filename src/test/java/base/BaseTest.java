package base;

import io.restassured.RestAssured;
import org.junit.Before;

import static kz.yandex.constants.ApiConstants.SCOOTER_URL;

public abstract class BaseTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = SCOOTER_URL;
    }
}
