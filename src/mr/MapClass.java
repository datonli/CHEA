package mr;

import java.io.IOException;

import chea.chea;

import mop.MopData;
import mop.MopDataPop;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import mop.MOP;
import mop.CHEAMOP;

import problems.AProblem;
import problems.ZDT1;
import problems.DTLZ1;

import utilities.WrongRemindException;

public class MapClass extends MapReduceBase implements Mapper<Object, Text, Text, Text> {

	private static int innerLoop = 1;
	
	//static MopDataPop mopData = new MopDataPop();
	
	Text keyIndex = new Text();
	Text valueInd = new Text();

	public static void setInnerLoop(int innerLoopTime){
		innerLoop = innerLoopTime;
	}
	
	public void map(Object key, Text value, OutputCollector<Text, Text> output, Reporter reporter) 
			throws IOException{
		String paragraph = value.toString();
		System.out.println("paragraph is \n" + paragraph);
		int popSize = 406;
		AProblem problem = DTLZ1.getInstance();
		int objectiveDimesion = problem.objectiveDimesion;
		int hyperplaneIntercept = 27;
		int neighbourSize = 2;
		MOP mop = CHEAMOP.getInstance(popSize, problem , hyperplaneIntercept, neighbourNum);
		mop.allocateAll(popSize,objectiveDimesion);
		MopData mopData = new MopData(mop,problem);
		//MopDataPop mopData = new MopDataPop();
		System.out.println("map begin ... ");
		//mopData.clear();
		try {
			mopData.str2Mop(paragraph);
			
			//running moead algorithm
			//chea.chea(mopData.mop,innerLoop);
			mopData.mop.updatePop(innerLoop);

			keyIndex.set("111111111");
			valueInd.set(mopData.mopAtr2Str());
			output.collect(keyIndex, valueInd);

			System.out.println("output collect ~!~");
			// key : subProblem 's index
			// value : str
			for (int i = 0; i < mopData.mop.sops.size(); i++) {
				keyIndex.set(String.valueOf(mopData.sops.get(i).sectorialIndex));
				//System.out.println("key : " + mopData.weight2Line(i) + " , value : " + mopData.mop2Line(i));
				valueInd.set(mopData.sop2Line(mopData.sops.get(i)));
				output.collect(keyIndex, valueInd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
