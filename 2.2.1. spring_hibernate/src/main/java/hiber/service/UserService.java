package hiber.service;

import hiber.model.User;
import java.util.List;

public interface UserService {

    void add(User user);//todo: codeStyle

    List<User> listUsers();

    User getUserByCar(String model, int series);
}