package com.osm.wallet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages="com.osm.wallet.api")
public class WalletSystemApplication  {

	public static void main(String[] args) {
		SpringApplication.run(WalletSystemApplication.class, args);
	}


//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//		return builder.sources(WalletSystemApplication.class);
//	}

}
