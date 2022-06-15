package com.yomean.carbooking.dao;

import com.yomean.carbooking.bean.Car;
import com.yomean.carbooking.bean.condition.CarQC;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarDao {

    int addCar(Car car);

    int updateCar(Car car);

    List<Car> query(CarQC qc);

    Car getByIdInShareMode(Long id);
}
