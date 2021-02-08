package com.udemy.spring.essential.intro;

import com.udemy.spring.essential.intro.interfaces.Sortable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BinarySearchImpl {

    Logger logger = LoggerFactory.getLogger(BinarySearchImpl.class);

    @Autowired
    Sortable sortAlgorithm;
    
    
    public BinarySearchImpl (Sortable algorithm) {
        this.sortAlgorithm = algorithm;
    }

    public int binarySearch(int [] arr, int num) {

        // Sort the Arr
        logger.info("Sorting the array: " + arr);
        sortAlgorithm.sort(arr);

        // Binary Search

        // Return the number
        return num;
    }
    
}
