package order.tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import kz.yandex.order.OrderModel;
import kz.yandex.order.OrderSteps;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.*;
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
                .statusCode(SC_CREATED)
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