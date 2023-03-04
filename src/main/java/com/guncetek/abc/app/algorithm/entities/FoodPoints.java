package com.guncetek.abc.app.algorithm.entities;

import com.guncetek.abc.app.algorithm.interfaces.IPopulation;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FoodPoints extends Algorithm implements IPopulation {

    private List<FoodPoint> foodPoints;

    public FoodPoints(List<FoodPoint> foodPoints) {
        super(100, -1, 1);
        validateArgument(foodPoints);
        this.foodPoints = foodPoints;
    }

    public FoodPoints(List<FoodPoint> foodPoints, int limit, int candidateLowNumber, int candidateHighNumber){
        super(limit,candidateLowNumber,candidateHighNumber);
         validateArgument(foodPoints);
        this.foodPoints = foodPoints;
    }

    private void validateArgument(List<FoodPoint> foodPoints){
        if(foodPoints ==null && getPopulationSize()==0)
            throw new IllegalArgumentException();
    }

    public List<FoodPoint> getFoodPoints() {
        return foodPoints;
    }

    private List<Float> calculateProbabilitiesForPoints(){
        final float totalOfFitValues= foodPoints.stream().map(bee -> Float.valueOf(FoodPoint.calculateFitValue(bee)))
                .reduce(0.0f,(a,b)->a+b);
        return foodPoints.stream().map(foodPoint -> Float.valueOf(FoodPoint.calculateFitValue(foodPoint)/totalOfFitValues)).collect(Collectors.toList());
    }

    public int getPopulationSize(){
        return foodPoints.size();
    }

    private float calculateProbability(FoodPoint foodPoint, float totalOfFitValues) {
        return FoodPoint.calculateFitValue(foodPoint)/totalOfFitValues;
    }

    @Override
    public void runEmployeeBee() {
        for (int i = 0; i < getPopulationSize(); i++) {
            short maxCount=1;
            FoodPoint firstFoodPoint = foodPoints.get(i);
            short iteration=0;
            while(true && iteration<10) {
                FoodPoint candidateFoodPoint = firstFoodPoint.generateCandidateSolution(getCandidateLowNumber(),getCandidateHighNumber());

                if(FoodPoint.calculateFitValue(candidateFoodPoint)> FoodPoint.calculateFitValue(firstFoodPoint)){
                    foodPoints.set(i, candidateFoodPoint);
                    break;
                }else{
                    firstFoodPoint.incTryCount();
                }

                float delta=FoodPoint.calculateFitValue(candidateFoodPoint)-FoodPoint.calculateFitValue(firstFoodPoint);
                if(Math.abs(delta)>10){
                    break;
                }
                iteration++;
            }
        }
    }

    @Override
    public void runLookoutBee() {
        List<Float> points =calculateProbabilitiesForPoints();
        int iteration=0;
        while (iteration!=points.size()){
            int innerIteration=0;
            if(Math.random()<points.get(innerIteration)){
                FoodPoint foodPoint=foodPoints.get(innerIteration);
                FoodPoint candidateSolution=foodPoint.generateCandidateSolution(getCandidateLowNumber(),getCandidateHighNumber());
                if(FoodPoint.calculateFitValue(candidateSolution)> FoodPoint.calculateFitValue(foodPoint)) {
                    foodPoints.set(innerIteration, candidateSolution);
                }else {
                    foodPoint.incTryCount();
                }
                iteration++;
            }
            if(innerIteration==points.size()-1)
                innerIteration=0;
            else
                innerIteration++;
        }

    }

    @Override
    public void runExplorerBee() {
       FoodPoint maxTryFoodPoint = foodPoints.stream().sorted(new Comparator<FoodPoint>() {
           @Override
           public int compare(FoodPoint o1, FoodPoint o2) {
               int tryCountFirst=o1.getTryCount();
               int tryCountLast=o2.getTryCount();
               return Integer.valueOf(tryCountLast).compareTo(Integer.valueOf(tryCountFirst));
           }
       }).findFirst().orElse(null);

        if(maxTryFoodPoint ==null)
            throw new IllegalStateException();

        if(maxTryFoodPoint.isTryCountHigherThanLimit(getLimit())){
            maxTryFoodPoint.goToNewFoodPoint();
            maxTryFoodPoint.setTryCount(0);
        }

    }
}
