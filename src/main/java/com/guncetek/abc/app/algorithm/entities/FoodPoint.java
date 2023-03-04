package com.guncetek.abc.app.algorithm.entities;

import com.guncetek.abc.app.algorithm.interfaces.IBee;
import com.guncetek.abc.app.algorithm.util.MathUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FoodPoint implements IBee {

    private Float[] foodCoordinates;

    private int tryCount;

    public FoodPoint(Float[] foodCoordinates) {
        if(foodCoordinates==null)
            throw new IllegalArgumentException();
        this.foodCoordinates = foodCoordinates;
        this.tryCount = 0;
    }

    public Float[] getFoodCoordinates() {
        return foodCoordinates;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    @Override
    public FoodPoint generateCandidateSolution(int low, int high) {
        int attributeIndexFirst=selectRandomAttribute(-1);
        int attributeIndexLast=selectRandomAttribute(attributeIndexFirst);

        Float[] candidateFoodCoordinate=foodCoordinates.clone();

        float valueFirst=candidateFoodCoordinate[attributeIndexFirst];
        float valueLast=candidateFoodCoordinate[attributeIndexLast];

        valueFirst=valueFirst+MathUtil.generateRandomValue(low,high)*(valueFirst-valueLast);
        candidateFoodCoordinate[attributeIndexFirst]=valueFirst;

        FoodPoint foodPoint =new FoodPoint(candidateFoodCoordinate);
        return foodPoint;
    }

    @Override
    public void goToNewFoodPoint() {
        for (int i = 0; i < foodCoordinates.length; i++) {
            foodCoordinates[i]=MathUtil.generateRandomValue(0,1);
        }
    }

    @Override
    public boolean isTryCountHigherThanLimit(int limit) {
        return tryCount>limit;
    }

    @Override
    public int getParamSize() {
        return foodCoordinates.length;
    }

    @Override
    public void incTryCount() {
        tryCount+=1;
    }

    public static FoodPoint generateRandomPoint(int paramSize){
        Float[] coordinates=new Float[paramSize];
        for (int i = 0; i < paramSize; i++) {
            coordinates[i]= MathUtil.generateRandomValue(100,400);
        }

        return new FoodPoint(coordinates);
    }

    public static float calculateObjectiveValue(FoodPoint foodPoint){
        if(foodPoint ==null)
            throw new NullPointerException();
        return Arrays.stream(foodPoint.getFoodCoordinates()).map(value->value*value)
                .reduce(0.0f,(a,b)->a+b);
    }

    public static float calculateFitValue(FoodPoint foodPoint) {
            if(foodPoint ==null)
                throw new NullPointerException();
            float objectiveValue=calculateObjectiveValue(foodPoint);
            return objectiveValue>0?(1/(1+objectiveValue)):(1+Math.abs(objectiveValue));
    }

    private int selectRandomAttribute(int except) {
        int paramSize=getParamSize();
        if(paramSize==1)
            return 0;
        List<Float> floats= Arrays.stream(foodCoordinates).collect(Collectors.toList());

        if(except<0 || except>=paramSize)
            return (int)Math.round(Math.random()*(paramSize-1));

        floats.remove(except);

        int index=(int)Math.round(Math.random()*(paramSize-2));

        return index>=except?index+1:index;
    }
}
