package mop;


public class CHEAMOP extends MOP{

    private final double F = 0.5;
    private final double CR = 1;
    
    private CHEAMOP(int popSize,AProblem problem,int hyperplaneIntercept,int neighbourNum){
        this.popSize = popSize;   
        this.neighbourNum = neighbourNum;
        this.hyperplaneIntercept = hyperplaneIntercept;
        this.objectiveDimesion = AProblem.objectiveDimesion;
        this.problem = problem;   
        allocateAll();
    } 	
	
	public void initial() {
		initPopulation();
		initNeighbour(1);

	}


	public void initPopulation() {
	    for(int i = 0 ; i < popSize; i ++){
            sop = new SOP(CMoChromosome.createChromosome());
            sops.add(sop);       
		}	
		for(int i = 0; i < objectiveDimesion; i ++) {
			
		}
	}

	public void evolutionTourSelect2() {}

    // tour select two points as parents for reproduction.  Nov 11
    public int tourSelectionHV(List<SOP> sops) {
        int p1 = int(PRNG.nextDouble() * popSize);
        int p2 = int(PRNG.nextDouble() * popSize);
        double hv1 = tourSelectionHVDifference(p1,sops);
        double hv2 = tourSelectionHVDifference(p2,sops);
        if(hv1 >= hv2) return p1;
        else return p2;
    }

    public int tourSelectionHVDifference(int p,List<SOP> sops){
            int num = 0 ;
            int index ;
            double hvSide = 0.0;
            double hvDifference = 0.0;
            
            // need to add a sub-problem class , CHEA must have a sub problem  Nov 13
            while(sops.get(i).ind.belongSubproblemIndex != sops.get(i).sectorialIndex) {
                p = sops.get(i).ind.belongSubproblemIndex;
            }
            SOP subproblem = sops.get(p);
            int subproblemNeighbourSize  = subproblem.neighbour.size();
            double hv0 = getHyperVolume(sops.get(p).ind, referencePoint);
            for(int i = 0 ; i < subproblemNeighbourSize; i ++) {
                SOP sop = sops.get(subproblem.neighbour.get(i));
                if( sop.sectorialIndex == sop.ind.belongSubproblemIndex) {
                    hvSide = getHyperVolume(sop.ind, referencePoint);
                    hvDifference += (hv0 - hvSide);
                    num ++;
                }
            }
            if(num != 0) hvDifference = hvDifference/num;
            return hvDifference;
            belongSubproblemIndex;
    }

	public double getHyperVolume(MoChromosome ind,double[] referencePointCalc) {
        double volume = 1;
        for(int j = 0 ; j < objectiveDimesion; j ++) volume *= (referencePointCalc - ind.objectiveValue[j]);
        return volume;	
	}

	public void excute(int run,List<double> igd) {}

	

    // update Pop part is main to excute the evolustion. Nov 14
    @Override
    public void updatePop() {
        boolean isUpdate = false;
        int len = 0 ;

        // need to add a part about calculating the IGD every 25 gen or 10 gen Nov 11
        for(int i = 0 ;i < popSize; i ++){
            // this is MOEAD part ; delete evolveNewInd(i);
            // select two indivduals to reproduce a new offspring. Nov 11
            int parentIndex1 = 0;
            int parentIndex2 = 0;
            int b = len % (popSize/7); 
            if(b < sizeSubpOnEdge) {
                parentIndex1 =  subpIndexOnEdge.get(b);
            } else {
                parentIndex1 = tourSelectionHV(sops);
            }
            parentIndex2 = tourSelectionHV(sops);
            MoChromosome offSpring = new CMoChromosome();
            offSpring.crossover((MoChromosome)sops.get(parentIndex1).ind,(MoChromosome)sops.get(parentIndex2).ind);
            offSpring.mutate(1d/offSpring.genesDimesion);
            
            offSpring.evaluate(problem);
            updatePoints(offSpring);
            
            len ++; 

        }

        // leave empty place for IGD
    }

    // updatePoints including idealPoint points ,reference points and extrem points. Nov 11
    private void updatePoints(MoChromosome offSpring) {

		// update idealPoint  Nov 17
        for(int j = 0 ; j < offSpring.objectiveDimesion; j ++){
            if(offSpring.objectiveValue[j] < idealPoint[j]){
                idealPoint[j] = offSpring.objectiveValue[j];
            }
        }	

		//update reference points
		
		// update extrem points

	}


	public void updatePartition() {}

	public void population2front(List<SOP> sops,List<double[]> popFront) {}

	public void updateFixWeight(SOP subproblem,boolean delivery) {}

	public boolean hyperVolumeCompareSectorialGrid(MoChromosome c1,MoChromosome c2) {}


    private double calcIGD() {
        double distanceIGD = 0.0;
        for (int i  = 0 ; i < ps.size(); i ++) {
            double minDistance = 1.0e+10;
            for (int j = 0 ; j < popSize; j ++) {
                double d = calcDistance(ps.get(i).objectiveValue,sops.get(i).ind.objectiveValue);
                if(d < minDistance) minDistance = d;
            }
            distanceIGD += minDistance;
        }
        distanceIGD /= popSize;
        return distanceIGD;
    }

}
