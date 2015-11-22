package mop;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.StringJoin;
import utilities.WrongRemindException;

public class MopData implements DataOperator {

	public MOP mop;

	public void setDelimiter(String delimiter) {
		DELIMITER = delimiter;
	}

	public MopData(MOP mop) {
		this.mop = mop;
	}


	private String sop2Line(SOP subProblem) {
		List<String> col = new ArrayList<String>();
		col.add(StringJoin.join(",",subProblem.ind.genes));
		col.add(StringJoin.join(",",subProblem.ind.objectiveValue));
		col.add(String.valueOf(subProblem.ind.kValue));
		col.add(String.valueOf(subProblem.ind.hyperplaneIntercept));
		col.add(String.valueOf(subProblem.ind.belongSubproblemIndex));
		col.add(String.valueOf(subProblem.sectorialIndex));
		col.add(StringJoin.join(",",IntegerList2IntArray(subProblem.neighbour)));
		col.add(StringJoin.join(",",subProblem.vObj));
		col.add(StringJoin.join(",",subProblem.fixWeight));
		col.add(String.valueOf(subProblem.objectiveDimesion));
		return StringJoin.join("#",col);
	}

	private int[] IntegerList2IntArray(List<Integer> l) {
		int[] n = new int[l.size()];
		for(int i = 0 ; i < l.size(); i ++) {
			n[i] = l.get(i);
		}
		return n;
	}

	private List<Integer> IntArray2IntegerList(int[] arr) {
		List<Integer> l = new ArrayList<Integer>(arr.length);
		for(int i = 0 ; i < arr.length; i ++) {
			l.add(new Integer(arr[i]));
		}
		return l;
	}

	private String mopAtr2Str() {
		List<String> col = new ArrayList<String>(mop.popSize + 1);
		col.add(String.valueOf(mop.popSize));
		col.add(String.valueOf(mop.hyperplaneIntercept));
		col.add(String.valueOf(mop.neighbourNum));
		col.add(String.valueOf(mop.perIntercept));
		List<String> tmp = new ArrayList<String>();
		for(int i = 0 ; i < mop.anchorPoint.length; i ++) {
			tmp.add(StringJoin.join(",",mop.anchorPoint[i]));
		}
		col.add(StringJoin.join("#",tmp));
		col.add(StringJoin.join("#",mop.trueNadirPoint));
		col.add(StringJoin.join("#",mop.idealPoint));
		col.add(StringJoin.join("#",mop.referencePoint));
		col.add(String.valueOf(mop.sizeSubpOnEdge));
		col.add(StringJoin.join("#",IntegerList2IntArray(mop.subpIndexOnEdge)));
		col.add(String.valueOf(mop.objectiveDimesion));
		return StringJoin.join(
	}

	// mop transfer to String popStr Nov 22
	@Override
	public String mop2Str() {
		List<String> col = new ArrayList<String>(mop.popSize + 1);
		for(int i = 0; i < mop.popSize; i ++) {
			col.add(sop2Line(mop.sops.get(i)));
		}
		col.add("111111111 " + mopAtr2Str());
		return StringJoin.join(
	}


	private void str2Sop(int i,String sopStr) throws WrongRemindException{
		String[] ss = sopStr.split("#");
		if(9 != ss.length) throws WrongRemindException("Wrong sop");
		mop.sops.get(i).ind.genes = StringJoin.decodeDoubleArray(",",ss[0]);
		mop.sops.get(i).ind.objectiveValue = StringJoin.decodeDoubleArray(",",ss[1]);
		mop.sops.get(i).ind.kValue = Double.parseDouble(ss[2]);
		mop.sops.get(i).ind.hyperplaneIntercept = Integer.parseInt(ss[3]);
		mop.sops.get(i).ind.belongSubproblemIndex = Integer.parseInt(ss[4]);
		mop.sops.get(i).sectorialIndex = Integer.parseInt(ss[5]);
		mop.sops.get(i).neighbour = IntArray2IntegerList(StringJoin.decodeIntArray(",",ss[6]));
		mop.sops.get(i).vObj = StringJoin.decodeIntArray(",",ss[7]);
		mop.sops.get(i).fixWeight = StringJoin.decodeDoubleArray(",",ss[8]);
		mop.sops.get(i).objectiveDimesion = Integer.parseInt(ss[9]);
	}

	private void str2MopAtr(String str) {
		
	}

	@Override
	public void str2Mop(String popStr) {
	
	}

	@Override
	public boolean write2FileTime(String filename, String str, int writeTime) throws IOException {
	
	}
}
