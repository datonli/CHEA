package mop;

public abstract class MOP {
	List<SOP> sops;
	List<MoChromosome> ps;
	//MoChromosome oneChild; due to use as offspring

	int popSize;
	int hyperplaneIntercept;
	int neighbourNum;

	List<double[]> anchorPoint;
	double[] trueNadirPoint;
	double[] idealPoint;
	double[] referencePoint;


	int sizeSubpOnEdge;
	List<int> subpIndexOnEdge;

	AProblem problem;
	int objectiveDimesion;

	public void allocateAll() {
		anchorPoint = new ArrayList<double[]>(objectiveDimesion);
		trueNadirPoint = new double[objectiveDimesion];
		idealPoint = new double[objectiveDimesion];
		referencePoint = new double[objectiveDimesion];
		subProblem = new ArrayList<int>(objectiveDimesion);

	}
	
	public abstract void initial();

	public abstract void excute(int run,List<double> igd);
	public abstract void evolutionTourSelect2();
	public abstract void initPopulation();
	public abstract double calcDistance(double[] w1,double[] w2) {
		double sum= 0.0;
		for(int i = 0; i < w1.length; i ++) {
			sum += Math.pow((weight1[i] - weight2[i]), 2);
		}
		return Math.sqrt(sum);
	}
	
	public abstract void initNeighbour(int neighbourNum);
	public abstract boolean hyperVolumeCompareSectorialGrid(MoChromosome c1,MoChromosome c2);
	public abstract boolean updateExtremePoint(MoChromosome ind);
	public abstract void updatePartition();
	public abstract void population2front(List<SOP> sops, List<double[]> popFront);
	public abstract double getHyperVolume(MoChromosome ind,double[] referencePointCalc);
	public abstract int tourSelectionHV(List<SOP> sops);
	public abstract double tourSelectionHVDifference(int p,List<SOP> sops);
	public abstract void savePopulation(List<SOP> sops,String fileName);
	public abstract void savePs(String fileName);
	public abstract void updateFixWeight(SOP subProblem,boolean delivery);
}
