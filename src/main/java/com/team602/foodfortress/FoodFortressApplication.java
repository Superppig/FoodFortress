package com.team602.foodfortress;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//自动扫描mapper包
@MapperScan("com/team602/foodfortress/mapper")
public class FoodFortressApplication {
	public static void main(String[] args) {
		SpringApplication.run(FoodFortressApplication.class, args);
	}

}
