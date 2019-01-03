import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.virtual.coin.SpringBootEntry;
import com.virtual.coin.service.OkenService;
import com.virtual.coin.utils.CommonConstant;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootEntry.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class OkenServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OkenService okenService;

    /*@Before
    public void init(){
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
    }
*/
    @Test
    public void getOken() {
        String url = getUrl(CommonConstant.BTC_TYPE);
        Object data = restTemplate.getForObject(url, Object.class);
        System.out.println(data);
    }

    private String getUrl(String type) {
        return "https://www.okex.com/v3/c2c/tradingOrders/book?side=all&baseCurrency=" + type
               + "&quoteCurrency=cny&userType=certified&paymentMethod=all";
    }

    @Test
    public void testGetPublicId(){
       //okenService.getPublicId("无敌大鲤鱼");
    }
}
