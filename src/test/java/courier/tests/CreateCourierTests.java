package courier.tests;

import static org.hamcrest.CoreMatchers.equalTo;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import kz.yandex.courier.CourierModel;
import kz.yandex.courier.CourierSteps;
import kz.yandex.courier.CourierTestData;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;

public class CreateCourierTests extends BaseTest {

    private final CourierTestData courierTestData = new CourierTestData();
    String id = null; // Переменная для хранения id курьера


    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера с валидно заполеннными полями")
    public void createCourierTest() {

        // Создаю объект курьера с валидными данными
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                courierTestData.getExistingPassword(),
                courierTestData.getFirstName()
        );

        Response response = CourierSteps.createCourier(courier);
        // Получаю ID курьера после его создания
        id = CourierSteps.loginCourier(courier)
                .then().extract()
                .path("id").toString();

        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера без имени")
    @Description("Создание курьера только с логином и паролем, без имени")
    public void createCourierWithoutFirstNameTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                courierTestData.getExistingPassword(),
                "" // имя отсутствует
        );

        Response response = CourierSteps.createCourier(courier);
        // Получаю ID курьера, чтобы потом удалить
        id = CourierSteps.loginCourier(courier)
                .then().extract()
                .path("id").toString();

        response.then()
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }


    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Создание курьера только с паролем и именем")
    public void createCourierWithoutLoginTest() {

        // Создаю объект курьера без логина
        CourierModel courier = new CourierModel(
                "",
                courierTestData.getExistingPassword(),
                courierTestData.getFirstName()
        );

        Response response = CourierSteps.createCourier(courier);
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Создание курьера только с логином и именем")
    public void createCourierWithoutPasswordTest() {

        // Создаю объект курьера без пароля
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                "",
                courierTestData.getFirstName()
        );

        Response response = CourierSteps.createCourier(courier);
        response.then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание одинаковых курьеров")
    @Description("Создание курьера с валидными данными и повтор создания этого же курьера")
    public void createDoubleCouriersTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                courierTestData.getExistingPassword(),
                courierTestData.getFirstName()
        );

        CourierSteps.createCourier(courier);
        Response response = CourierSteps.createCourier(courier);

        // Получаю id курьера после его создания
        id = CourierSteps.loginCourier(courier).then().extract().path("id").toString();

        response.then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .and()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    /**
     * Метод для удаления созданного курьера в случае id не равен null
     */
    @After
    public void deleteCourier() {
        if (id != null) {
            CourierSteps.deleteCourier(id);
        }
    }
}