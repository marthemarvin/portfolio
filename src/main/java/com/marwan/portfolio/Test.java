package com.marwan.portfolio;

import java.util.ArrayList;

public class Test {


    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("strawberry");
        fruits.add("grapes");

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);

        printFirstItem(fruits);
        printFirstItem(nums);
    }


    public static <T> void printFirstItem(ArrayList<T> incomingArray){
        System.out.println(incomingArray.getFirst());
    }

}
