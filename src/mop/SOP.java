package mop;

class SOP {
	public MoChromosome ind ;
	public int sectorialIndex;
	public List<int> neighbour;
	public double[] VObj;
	public int objectiveDimesion;


	public SOP(MoChromosome ind) {
		this.ind = ind;
		VObj = new double[ind.getObjectiveDimesion()];
		objectiveDimesion = ind.getObjectiveDimesion();
	}

	// cal VObj and vicinity, save the result in neighbour
	void getVicinity(int vicinityRange, int hyperplaneIntercept) {
		int[] vicinity = new int[ind.getObjectiveDimesion()];
		calVicinity(vicinityRange,hyperplaneIntercept,0,0,false,vicinity,neighbour);
	}

	void calVicinity(int vicinityRange, int hyperplaneIntercept, int calIndexNow, int leftRange, boolean isChangeBefore, int[] vicinity, List<int> neighbour) {
		int indexValue = VObj[calIndexNow];
		for (int k = indexValue - vicinityRange; k <= indexValue + vicinityRange; k ++) {
			if (k < 0 || k > hyperplaneIntercept ) continue;
			vicinityRange[calIndexNow] = k;
			if (calIndexNow == objectiveDimesion - 1) {
				if (isChangeBefore && (leftRange + (k - indexValue) == ) ) {
					int index = ind.getIndexFromVObj(vicinity, hyperplaneIntercept);
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
