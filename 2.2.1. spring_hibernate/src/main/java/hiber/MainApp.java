package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user = new User("User1", "Lastname1", "user1@mail.ru");
      Car car = new Car(user, "Mercedes", 220);
      user.setCar(car);
      userService.add(user);

      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User userr : users) {
         System.out.println("Id = "+userr.getId());
         System.out.println("First Name = "+userr.getFirstName());
         System.out.println("Last Name = "+userr.getLastName());
         System.out.println("Email = "+userr.getEmail());
         System.out.println();
      }
//      System.out.println(userService.getUserById(1));
//      System.out.println(userService.getUserById(2));
//      System.out.println(userService.getUserById(3));
//      System.out.println(userService.getUserById(4));
//      System.out.println(userService.getUserById(5));

      System.out.println(userService.getUserByCar(car.getModel(), car.getSeries()));
      context.close();
   }
}
