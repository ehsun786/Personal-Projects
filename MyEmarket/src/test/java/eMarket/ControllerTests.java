package eMarket;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;

import eMarket.controller.IndexController;
import eMarket.controller.ItemController;
import eMarket.controller.OrderController;
import eMarket.controller.ProductController;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {EMarketApp.class,WebConfig.class,DbConfig.class})
@SpringApplicationConfiguration(classes = {EMarketApp.class,WebConfig.class,DbConfig.class})
@WebAppConfiguration
public class ControllerTests {
    @Test
    public void testViewController() throws Exception{     
        IndexController controller = new IndexController();
        String view = controller.index();       
        assertEquals("index", view);
    }
    @Test
    public void testProductController() throws Exception{     
    	ProductController controller = new ProductController();
    	String view = controller.index(new ExtendedModelMap());       
    	assertEquals("form/productMaster", view);
    }
    @Test
    public void testOrderController() throws Exception{     
    	OrderController controller = new OrderController();
    	String view = controller.index(new ExtendedModelMap());       
    	assertEquals("form/orderMaster", view);
    }
    @Test
    public void testItemController() throws Exception{     
    	ItemController controller = new ItemController();
    	String view = controller.index();       
    	assertEquals("form/itemDetail", view);
    }
//    @Test
//    public void testAuthController() throws Exception{     
//        AuthController controller = new AuthController();
//        // String view = controller.index();       
//        // assertEquals("index", view);
//    }
}