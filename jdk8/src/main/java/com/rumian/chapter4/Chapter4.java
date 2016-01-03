package com.rumian.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.rumian.chapter4.Dish.Type;

public class Chapter4 {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Type.MEAT),
            new Dish("beef", false, 700, Type.MEAT),
            new Dish("chicken", false, 400, Type.MEAT),
            new Dish("french fries", true, 530, Type.OTHER),
            new Dish("rice", true, 350, Type.OTHER),
            new Dish("season fruit", true, 120, Type.OTHER),
            new Dish("pizza", true, 550, Type.OTHER),
            new Dish("prawns", false, 300, Type.FISH),
            new Dish("salmon", false, 450, Type.FISH)
        );

    public void test1() {
        // [pork, beef, chicken, french fries, rice, pizza, salmon]

        List<String> threeHighCaloricDishNames = 
                menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .map(Dish::getName)
                    .limit(3) // [pork, beef, chicken]
                    .collect(Collectors.toList());
        
        System.out.println(threeHighCaloricDishNames);
    }
    
    
    public void test2() {
        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println); // title 정상 출력
        s.forEach(System.out::println); // Exception in thread "main" java.lang.IllegalStateException: 
        //stream has already been operated upon or closed
        
    }
    

    public void test3() {
        List<Dish> newMenu = new ArrayList<>();
        
        menu.forEach(d -> {
            if (d.getCalories() > 500) {
                newMenu.add(d);
            }
        });
        
        System.out.println(newMenu);
        
        newMenu.forEach(d -> {
            d.setName("new_" + d.getName());
        });
        System.out.println(newMenu);
    }
    
    
    public void test4() {
        List<String> names = 
                menu.stream()
                    .filter(d -> {
                        System.out.println("filtering : " + d.getName());
                        return d.getCalories() > 300;
                    })
                    .map(d -> {
                        System.out.println("mapping : " + d.getName());
                        return d.getName();
                    })
                    .limit(3)
                    .collect(Collectors.toList());
        System.out.println(names);
    }
    
    
    public void test5() {
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        
        List<int[]> pairs = num1.stream()
                                .flatMap(i -> num2.stream()
                                                  .filter(j -> (i + j) % 3 == 0)
                                                  .map(j -> new int[]{i, j})
                                        )
                                .collect(Collectors.toList());
        //System.out.println(pairs);
        pairs.forEach(d -> {
            System.out.println(d[0] + "," + d[1]);
        });
    }
    
    
    public void test6() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }
        System.out.println(sum);
        
        sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        
        List<Integer> num = new ArrayList<>();
        Optional<Integer> sum2 = num.stream().reduce(Integer::sum);
        System.out.println(sum2);
    }
    
    public static void main(String[] args) {
        Chapter4 ch = new Chapter4();
        //ch.test1();
        //ch.test2();
        //ch.test3();
        //ch.test4();
        //ch.test5();
        ch.test6();
    }
}
