package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   private String hqlQuery = "from User user where user.car.model = :model and user.car.series = :series";//todo: ..а вот HQL_query's принято в отличии от константных SQL-запросов писать в методах

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {//todo: забыли try_with_resources на методах..
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");//todo: codeStyle -> listUsersQuery..
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hqlQuery, User.class);
      query.setParameter("model", model).setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }
}