package com.guncetek.abc;

import com.guncetek.abc.app.algorithm.entities.FoodPoint;
import com.guncetek.abc.app.algorithm.entities.FoodPoints;
import com.guncetek.abc.app.algorithm.util.Populations;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<FoodPoint> foodPointList = Populations.newFoodPoints(500,2);
        FoodPoints foodPoints =new FoodPoints(foodPointList,5,-5,5);
        long firstTime=System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
         foodPoints.runEmployeeBee();
         foodPoints.runLookoutBee();
         foodPoints.runExplorerBee();
        }
        System.out.println(System.currentTimeMillis()-firstTime);
        foodPoints.getFoodPoints().stream().sorted(new Comparator<FoodPoint>() {
            @Override
            public int compare(FoodPoint o1, FoodPoint o2) {
                return Float.valueOf(FoodPoint.calculateObjectiveValue(o1)).compareTo(Float.valueOf(FoodPoint.calculateObjectiveValue(o2)));
            }
        }).map(bee -> Float.valueOf(FoodPoint.calculateObjectiveValue(bee)))
                .forEach(value->System.out.println(value));

        System.out.println(foodPoints.getFoodPoints().stream().map(bee -> Float.valueOf(FoodPoint.calculateObjectiveValue(bee)))
        .reduce((a,b)->a+b).get()/foodPoints.getFoodPoints().size());


    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent= FXMLLoader.load(Main.class.getResource("/Views/main.fxml"));
        primaryStage.setTitle("Yapay Arı Kolonisi Uygulaması");
        Scene scene=new Scene(parent,500,500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }*/
}
