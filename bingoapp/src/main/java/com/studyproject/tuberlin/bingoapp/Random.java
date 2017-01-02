package com.studyproject.tuberlin.bingoapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Random {
  public static void main(String args[]) {
	  String shuffledNumbers = null;
	
		List<Integer> dataList = new ArrayList<Integer>();
	    for (int i = 1; i <= 75; i++) {
	      dataList.add(i);
	    }
	    Collections.shuffle(dataList);
	    for (int i = 0; i < dataList.size(); i++) {
	    	shuffledNumbers = shuffledNumbers+"," + dataList.get(i);
	    }
	    int index = shuffledNumbers.indexOf(",");
	    shuffledNumbers = shuffledNumbers.substring(index+1, shuffledNumbers.length());
	    System.out.println(shuffledNumbers);
  }
}
