package eMarket.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eMarket.EMarketApp;
import eMarket.domain.Order;
import eMarket.domain.OrderItem;
import eMarket.domain.Product;
import eMarket.domain.Store;
import eMarket.repository.OrderItemRepository;
import eMarket.repository.StoreRepository;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired StoreRepository storeRepo;
	@Autowired OrderItemRepository itemRepo;

	public String index() {
		return "form/itemDetail";
	}
	
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String itemDetail(
    		@ModelAttribute("itemFormDto") ItemFormDto itemFormDto, 
    		@RequestParam(value="itemId", required=false, defaultValue="-1") int itemId, 
    		@RequestParam(value="orderId", required=true, defaultValue="-1") int orderId
    ) {
    	// fetch info
    	Store store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// prepare model for view
    	if (itemId > -1) {
    		Order order = store.getOrderList().stream().filter(o -> o.getId() == orderId).findFirst().orElse(null);
	    	OrderItem item = order.getItemList().stream().filter(p -> p.getId()==itemId).findFirst().orElse(null);
	    	itemFormDto.setId(itemId);
	    	itemFormDto.setProductId(item.getProduct().getId());
	    	itemFormDto.setAmount(item.getAmount());
    	} else {
//    		OrderItem savedItem = itemRepo.save(new OrderItem());
//    		itemFormDto.setId(savedItem.getId());
    	}
    	itemFormDto.setOrderId(orderId);
    	itemFormDto.setProductList(store.getProductList());
    	// choose view    	
    	return "form/itemDetail";
    }   
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ExceptionHandler(SpringException.class)
    public String add(@ModelAttribute("itemFormDto") ItemFormDto itemFormDto, @RequestParam(value="action") String action,  Model model) {
    	// form validation
    	if (itemFormDto.getAmount() < -1) 
			throw new SpringException("Amount is negative.");
    	// fetch info
    	Store store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// backend logic
    	Order order = store.getOrderList().stream().filter(o -> o.getId() == itemFormDto.getOrderId()).findFirst().orElse(null);
    	if (order==null) {
    		throw new SpringException("Order with id " + itemFormDto.getOrderId() + " not found");
    	}
    	
    	if (action.startsWith("Submit")) {
    		Optional<OrderItem> itemOp = order.getItemList().stream().filter(item -> (item.getId() == itemFormDto.getId())).findFirst();
    		if (itemOp.isPresent()) {
    			// edit
    			OrderItem item = itemOp.get();
    			Product product = store.getProductList().stream().filter(p -> p.getId() == itemFormDto.getProductId()).findFirst().get();
    			item.setProduct(product);
    			item.setAmount(itemFormDto.getAmount());
    			item.setCost(item.getProduct().getPrice() * item.getAmount());
    		} else {
    			// create
    			Product product = store.getProductList().stream().filter(p -> p.getId()==itemFormDto.getProductId()).findFirst().orElse(null);
    			order.addItem(product, itemFormDto.getAmount());
    		}
    		order.updateCost();
    		storeRepo.save(store);
    	}
    	// prepare model for view
    	model.addAttribute("order", order);
    	// choose view    	
    	return "form/orderDetail";
    }   

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(
    		@RequestParam(value="itemId", required=false, defaultValue="-1") int itemId, 
    		@RequestParam(value="orderId", required=true, defaultValue="-1") int orderId,
    		Model model
    ) {
    	// fetch info from repo
    	Store store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// backend logic
    	Order order = store.getOrderList().stream().filter(o -> o.getId()==orderId).findFirst().get();
    	order.getItemList().removeIf(p -> p.getId()==itemId);
    	order.updateCost();
    	storeRepo.save(store);
    	// prepare model for view
    	model.addAttribute("order", order);
    	// choose view
    	return "form/orderDetail";
    }   
    


    
}
