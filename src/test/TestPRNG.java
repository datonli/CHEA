package test;

import mop.PRNG;


public class TestPRNG {
	public static void main(String[] args) {
		for (int i = 0 ; i < 100; i ++)
		System.out.println((int)PRNG.nextDouble(0,1)*1000);
	}
}
