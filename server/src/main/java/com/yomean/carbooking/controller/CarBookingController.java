package com.yomean.carbooking.controller;

import com.yomean.carbooking.bean.CarBookingOrder;
import com.yomean.carbooking.common.ReturnMessage;
import com.yomean.carbooking.service.CarBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class CarBookingController {

    @Autowired
    private CarBookingService carBookingService;

    @PostMapping
    public ReturnMessage<Void> addBookingOrder(@RequestBody CarBookingOrder carBookingOrder) {
        carBookingService.addBookingOrder(carBookingOrder);
        return ReturnMessage.success();
    }

    @PutMapping
    public ReturnMessage<Void> updateBookingOrder(@RequestBody CarBookingOrder carBookingOrder) {
        carBookingService.updateBookingOrder(carBookingOrder);
        return ReturnMessage.success();
    }

    @GetMapping("/{id}/bill")
    public ReturnMessage<Double> getOrderBill(@PathVariable("id") Long id) {
        return ReturnMessage.success(carBookingService.getBillById(id));
    }

    @GetMapping("/finish")
    public ReturnMessage<Void> finishOrder(@PathVariable("id") Long id) {
        carBookingService.finishRentalOrder(id);
        return ReturnMessage.success();
    }
}
