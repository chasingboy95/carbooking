package com.yomean.carbooking.controller;

import com.github.pagehelper.PageInfo;
import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.condition.CarQC;
import com.yomean.carbooking.common.PageParam;
import com.yomean.carbooking.common.ReturnMessage;
import com.yomean.carbooking.service.CarBookingService;
import com.yomean.carbooking.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car")
@Api(value = "CarController", tags = "car manage controller")
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarBookingService carBookingService;

    @PostMapping
    @ApiOperation("add car")
    public ReturnMessage<Void> addCar(@RequestBody Car car) {
        carService.addCar(car);
        return ReturnMessage.success();
    }

    @PutMapping
    @ApiOperation("update car")
    public ReturnMessage<Void> updateCar(@RequestBody Car car) {
        carService.updateCar(car);
        return ReturnMessage.success();
    }

    @GetMapping("/list")
    @ApiOperation("query car")
    public ReturnMessage<PageInfo<Car>> queryCars(CarQC qc, PageParam page) {
        return ReturnMessage.success(carService.queryCars(qc, page));
    }

    @GetMapping("/{id}/booked/date")
    @ApiOperation("query car booked date")
    public ReturnMessage<List<LocalDate>> queryBookedDate(@PathVariable("id") Long id) {
        return ReturnMessage.success(carBookingService.getBookedData(id));
    }
}
