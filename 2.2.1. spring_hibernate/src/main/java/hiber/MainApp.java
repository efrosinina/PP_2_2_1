package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1",
              "user1@mail.ru", new Car("BMW", 111)));
      userService.add(new User("User2", "Lastname2",
              "user2@mail.ru", new Car("Honda", 222)));
      userService.add(new User("User3", "Lastname3",
              "user3@mail.ru", new Car("Kia", 333)));
      userService.add(new User("User4", "Lastname4",
              "user4@mail.ru", new Car("Toyota", 444)));

      List<User> users = userService.getListUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println("Такая машина имеется у данного User-а: " + userService.getUserByCar("Kia", 333));

      context.close();
   }
}