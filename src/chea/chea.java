package chea;

import java.io.IOException;

import mop.MOP;
import mop.CHEAMOP;
import problems.AProblem;
import problems.DTLZ1;
import problems.DTLZ2;


class chea {
		public static void main(String[] args) {
			int popSize = 406;
			int hyperplaneIntercept = 1;
			int iterations = 400;
			
			AProblem problem = DTLZ1.getInstance();
			MOP mop = CHEAMOP.getInstance(popSize,problem,hyperplaneIntercept);
			mop.initial();
			for(int i = 0 ; i < iterations; i ++) {
				System.out.println("The " + i "th iteration !!");
				mop.updatePop();
			}
	        String filename = "/home/laboratory/workspace/moead_parallel/experiments/moead_new.txt";
	        mop.write2File(filename);
		    System.out.println("done!");
		}
}

