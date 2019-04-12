import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class UserDaoTests {

    private UserDao userDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext applicationContext = new XmlBeanDefinitionReader()
        userDao = applicationContext.getBean("userDao", UserDao.class);
//        DaoFactory daoFactory = new DaoFactory();
//        userDao = daoFactory.getUserDao();
    }

    @Test
    public void testJejuGet() throws SQLException, ClassNotFoundException {
        long id = 1;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testJejuAdd() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "heeee";
        String password = "tesst";
        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }

    @Test
    public void testJejuUpdate() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "heeee";
        String password = "tesst";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);

        user.setId(id);
        String changedName = "허윤호";
        String changedPassword = "1234";
        user.setName(changedName);
        user.setPassword(changedPassword);

        userDao.update(user);

        User resultUser = userDao.get(id);
        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(changedName));
        assertThat(resultUser.getPassword(), is(changedPassword));
    }


    @Test
    public void testJejuDelete() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "heeee";
        String password = "tesst";

        user.setName(name);
        user.setPassword(password);
        Long id = userDao.add(user);
        System.out.println(id+ " 제거");
//        user.setId(id);

        userDao.delete(id);

        user = userDao.get(id);

        assertThat(user, nullValue());
    }
}
