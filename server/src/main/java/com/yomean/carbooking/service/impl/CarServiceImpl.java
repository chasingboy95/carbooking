package com.yomean.carbooking.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.condition.CarQC;
import com.yomean.carbooking.common.PageParam;
import com.yomean.carbooking.dao.CarDao;
import com.yomean.carbooking.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    public boolean addCar(Car car) {
        return carDao.addCar(car) > 0;
    }

    public boolean updateCar(Car car) {
        if (Objects.isNull(car.getId())) {
            throw new RuntimeException();
        }
        return carDao.updateCar(car) > 0;
    }

    public PageInfo<Car> queryCars(CarQC qc, PageParam page) {
        PageHelper.startPage(page.getCurPage(), page.getPageSize());
        PageInfo<Car> pageInfo = new PageInfo<>(carDao.query(qc));
        return pageInfo;
    }

    public Car getById(Long id) {
        return carDao.getById(id);
    }

    public Car getByIdWithLock(Long id) {
        return carDao.getByIdWithLock(id);
    }
}
