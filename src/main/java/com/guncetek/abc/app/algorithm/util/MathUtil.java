package com.guncetek.abc.app.algorithm.util;

public class MathUtil {

    public static float generateRandomValue(int low,int high){
        return (float) (low+Math.random()*(high-low));
    }

}
