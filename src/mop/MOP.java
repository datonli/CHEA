package mop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import problems.AProblem;
import utilities.WrongRemindException;

public abstract class MOP {
	static MOP instance;

	List<SOP> sops;
	List<double[]> ps;
	//MoChromosome oneChild; due to use as offspring

	int popSize;
	int hyperplaneIntercept;
	int neighbourNum;
	double perIntercept; 

	//List<double[]> anchorPoint;
	double[][] anchorPoint;
	double[] trueNadirPoint;
	double[] idealPoint;
	double[] referencePoint;


	int sizeSubpOnEdge;
	List<Integer> subpIndexOnEdge;

	AProblem problem;
	int objectiveDimesion;

	public void allocateAll() {
		//anchorPoint = new ArrayList<double[]>(objectiveDimesion);
		anchorPoint = new double[objectiveDimesion][objectiveDimesion];
		trueNadirPoint = new double[objectiveDimesion];
		idealPoint = new double[objectiveDimesion];
		referencePoint = new double[objectiveDimesion];
		subpIndexOnEdge = new ArrayList<Integer>(objectiveDimesion);
		sops = new ArrayList<SOP>(popSize);
	}

	public void clear() {
		sops.clear();
		subpIndexOnEdge.clear();
	}
	
public void allocateAll(int popSize, int objectiveDimesion) {
		//anchorPoint = new ArrayList<double[]>(objectiveDimesion);
		anchorPoint = new double[objectiveDimesion][objectiveDimesion];
		trueNadirPoint = new double[objectiveDimesion];
		idealPoint = new double[objectiveDimesion];
		referencePoint = new double[objectiveDimesion];
		subpIndexOnEdge = new ArrayList<Integer>(objectiveDimesion);
		sops = new ArrayList<SOP>(popSize);
	}


	public abstract void initial();


	//public abstract void excute(int run,List<Double> igd);
	public abstract void evolutionTourSelect2();
	public abstract void initPopulation();
	public double calcDistance(double[] w1,double[] w2) {
		double sum= 0.0;
		for(int i = 0; i < w1.length; i ++) {
			sum += Math.pow((w1[i] - w2[i]), 2.0);
		}
		return Math.sqrt(sum);
	}
	
	public abstract void updatePop(int itertions);
	public abstract void initNeighbour(int neighbourNum);
	public abstract MoChromosome hyperVolumeCompareSectorialGrid(MoChromosome ind);
	public abstract boolean updateExtremePoint(MoChromosome ind);
	public abstract void updatePartition();
	public abstract List<double[]> population2front(List<SOP> pop);
	public abstract double getHyperVolume(MoChromosome ind,double[] referencePointCalc);
	public abstract int tourSelectionHV(List<SOP> sops);
	public abstract double tourSelectionHVDifference(int p,List<SOP> sops);
	//public abstract void savePopulation(List<SOP> sops,String fileName);
	public abstract void write2File(String fileName) throws IOException;
	public abstract void updateFixWeight(SOP subProblem,boolean delivery);
}
