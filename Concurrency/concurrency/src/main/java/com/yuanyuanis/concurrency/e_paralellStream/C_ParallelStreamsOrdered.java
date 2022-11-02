package com.yuanyuanis.concurrency.e_paralellStream;

import java.util.List;

public class C_ParallelStreamsOrdered {
	
	

	public static void main(String[] args) {
		System.out.println(List.of('w', 'o', 'l', 'f') 
				.parallelStream()
				.reduce("",
				    (s1,c) -> s1 + c,
				    (s2,s3) -> s2 + s3)); // wolf

	}

}
