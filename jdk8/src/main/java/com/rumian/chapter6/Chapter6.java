package com.rumian.chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Chapter6 {
    public static List<Transaction> transactions = 
            Arrays.asList( new Transaction(Currency.EUR, 1500.0),
                            new Transaction(Currency.USD, 2300.0),
                            new Transaction(Currency.GBP, 9900.0),
                            new Transaction(Currency.EUR, 1100.0),
                            new Transaction(Currency.JPY, 7800.0),
                            new Transaction(Currency.CHF, 6700.0),
                            new Transaction(Currency.EUR, 5600.0),
                            new Transaction(Currency.USD, 4500.0),
                            new Transaction(Currency.CHF, 3400.0),
                            new Transaction(Currency.GBP, 3200.0),
                            new Transaction(Currency.USD, 4600.0),
                            new Transaction(Currency.JPY, 5700.0),
                            new Transaction(Currency.EUR, 6800.0) );
    
    public static class Transaction {
        private final Currency currency;
        private final double value;

        public Transaction(Currency currency, double value) {
            this.currency = currency;
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public double getValue() {
            return value;
        }

        @Override
        public String toString() {
            return currency + " " + value;
        }
    }

    public enum Currency {
        EUR, USD, JPY, GBP, CHF
    }

    
    public static void main(String[] args) {
        test1();
        test2();
        counting();
        maxValue();
        minValue();
        summingDouble();
    }
    
    public static void test1() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        
        System.out.println(transactionsByCurrencies);
    }
    
    public static void test2() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = 
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }
    
    public static void counting() {
        long howMany = transactions.stream().filter(transaction -> transaction.currency == Currency.JPY).collect(Collectors.counting());
        System.out.println(howMany);
    }
    
    
    public static void maxValue() {
        Comparator<Transaction> compareValue = Comparator.comparingDouble(Transaction::getValue);
        Optional<Transaction> max = transactions.stream().collect(Collectors.maxBy(compareValue));
        
        System.out.println(max.get().value);
        
    }
    
    public static void minValue() {
        Comparator<Transaction> compareValue = Comparator.comparingDouble(Transaction::getValue);
        Optional<Transaction> max = transactions.stream().collect(Collectors.minBy(compareValue));
        
        System.out.println(max.get().value);
    }
    
    public static void summingDouble() {
        double total = transactions.stream().collect(Collectors.summingDouble(Transaction::getValue));
        System.out.println(total);
        
        double average = transactions.stream().collect(Collectors.averagingDouble(Transaction::getValue));
        System.out.println(average);
        
        DoubleSummaryStatistics stat = transactions.stream().collect(Collectors.summarizingDouble(Transaction::getValue));
        System.out.println(stat);
    }
}
