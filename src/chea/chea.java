package chea;

import java.io.IOException;

import mop.MOP;
import mop.CHEAMOP;
import problems.AProblem;
import problems.DTLZ1;
import problems.DTLZ2;


class chea {
		public static void main(String[] args) throws IOException{
			int popSize = 406;
			int hyperplaneIntercept = 27;
			int iterations = 800;
			
			AProblem problem = DTLZ2.getInstance();
			MOP mop = CHEAMOP.getInstance(popSize,problem,hyperplaneIntercept,2);
			mop.initial();
	        String filename = "/home/laboratory/workspace/moead_parallel/experiments/chea_init.txt";
	        mop.write2File(filename);
			mop.updatePop(iterations);
			/*
			for(int i = 0 ; i < iterations; i ++) {
				//System.out.println("The " + i + "th iteration !!");
				mop.updatePop(1);
			}
			*/
	        filename = "/home/laboratory/workspace/moead_parallel/experiments/chea.txt";
	        mop.write2File(filename);
		    System.out.println("done!");
		}
}

