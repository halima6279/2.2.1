package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

import static org.springframework.dao.support.DataAccessUtils.uniqueResult;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUserByCar(Car car){
      long carId = car.getId();
      User user =  sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u where u.id=:carId", User.class)
              .setParameter("carId", carId).uniqueResult();;
      return user;
   }
}
