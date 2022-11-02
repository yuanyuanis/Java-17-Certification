package com.yuanyuanis.concurrency.e_paralellStream;

import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Stream;

public class D_CombiningResultsCollect {

	public static void main(String[] args) {
		
	    Stream<String> stream = Stream.of("w", "o", "l", "f").parallel(); 
	    SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,
	        Set::add,
	        Set::addAll);
	    System.out.println(set); // [f, l, o, w]

	}

}
