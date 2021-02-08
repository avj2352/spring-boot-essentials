package com.udemy.spring.essential.intro.sorting;

import com.udemy.spring.essential.intro.interfaces.Sortable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuickSortAlgorithm implements Sortable {

    Logger logger = LoggerFactory.getLogger(QuickSortAlgorithm.class);
    
    public int [] sort (int[] arr) {
        System.out.println("Implementing Quick Sort Algorithm");
        return arr;
    }
    
}
