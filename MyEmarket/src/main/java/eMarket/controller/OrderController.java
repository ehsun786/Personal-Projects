package eMarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import eMarket.EMarketApp;
import eMarket.domain.Order;
import eMarket.domain.Store;
import eMarket.repository.OrderRepository;
import eMarket.repository.StoreRepository;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired StoreRepository storeRepo;
	@Autowired OrderRepository orderRepo;

    @RequestMapping("/")
    public String index(Model model) {
    	// fetch info from repository
    	Store s = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// prepare model for view
    	model.addAttribute("orderList", s.getOrderList());
    	// choose view
        return "form/orderMaster";
    }
   
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String orderDetail(@ModelAttribute("order") Order order, @RequestParam(value="orderId", required=false, defaultValue="-1") int orderId) {
    	// fetch info from repository
    	Store store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// backend logic and prepare model for view
    	if (orderId > -1) {
	    	Order orderAux = store.getOrderList().stream().filter(o -> o.getId()==orderId).findFirst().orElse(null);
	    	order.setId(orderAux.getId());
	    	order.setDate(orderAux.getDate());
	    	order.setItemList(orderAux.getItemList());
	    	order.setUser(orderAux.getUser());
	    	order.setCost(orderAux.getCost());
	    	System.out.println(order.toString());
    	} else {
    		// new instance
    		store.getOrderList().add(order);
    		Order savedOrder = orderRepo.save(order);
    		order.setId(savedOrder.getId());
    	}
    	storeRepo.save(store);

    	// choose view
    	return "form/orderDetail";
    }   

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="orderId", required=false, defaultValue="-1") int orderId, Model model) {
    	// fetch info from repository
    	Store store = storeRepo.findByName(EMarketApp.STORE_NAME).get(0);
    	// backend logic
    	store.getOrderList().removeIf(p -> (p.getId() == orderId));
    	storeRepo.save(store);
    	// prepare model for view
    	model.addAttribute("orderList", store.getOrderList());
    	// choose view
    	return "form/orderMaster";
    }   
   

    
}
