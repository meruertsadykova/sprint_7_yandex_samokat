package kz.yandex.courier;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public class CourierTestData {
    // Существующий логин курьера
    private final String existingLogin;
    // Несуществующий логин курьера
    private final String nonExistentLogin;
    // Существующий пароль курьера
    private final String existingPassword;
    // Несуществующий пароль курьера
    private final String nonExistentPassword;
    // Имя курьера
    private final String firstName;

    /**
     * Конструктор класса, инициализирующий рандомные тестовые данные
     */
    public CourierTestData() {
        Faker faker = new Faker();

        this.existingLogin = faker.name().username();
        this.nonExistentLogin = faker.name().username() + "_nonexist";
        this.existingPassword = faker.internet().password(8, 12);
        this.nonExistentPassword = faker.internet().password(8, 12);
        this.firstName = faker.name().firstName();
    }
}