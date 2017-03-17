package eMarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import eMarket.domain.Role;
import eMarket.domain.Store;
import eMarket.domain.User;
import eMarket.repository.StoreRepository;
import eMarket.repository.UserRepository;

@SpringBootApplication
public class EMarketApp implements CommandLineRunner { 

	@Autowired 
	private StoreRepository repo;
	@Autowired
	private UserRepository userRepo;	
    
	public static final int ROLE_MANAGER = 1;
	public static final int ROLE_DEVELOPER = 2;

	public static final String STORE_NAME = "MyEMarket";
	
    public static void main(String[] args) {
        SpringApplication.run(EMarketApp.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
		if (repo.findByName(STORE_NAME).size() == 0) {
			Store store = new Store(STORE_NAME);
			repo.save(store);
		}
		
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();

		User user = new User();
		user.setLogin("Alice");
		user.setPassword(pe.encode("password"));
		Role role = new Role();
		role.setId(ROLE_DEVELOPER);
		role.setRole("DEVELOPER");
		user.setRole(role);
		userRepo.save(user);
		
		user = new User();
		user.setLogin("Bob");
		user.setPassword(pe.encode("admin"));
		role = new Role();
		role.setId(ROLE_MANAGER);
		role.setRole("PROJECT_MANAGER");
		user.setRole(role);
		userRepo.save(user);
    }   
    
}
