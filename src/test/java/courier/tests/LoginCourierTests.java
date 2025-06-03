package courier.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import kz.yandex.courier.CourierModel;
import kz.yandex.courier.CourierSteps;
import kz.yandex.courier.CourierTestData;
import org.junit.After;
import org.junit.Test;

public class LoginCourierTests extends BaseTest {

    private final CourierTestData courierTestData = new CourierTestData();
    String id = null;


    @Test
    @DisplayName("Успешный логин курьера")
    @Description("Логин курьера в системе.Курьер может авторизоваться. Успешный запрос возвращает id.")
    public void loginCourierTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                courierTestData.getExistingPassword()
        );
        CourierSteps.createCourier(courier);

        id = CourierSteps.loginCourier(courier)
                .then()
                .extract()
                .path("id")
                .toString();
        Response response = CourierSteps.loginCourier(courier);
        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Для авторизации курьера необходимо передать все обязательные поля. Передаётся пустой логин")
    public void authorizationCourierWithoutLoginTest() {
        CourierModel courier = new CourierModel(
                "",
                courierTestData.getExistingPassword()
        );

        Response response = CourierSteps.loginCourier(courier);
        response.then()
                .assertThat()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера без пароля")
    @Description("Для авторизации курьера необходимо передать все обязательные поля. Передается пустой пароль курьера")
    public void authorizationCourierWithoutPasswordTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                ""
        );

        Response response = CourierSteps.loginCourier(courier);
        response.then()
                .assertThat()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера c неправильным логином")
    @Description("Для авторизации курьера необходимо передать существующие данные. Передается неправильный логин")
    public void authorizationCourierWithNonExistentLoginTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getNonExistentLogin(),
                courierTestData.getExistingPassword()
        );

        Response response = CourierSteps.loginCourier(courier);
        response.then()
                .assertThat()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация курьера c неправильным паролем")
    @Description("Для авторизации курьера необходимо передать существующие данные. Передается неверный пароль курьера")
    public void authorizationCourierWithNonExistentPasswordTest() {
        CourierModel courier = new CourierModel(
                courierTestData.getExistingLogin(),
                courierTestData.getNonExistentPassword()
        );

        Response response = CourierSteps.loginCourier(courier);
        response.then()
                .assertThat()
                .statusCode(404)
                .and()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier() {
        if (id != null) {
            CourierSteps.deleteCourier(id);
        }
    }
}