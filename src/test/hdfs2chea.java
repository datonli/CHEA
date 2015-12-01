package test;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import utilities.StringJoin;
import utilities.WrongRemindException; 

import problems.DTLZ1;

import mr.HdfsOper;

import problems.AProblem;
import mop.CHEAMOP;
import mop.MOP;
import mop.MopData;



public class hdfs2chea {

	public static void main(String[] args) throws IOException ,WrongRemindException {
		int popSize = 406;
        int hyperplaneIntercept = 27;
        int neighbourNum = 2; 
        AProblem problem = DTLZ1.getInstance();
        MOP mop = CHEAMOP.getInstance(popSize, problem , hyperplaneIntercept, neighbourNum);
		MopData mopData = new MopData(mop,problem);
		HdfsOper hdfsOper = new HdfsOper();
        String line = null;
        String content = null;
        List<String> col = new ArrayList<String>();
		mopData.clear();
        mopData.setDelimiter("\n");
        mopData.str2Mop(hdfsOper.readWholeFile("chea/400/part-00000"));
        for(int j = 0 ; j < mopData.mop.sops.size(); j ++) {
           col.add(StringJoin.join(" ",mopData.mop.sops.get(j).ind.objectiveValue));
        }
        content = StringJoin.join("\n", col);
        mopData.write2File("/home/laboratory/workspace/chea_parallel/experiments/parallel/mr_chea.txt",content);                                                                                           
	}
}
