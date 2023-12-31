package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      try (Session session = sessionFactory.openSession()) {
         TypedQuery<User> createUserQuery = session.createQuery("FROM User WHERE firstName = :firstName" +
                 " and lastName = :lastName and email = :email");
         createUserQuery.setParameter("firstName", user.getFirstName());
         createUserQuery.setParameter("lastName", user.getLastName());
         createUserQuery.setParameter("email", user.getEmail());
         List<User> existingUsers = createUserQuery.getResultList();
         if (existingUsers.isEmpty()) {
            session.getSessionFactory().getCurrentSession().save(user);
         }
      }
   }

   @Override
   public List<User> listUsers() {
      try(Session session = sessionFactory.openSession()) {
         return session.createQuery("from User").getResultList();
      }
   }

   @Override
   public User getUserByCar(String model, Integer series) {
      try(Session session = sessionFactory.openSession()) {
         TypedQuery<User> getUserQuery = session.createQuery("from User user where" +
                 " user.car.model = :model and user.car.series = :series", User.class);
         getUserQuery.setParameter("model", model);
         getUserQuery.setParameter("series", series);
         return getUserQuery.setMaxResults(1).getSingleResult();
      }
   }
}