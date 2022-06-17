package com.yomean.carbooking.controller;

import com.yomean.carbooking.bean.CarBookingOrder;
import com.yomean.carbooking.bean.vo.CarBookingOrderVo;
import com.yomean.carbooking.common.ReturnMessage;
import com.yomean.carbooking.service.CarBookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@Api(value = "CarBookingController", tags = "car booking controller")
public class CarBookingController {

    @Autowired
    private CarBookingService carBookingService;

    @PostMapping
    @ApiOperation("new booking order")
    public ReturnMessage<Void> addBookingOrder(@RequestBody CarBookingOrder carBookingOrder) {
        carBookingService.addBookingOrder(carBookingOrder);
        return ReturnMessage.success();
    }

    @PutMapping
    @ApiOperation("update booking order")
    public ReturnMessage<Void> updateBookingOrder(@RequestBody CarBookingOrder carBookingOrder) {
        carBookingService.updateBookingOrder(carBookingOrder);
        return ReturnMessage.success();
    }

    @GetMapping("/{id}/bill")
    @ApiOperation("get booking order's bill")
    public ReturnMessage<Double> getOrderBill(@PathVariable("id") Long id) {
        return ReturnMessage.success(carBookingService.getBillById(id));
    }

    @GetMapping("/list")
    @ApiOperation("get booking order list")
    public ReturnMessage<List<CarBookingOrderVo>> getOrderList(Long userId) {
        return ReturnMessage.success(carBookingService.getOrderVoByUserId(userId));
    }

    @PutMapping("/{id}/finish")
    @ApiOperation("return car, finish order")
    public ReturnMessage<Void> finishOrder(@PathVariable("id") Long id) {
        carBookingService.finishRentalOrder(id);
        return ReturnMessage.success();
    }
}
