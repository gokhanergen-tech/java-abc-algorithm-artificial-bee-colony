package com.guncetek.abc.app.algorithm.util;

import com.guncetek.abc.app.algorithm.entities.FoodPoint;

import java.util.ArrayList;
import java.util.List;

public class Populations {

    public static List<FoodPoint> newFoodPoints(int capacity, int beeParamSize){
        if(capacity<=0 && beeParamSize<=0)
            throw new IllegalArgumentException("Parameters must not be zero or less than zero");
        List<FoodPoint> foodPointList =new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            foodPointList.add(FoodPoint.generateRandomPoint(beeParamSize));
        }
        return foodPointList;
    }

}
