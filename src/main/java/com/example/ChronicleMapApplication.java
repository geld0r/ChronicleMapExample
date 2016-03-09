package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChronicleMapApplication implements CommandLineRunner
{
	@Autowired
	private MyMap map;
	
    public static void main(String[] args) {
        SpringApplication.run(ChronicleMapApplication.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception
	{
		System.out.println(map.get("test"));
		map.put("test", "value");
		System.out.println(map.get("test"));
	}

	@Bean
    public MyMap myMap()
    {
    	return new MyMap();
    }
}
