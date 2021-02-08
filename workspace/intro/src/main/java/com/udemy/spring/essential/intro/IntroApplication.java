package com.udemy.spring.essential.intro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class IntroApplication {

	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(IntroApplication.class, args);
		
		// Binary Search Implementation
		// BinarySearchImpl binarySearch = new BinarySearchImpl(new BubbleSortAlgorithm());

		// Application Context - which will maintain all the beans
		BinarySearchImpl binarySearch = appContext.getBean(BinarySearchImpl.class);

		int result = binarySearch.binarySearch(new int[] {1,2,3}, 3);
		System.out.println("Completed run: " + result);
	}

}
