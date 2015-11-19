package mop;

class SOP {
	public MoChromosome ind ;
	public int sectorialIndex;
	public List<int> neighbour;
	//public double[] vObj;
	public int[] vObj;
	public double[] fixWeight;
	public int objectiveDimesion;
	

	public SOP(MoChromosome ind) {
		this.ind = ind;
		//vObj = new double[ind.getObjectiveDimesion()];
		vObj = new int[ind.getObjectiveDimesion()];
		objectiveDimesion = ind.getObjectiveDimesion();
		fixWeight = new double[objectiveDimesion];
		for(int i = 0 ; i < objectiveDimesion; i ++) fixWeight[i] = 1.0;
	}

	// cal vObj and vicinity, save the result in neighbour
	void getVicinity(int vicinityRange, int hyperplaneIntercept) {
		int[] vicinity = new int[ind.getObjectiveDimesion()];
		calVicinity(vicinityRange,hyperplaneIntercept,0,0,false,vicinity,neighbour);
	}

	void calVicinity(int vicinityRange, int hyperplaneIntercept, int calIndexNow, int leftRange, boolean isChangeBefore, int[] vicinity, List<int> neighbour) {
		int indexValue = vObj[calIndexNow];
		for (int k = indexValue - vicinityRange; k <= indexValue + vicinityRange; k ++) {
			if (k < 0 || k > hyperplaneIntercept ) continue;
			vicinityRange[calIndexNow] = k;
			if (calIndexNow == objectiveDimesion - 1) {
				if (isChangeBefore && (leftRange + (k - indexValue) == ) ) {
					int index = ind.getIndexFromvObj(vicinity, hyperplaneIntercept);
					neighbour.add(index);
				}
				continue;
			}
			boolean trueOrFalse = ture;
			if (!isChangeBefore && (k == indexValue) ) trueOrFalse = false;
			calVicinity(vicinityRange,hyperplaneIntercept,calIndexNow + 1,leftRange + (k - indexValue)
							,trueOrFalse,vicinity,neighbour);
		}
	}

}
