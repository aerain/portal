import org.junit.Test;


import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    @Test
    public void testGet() throws SQLException, ClassNotFoundException {
        long id = 1;
        String name = "허윤호";
        String password = "1234";

        UserDao userDao = new UserDao();
        User user = userDao.get(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void testAdd() throws SQLException, ClassNotFoundException {
        User user = new User();
        String name = "heeee";
        String password = "tesst";
        user.setName(name);
        user.setPassword(password);

        UserDao userDao = new UserDao();
        userDao.add(user);

        Long id = userDao.add(user);

        User resultUser = userDao.get(id);

        assertThat(resultUser.getId(), is(id));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));
    }
}
