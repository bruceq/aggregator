package aggregator;

import com.aggregator.model.News;
import com.aggregator.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-service.xml", "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-mvc.xml"})
public class UserServiceImplTest {

    @Resource
    private NewsService newsService;

    @Test
    public void search() throws Exception {
        List<News> newss = newsService.selectAllByTitle("乌克兰");
        for (int i = 0; i < newss.size(); i++) {
            System.out.println(newss.get(i).getTitle());
        }

    }

}