package kr.ac.jejunu.user;

import kr.ac.jejunu.user.model.Comment;
import kr.ac.jejunu.user.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class HibernateTests {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setup() {
        Configuration configuration = new Configuration()
                .configure("jejunu.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class);
        final StandardServiceRegistry standardServiceRegistry =
                new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        try {
            sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
        } catch (HibernateException e) {
            StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
        }

    }

    @Test
    public void getUser() {
        // 하나의 세션을 얻어옴.
        Session session = sessionFactory.openSession();
        // 질의를 날림
        User user = session.get(User.class, 1);


        assertThat(user.getName(), is("허윤호"));
        assertThat(user.getPassword(), is("1234"));
        session.close();
    }

    @Test
    public void saveUser() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        User user = User.builder().name("abcd").password("111").build();
        try {
            session.save(user);
            //hibernate 가 필요할때마다 캐싱하기때문에 쿼리를 여기서는 날리지 않음.
            //한번 수행된 쿼리가 다시한번 수행되지 않기 위해 세션 단위 캐싱을 수행함.


            User savedUser = session.get(User.class, user.getId());
            assertThat(savedUser.getName(), is(user.getName()));
            assertThat(savedUser.getName(), is(user.getPassword()));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }
    @AfterClass
    public static void destroy() {
        sessionFactory.close();
    }
}
