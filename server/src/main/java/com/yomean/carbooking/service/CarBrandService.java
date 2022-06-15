package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.CarBrand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarBrandService {

    boolean addBrand(CarBrand brand);

    CarBrand getById(Long id);

    List<CarBrand> list();
}
