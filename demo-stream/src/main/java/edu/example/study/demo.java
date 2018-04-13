package edu.example.study;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Taxz on 2018/4/11.
 */
public class demo {
    public static void main(String args[]){
        //构造流
        /*
        //1. Individual values
        Stream stream = Stream.of("a", "b", "c");

        //2. Arrays
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Arrays.stream(strArray);
        stream = Stream.of(strArray);

        //3. Collections
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();


        //数值流构造
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::print);
        IntStream.range(1, 3).forEach(System.out::print);
        IntStream.rangeClosed(1, 3).forEach(System.out::print);

        //流转化为其他数据结构
        String[] array = (String[]) stream.toArray(String[]::new);
        List<String> list1 = (List<String>) stream.collect(Collectors.toCollection(ArrayList::new));
        list1 = (List<String>) stream.collect(Collectors.toList() );
        Set set = (Set) stream.collect(Collectors.toSet());
        Stack stack = (Stack) stream.collect(Collectors.toCollection(ArrayList::new));
        String str = stream.collect(Collectors.joining()).toString();
        */
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        inputStream.flatMap((childList) -> childList.stream()).filter(p -> p%2 == 0).forEach(p -> System.out.println(p));
        Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3).peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase).peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
    }
}
