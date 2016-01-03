package com.rumian.chapter5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter5 {
    private List<Transaction> transactions;
    private Trader raoul = new Trader("Raoul", "Cambridge");
    private Trader mario = new Trader("Mario", "Milano");
    private Trader alan = new Trader("Alan", "Cambridge");
    private Trader brian = new Trader("Brian", "Cambridge");
    
    public Chapter5() {
        transactions = Arrays.asList(
                    new Transaction(brian, 2011, 300),
                    new Transaction(raoul, 2012, 1000),
                    new Transaction(raoul, 2011, 400),
                    new Transaction(mario, 2012, 700),
                    new Transaction(mario, 2011, 700),
                    new Transaction(alan, 2012, 950)
                );
    }
    
    public void method1() {
        // [{Trader : Brian in Cambridge, year : 2011, value : 300}, {Trader : Raoul in Cambridge, year : 2011, value : 400}, {Trader : Mario in Milan, year : 2011, value : 700}]
        List<Transaction> results = this.transactions.stream()
                                                     .filter(t -> t.getYear() == 2011)
                                                     .sorted(Comparator.comparing(Transaction::getValue).reversed())
                                                     .collect(Collectors.toList());
        
        System.out.println(results);
    }
    
    public void method2() {
        List<String> citys = 
                this.transactions.stream()
                                 .map(Transaction::getTrader)
                                 .map(Trader::getCity)
                                 .distinct()
                                 .collect(Collectors.toList());
        System.out.println(citys);
    }
    
    public void method3() {
        List<String> names =
                this.transactions
                .stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        
        System.out.println(names);
    }
    
    public void method4() {
        boolean isMilano =
                this.transactions
                .stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milano"));
        System.out.println(isMilano);
    }
    
    // 캠브리지에 거주하는 거래자의 모든 트랜잭션 값을 출
    public void method5() {
        this.transactions
        .stream()
        .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
        .forEach(System.out::println);
    }
    
    // 전체 트랜잭션 중 최댓값
    public void method6() {
        Optional<Integer> result = 
                this.transactions
                    .stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::max);
        System.out.println(result.orElse(0));
    }
    
    // 전체 트랜잭션 중 최솟값
    public void method7() {
        Optional<Integer> result = 
                this.transactions
                    .stream()
                    .map(Transaction::getValue)
                    .reduce(Integer::min);
        System.out.println(result.orElse(0));
    }
    
    
    //피보나치 수열 만들기
    public void method8() {
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
              .limit(10)
              .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
        
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
              .limit(10)
              .map(t -> t[0])
              .forEach(t -> System.out.print(t + ", "));
    }
    
    public static void main(String[] args) {
        Chapter5 ch = new Chapter5();
        ch.method1();
        ch.method2();
        ch.method3();
        ch.method4();
        ch.method5();
        ch.method6();
        ch.method7();
        ch.method8();
    }
}
