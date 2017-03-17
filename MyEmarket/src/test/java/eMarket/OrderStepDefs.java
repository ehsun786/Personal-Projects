package eMarket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import eMarket.controller.AuthorizationController;
import eMarket.controller.ItemController;
import eMarket.controller.OrderController;
import eMarket.controller.ProductController;
import eMarket.domain.Order;
import eMarket.domain.OrderItem;
import eMarket.domain.Product;
import eMarket.domain.Store;
import eMarket.repository.OrderItemRepository;
import eMarket.repository.OrderRepository;
import eMarket.repository.ProductRepository;
import eMarket.repository.StoreRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {EMarketApp.class,WebConfig.class,DbConfig.class,AuthorizationController.class,ProductController.class,OrderController.class,ItemController.class})
@Transactional
public class OrderStepDefs {
	
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    private ResultActions result;

    @Autowired 
    StoreRepository storeRepo;
    @Autowired 
    OrderItemRepository itemRepo;
    
    @Autowired 
    OrderRepository orderRepo;
    @Autowired 
    ProductRepository productRepo;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
    @Before("@repo")
    public void beforeScenario() {
    	store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	storeRepo.delete(store);
    	if (storeRepo.findByName(EMarketApp.STORE_NAME).size() == 0) {
			store = storeRepo.save(new Store(EMarketApp.STORE_NAME));
		}
    }
    
    public Store getStore() {
    	return storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    }
    
    @When("^I add a new order using \"([^\"]*)\"$")
    public void i_add_a_new_order_using(String arg1) throws Throwable {
    	result = this.mockMvc.perform(get(arg1));
    }

    @Then("^a new order is stored in the repository$")
    public void a_new_order_is_stored_in_the_repository() throws Throwable {
        assertThat(getStore().getOrderList().size(), greaterThan(0));
    }

    Product product;
    Store store;
    Order orderX;
    @Given("^I have a product with name \"([^\"]*)\", description \"([^\"]*)\" and price \"([^\"]*)\" in the catalogue$")
    public void i_have_a_product_with_name_description_and_price_in_the_catalogue(String name, String description, Double price) throws Throwable {
    	store = getStore();
    			
    	product = productRepo.save(new Product(name,description,price));
    	store.getProductList().add(product);
    	storeRepo.save(store);
    }

    @Given("^an order X without any items$")
    public void an_order_X_without_any_items() throws Throwable {
    	store = getStore();
    	orderX = orderRepo.save(new Order());
    	store.getOrderList().add(orderX);
    	storeRepo.save(store);
    }

    @When("^I do a post \"([^\"]*)\" for the order X with an item with product \"([^\"]*)\" and amount \"([^\"]*)\"$")
    public void i_do_a_post_for_the_order_X_with_an_item_with_product_and_amount(String request, String productName, String amount) throws Throwable {
    	OrderItem item = itemRepo.save(new OrderItem());
//    	request += "?action=Submit";
    	System.out.println("---------------------------request= " + request);
    	result = this.mockMvc.perform(post(request)
        		.param("action", "Submit")
        		.param("id", String.valueOf(item.getId()))
        		.param("orderId", String.valueOf(orderX.getId()))
        		.param("productId", String.valueOf(product.getId()))
        		.param("amount", amount));
    }

    @Then("^the order X contains an item with \"([^\"]*)\" products \"([^\"]*)\" and the total cost of the order is \"([^\"]*)\"$")
    public void the_order_X_contains_an_item_with_products_and_the_total_cost_of_the_order_is(int amount, String productName, Double cost) throws Throwable {
        store = getStore();
        Order o = ((List<Order>)store.getOrderList()).get(0);
        assertThat("The cost of the order is incorrect.", o.getCost(), is(cost));
        OrderItem item = o.getItemList().get(0);
        assertThat("The item is not associated with the correct product.", item.getProduct().getName(), is(productName));
        assertThat("The amount is incorrect.", item.getAmount(), is(amount));
    }

    @When("^I delete the order X using \"([^\"]*)\"$")
    public void i_delete_the_order_X_using(String request) throws Throwable {
    	result = this.mockMvc.perform(get(request)
    			.param("orderId", String.valueOf(orderX.getId())));
    }

    @Then("^The order X no longer exists in the repository$")
    public void the_order_X_no_longer_exists_in_the_repository() throws Throwable {
    	store = getStore();
        assertThat("The order has not been deleted.", store.getOrderList().stream().filter(o -> (((Order) o).getId() == orderX.getId())).findAny().isPresent(), is(false));
    }
    
    @Given("^an order X with with an item with product \"([^\"]*)\" and amount \"([^\"]*)\"$")
    public void an_order_X_with_with_an_item_with_product_and_amount(String productName, int amount) throws Throwable {
    	store = getStore();
    	orderX = orderRepo.save(new Order());
    	orderX.addItem(product, amount);
    	store.getOrderList().add(orderX);
    	storeRepo.save(store);
    }

    @When("^I do a get \"([^\"]*)\" for the order X and item containing \"([^\"]*)\"$")
    public void i_do_a_get_for_the_order_X_and_item_containing(String request, String productName) throws Throwable {
    	System.out.println(orderX.toString());
    	Optional<OrderItem> opItem = orderX.getItemList().stream().filter(i -> i.getProduct().getName().equals(productName)).findFirst();
    	result = this.mockMvc.perform(get(request)
    			.param("orderId", String.valueOf(orderX.getId()))
    			.param("itemId", String.valueOf(opItem.get().getId())));
    }

    @Then("^the order X does not contain the item with \"([^\"]*)\" and the total cost of the order is \"([^\"]*)\"$")
    public void the_order_X_does_not_contain_the_item_with_and_the_total_cost_of_the_order_is(String productName, Double cost) throws Throwable {
    	store = getStore();
    	orderX = store.getOrderList().stream().filter(o -> o.getId() == orderX.getId()).findFirst().get();
    	Optional<OrderItem> opItem = orderX.getItemList().stream().filter(i -> i.getProduct().getName() == productName).findFirst();
    	assertThat("Order item should have been deleted.", opItem.isPresent(), is(false));
    	assertThat("The cost has not been updated after deleting the item.", orderX.getCost(), is(cost));
    }

}
