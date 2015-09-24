package org.redfrog404.prime.numbers;

import java.math.BigInteger;

public class NumberFinder extends Thread {

	BigInteger number = new BigInteger("0");
	BigInteger end_number = new BigInteger("0");
	int completed;

	public NumberFinder(BigInteger number, BigInteger end_number) {
		this.number = number;
		this.end_number = end_number;
	}

	public void run() {
		while (number.compareTo(end_number) == -1) {
			if (isPrime(number)) {
				System.err.println("Prime number: " + number);
				Main.numbers.add(number);
			}

			number = number.add(BigInteger.ONE);
		}
		
		Main.latch.countDown();

	}

	public boolean isPrime(BigInteger number) {
		BigInteger two = new BigInteger("2");

		for (BigInteger i = BigInteger.ONE; i.compareTo(number
				.divideAndRemainder(two)[0].add(BigInteger.ONE)) == -1; i = i
				.add(BigInteger.ONE)) {

			if (i.compareTo(BigInteger.ONE) != 0) {
				if (number.divideAndRemainder(i)[1] == BigInteger.ZERO) {
					return false;
				}
			}

		}

		return true;
	}

}
