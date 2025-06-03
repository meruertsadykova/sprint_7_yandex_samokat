package order.tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import kz.yandex.courier.CourierModel;
import kz.yandex.order.OrderModel;
import kz.yandex.order.OrderSteps;
import kz.yandex.order.OrderTestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static kz.yandex.constants.ApiConstants.SCOOTER_URL;
import static kz.yandex.order.OrderTestData.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class CreateOrderTests extends BaseTest {

    // Параметры для цветов заказа
    private final String color;
    private String track;

    public CreateOrderTests(String color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "colour = ''{0}''")
    public static Object[] getColour() {
        return new Object[][]{
                {"BLACK"},
                {"GREY"},
                {"BLACK, GREY"},
                {""}
        };
    }


    @Test
    @DisplayName("Создание заказа")
    @Description("Заказ можно создать с указанием только одного цвета или обоих цветов")
    public void createOrderTest() {
        OrderModel order = new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATA, COMMENT, new String[]{color});
        Response response = OrderSteps.createOrder(order);

        track = response.then().extract().path("track").toString();
        response.then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без указания параметра color")
    @Description("Заказ можно создать, если не указать параметр color")
    public void createOrderWithoutColorTest() {
        OrderModel order = new OrderModel(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATA, COMMENT);
        Response response = OrderSteps.createOrder(order);

        track = response.then().extract().path("track").toString();
        response.then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("track", notNullValue());
    }

    /**
     * Метод для отмены заказа после создания
     */
    @After
    public void cancelOrder() {
        OrderSteps.cancelOrder(track);
    }
}