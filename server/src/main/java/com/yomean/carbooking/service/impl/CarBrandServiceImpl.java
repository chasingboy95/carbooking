package com.yomean.carbooking.service.impl;

import com.yomean.carbooking.bean.CarBrand;
import com.yomean.carbooking.dao.CarBrandDao;
import com.yomean.carbooking.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandDao carBrandDao;

    public boolean addBrand(CarBrand brand) {
        return carBrandDao.addBrand(brand) > 0;
    }

    public CarBrand getById(Long id) {
        return carBrandDao.getById(id);
    }

    public List<CarBrand> list() {
        return carBrandDao.getAll();
    }
}
