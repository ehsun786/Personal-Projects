package eMarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// THIS CLASS NEEDS TO BE CONFIGURED FOR OBVIOUS REASONS

@Configuration
public class DbConfig {

	private String USERNAME = "root";
	private String PASSWORD = "password"; 
	
	// from campus
    private String HOST = "localhost";
    private int PORT = 3306;
	
    @Bean
    public DriverManagerDataSource dataSource() {		
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        // jdbc:mysql://host:port/db
        ds.setUrl("jdbc:mysql://" + HOST + ":" + PORT + "/" + USERNAME );
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }
}



