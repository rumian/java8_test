package com.rumian.chapter6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.*;

public class Grouping {

    public static final List<Dish> menu =
            Arrays.asList( new Dish("pork", false, 800, Dish.Type.MEAT),
                           new Dish("beef", false, 700, Dish.Type.MEAT),
                           new Dish("chicken", false, 400, Dish.Type.MEAT),
                           new Dish("french fries", true, 530, Dish.Type.OTHER),
                           new Dish("rice", true, 350, Dish.Type.OTHER),
                           new Dish("season fruit", true, 120, Dish.Type.OTHER),
                           new Dish("pizza", true, 550, Dish.Type.OTHER),
                           new Dish("prawns", false, 400, Dish.Type.FISH),
                           new Dish("salmon", false, 450, Dish.Type.FISH));
    
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
    
    public enum CaloricLevel { DIET, NORMAL, FAT }
    
    public static void test1() {
//        menu.stream().forEach(dish -> {
//            System.out.println(dish.getCalories());
//        });

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = 
                menu.stream()
                    .collect(
                        groupingBy(
                                dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    }
                                    return CaloricLevel.FAT;
                                }
                        )
                    );
        System.out.println(dishesByCaloricLevel);
    }
    
    public static void test2() {
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel
        = menu.stream()
              .collect(
                      groupingBy(Dish::getType, 
                              groupingBy(dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return CaloricLevel.NORMAL;
                                    }
                                    return CaloricLevel.FAT;
              })));
        System.out.println(dishesByTypeCaloricLevel);
    }
    
    public static void test3() {
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
        
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                    .collect(groupingBy(Dish::getType,
                            maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(mostCaloricByType);
        
        Map<Dish.Type, Dish> mostCaloricByType2 = 
                menu.stream()
                    .collect(
                            groupingBy(Dish::getType,
                                    collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)
                                    )
                            );
        System.out.println(mostCaloricByType2);
    }
}
