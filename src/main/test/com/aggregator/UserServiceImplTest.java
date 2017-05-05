package aggregator;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-mvc.xml"})
public class UserServiceImplTest {
//    @Resource
//    private UserService userService;
//
//
//    @Test
//    public void selectByUser() throws Exception {
//
//    }

}