package com.udemy.spring.essential.intro.sorting;

import com.udemy.spring.essential.intro.interfaces.Sortable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BubbleSortAlgorithm implements Sortable {

    Logger logger = LoggerFactory.getLogger(BubbleSortAlgorithm.class);
    
    public int [] sort (int[] arr) {
        System.out.println("Implementing Bubble Sort Algorithm");
        return arr;
    }
    
}
