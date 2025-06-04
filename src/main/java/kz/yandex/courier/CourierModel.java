package kz.yandex.courier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierModel {

    private String login;
    private String password;
    private String firstName;

    public CourierModel(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
