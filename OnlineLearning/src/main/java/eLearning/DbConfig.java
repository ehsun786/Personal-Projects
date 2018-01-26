package eLearning;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

// THIS CLASS NEEDS TO BE CONFIGURED FOR OBVIOUS REASONS

@Configuration
public class DbConfig {

	// information about accessing the Departmental MySQL server
	// https://campus.cs.le.ac.uk/labsupport/usinglinux/mysqlaccountdetails
	private String USERNAME = "";
	private String PASSWORD = ""; // in ~/.my.cnf
	
	// from campus or travis
	
	// 127.0.0.1
	private String HOST = "mysql.mcscw3.le.ac.uk";
	private int PORT = 3306;
	
	// off-campus: after executing 
	//ssh -fNg -L 3307:mysql.mcscw3.le.ac.uk:3306 abc123@xanthus.mcscw3.le.ac.uk
	//private String HOST = "127.0.0.1";
	//private int PORT = 3306;
			
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



