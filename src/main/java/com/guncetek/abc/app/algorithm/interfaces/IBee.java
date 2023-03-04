package com.guncetek.abc.app.algorithm.interfaces;

import com.guncetek.abc.app.algorithm.entities.FoodPoint;

public interface IBee {
     FoodPoint generateCandidateSolution(int low, int high);
     void goToNewFoodPoint();
     boolean isTryCountHigherThanLimit(int limit);
     int getParamSize();
     void incTryCount();

}
