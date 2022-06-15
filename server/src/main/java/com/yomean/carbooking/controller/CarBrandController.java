package com.yomean.carbooking.controller;

import com.yomean.carbooking.bean.CarBrand;
import com.yomean.carbooking.common.ReturnMessage;
import com.yomean.carbooking.service.CarBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@Api(value = "CarBrandController", tags = "car brand manage controller")
public class CarBrandController {

    @Autowired
    private CarBrandService carBrandService;

    @PostMapping
    @ApiOperation("add car brand")
    public ReturnMessage<Void> addCarBrand(@RequestBody CarBrand carBrand) {
        carBrandService.addBrand(carBrand);
        return ReturnMessage.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("query car brand by id")
    public ReturnMessage<CarBrand> queryCarBrands(@PathVariable("id") Long id) {
        return ReturnMessage.success(carBrandService.getById(id));
    }

    @GetMapping("/list")
    @ApiOperation("list car brand")
    public ReturnMessage<List<CarBrand>> list() {
        return ReturnMessage.success(carBrandService.list());
    }
}
