package com.studyproject.tuberlin.bingoapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.studyproject.tuberlin.bingoapp.helpers.GameStatus;

public class Random {
  public static void main(String args[]) {
	  /*String shuffledNumbers = null;
	
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
	    System.out.println(GameStatus.ACTIVE.toString());*/
	  calldatabase();
	  test();
  }

private static void test() {
	String longestMatch = "ramesh,4";
	int idx = longestMatch.indexOf(",");
	int value = -1;
	if(idx != -1){
		value = Integer.parseInt(longestMatch.substring(idx+1,longestMatch.length()));
	}
	System.out.println(value);
	
}

private static void calldatabase() {
	// TODO Auto-generated method stub
	
}
}
