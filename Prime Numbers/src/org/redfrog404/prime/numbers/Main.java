package org.redfrog404.prime.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Main {

	static CountDownLatch latch = new CountDownLatch(10);

//	static BigInteger number = new BigInteger(
//			"10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	static BigInteger number = new BigInteger("10000");

	private static long start = 0L;

	public static List<BigInteger> numbers = new ArrayList<BigInteger>();

	public static void main(String[] args) {
		start = System.currentTimeMillis();

		for (int i = 0; i < 10; i++) {
			NumberFinder finder = new NumberFinder(
					number.multiply(new BigInteger(Integer.toString(i))),
					number.multiply(new BigInteger(Integer.toString(i + 1))));
			finder.start();
		}

		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long time = (long) ((System.currentTimeMillis() - start) / 1000.0);

		numbers.remove(0);

		Collections.sort(numbers);

		for (int i = 0; i < numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}

		System.out.println("Time elapsed while finding prime numbers: " + time
				+ " seconds");
	}

}
