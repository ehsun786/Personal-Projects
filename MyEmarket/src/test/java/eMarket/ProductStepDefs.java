package eMarket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
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
import eMarket.domain.Product;
import eMarket.domain.Store;
import eMarket.repository.ProductRepository;
import eMarket.repository.StoreRepository;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {EMarketApp.class,WebConfig.class,DbConfig.class,AuthorizationController.class,ProductController.class,OrderController.class,ItemController.class})
@Transactional
public class ProductStepDefs {
	
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private ResultActions result;

    @Autowired 
    StoreRepository storeRepo;
    @Autowired 
    ProductRepository productRepo;
    
    Product product;
    Store store;

    
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

    @Given("^I have a product X with name \"([^\"]*)\", description \"([^\"]*)\" and price \"([^\"]*)\"$")
    public void i_have_a_product_X_with_name_description_and_price(String name, String description, Double price) throws Throwable {
        product = new Product(name,description,price);
    }

    @When("^I add the product using \"([^\"]*)\"$")
    public void i_add_the_product_using(String request) throws Throwable {
    	result = this.mockMvc.perform(post(request)
        	.param("id", String.valueOf(product.getId()))
    		.param("name", product.getName())
    		.param("description", product.getDescription())
        	.param("price", String.valueOf(product.getPrice())));
    }

    @Then("^a new product is stored in the repository$")
    public void a_new_product_is_stored_in_the_repository() throws Throwable {
        store = getStore();
        assertThat("Product not added to the catalogue.", store.getProductList().size(),greaterThan(0)); 
    }

    @Given("^I have a product X with name \"([^\"]*)\", description \"([^\"]*)\" and price \"([^\"]*)\" in the catalogue$")
    public void i_have_a_product_X_with_name_description_and_price_in_the_catalogue(String name, String description, Double price) throws Throwable {
    	store = getStore();
		
    	product = productRepo.save(new Product(name,description,price));
    	store.getProductList().add(product);
    	storeRepo.save(store);
    }

    @When("^I do a post \"([^\"]*)\" for the product X description \"([^\"]*)\"$")
    public void i_do_a_post_for_the_product_X_description(String request, String description) throws Throwable {
    	result = this.mockMvc.perform(post(request)
            .param("id", String.valueOf(product.getId()))
        	.param("name", product.getName())
        	.param("description", description) // <-- update
            .param("price", String.valueOf(product.getPrice())));
    }

    @Then("^the product  \"([^\"]*)\" contains the description \"([^\"]*)\"$")
    public void the_product_contains_the_description(String productName, String description) throws Throwable {
    	store = getStore();
    	Product actualProduct = store.getProductList().stream().filter(p -> p.getName().equals(productName)).findFirst().get();
        assertThat("Description attribute not updated correctly.", actualProduct.getDescription(),is(description)); 
    }

    @When("^I delete the product X using \"([^\"]*)\"$")
    public void i_delete_the_product_X_using(String request) throws Throwable {
    	result = this.mockMvc.perform(get(request)
    		.param("productId", String.valueOf(product.getId())));
    }

    @Then("^The product \"([^\"]*)\" no longer exists in the repository$")
    public void the_product_no_longer_exists_in_the_repository(String arg1) throws Throwable {
    	store = getStore();
        assertThat("The product has not been deleted.", store.getProductList().stream().filter(p -> p.getId() == product.getId()).findAny().isPresent(), is(false));
    }
    
}
