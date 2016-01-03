package com.rumian.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Partition {

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
        System.out.println(isPrime(3));
        System.out.println(isPrime2(3));
        System.out.println(partitionPrimes(30));
    }
    
    public static void test1() {
        Map<Boolean, List<Dish>> partitionsMenu = 
                menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionsMenu);
        
        List<Dish> vegetarianDishes = partitionsMenu.get(true);
        System.out.println(vegetarianDishes);
    }
    
    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
                        .noneMatch(i -> {
                            System.out.println(i + " ? " + candidate);
                            return candidate % i == 0;
                        });
    }
    
    public static boolean isPrime2(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        
        return IntStream.rangeClosed(2, candidateRoot)
                        .noneMatch(i -> candidate % i == 0);
    }
    
    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                        .collect(partitioningBy(candidate -> isPrime(candidate)));
    }
}
