package kz.yandex.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    //Поля создания заказа
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;

    //Поля получения списка заказов
    private int courierId;
    private String nearestStation;
    private int limit;
    private int page;

    //Ещё поля в теле запроса на получение спика заказов
    private int id;
    private int track;
    private String createdAt;
    private String updatedAt;
    private int status;

    // Конструктор для создания заказа с обязательными полями (кроме цвета).
    public OrderModel(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    // Конструктор для создания заказа с обязательными полями без указания цвета самоката
    public OrderModel(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }
}
