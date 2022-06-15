package com.yomean.carbooking.service;

import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.condition.CarQC;
import com.yomean.carbooking.dao.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    @Autowired
    private CarDao carDao;

    public boolean addCar(Car car) {
        return carDao.addCar(car) > 0;
    }

    public boolean updateCar(Car car){
        if (Objects.isNull(car.getId())){
            throw new RuntimeException();
        }
        return carDao.updateCar(car)>0;
    }

    public List<Car> queryCars(CarQC qc){
        return carDao.query(qc);
    }

    public Car getById(Long id){
        return carDao.getByIdInShareMode(id);
    }
}
