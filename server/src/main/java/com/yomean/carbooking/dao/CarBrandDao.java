package com.yomean.carbooking.dao;

import com.yomean.carbooking.bean.CarBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarBrandDao {

    int addBrand(CarBrand brand);

    CarBrand getById(Long id);

    List<CarBrand> getAll();
}
