package com.yomean.carbooking.controller;

import com.yomean.carbooking.service.CarBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class CarBookingController {

    @Autowired
    private CarBookingService carBookingService;


}
