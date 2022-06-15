package com.yomean.carbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class CarBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarBookingApplication.class, args);
    }

}
