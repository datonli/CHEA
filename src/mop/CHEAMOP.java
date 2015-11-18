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
	
	// init all data struct need to initial Nov 18
	public void initial() {
		initPopulation();
		initNeighbour(1);

	}


	// generate subproblem, and add points to subpIndexOnEdge ... Nov 18
	private void genSubProblems(){ 
		for(int i = 0 ; i < popSize; i ++){
            sop = new SOP(CMoChromosome.createChromosome());
            sops.add(sop);       
		}	
	}

	public void initPopulation() {
		// generate subproblem , add them all into sops  , Nov 18
	    genSubProblems();
		// initial the all points
		for(int i = 0; i < objectiveDimesion; i ++) {
			
		}
		for(int n = 1 ; n < popSize; n ++) {
			updateExtremePoint(sops.get(n).ind);
		}
		updatePartition();
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

	// update after offspring 
	public void updatePartition() {
		for( int n = 0; n < popSize; n ++) {
			sops.get(n).ind.objIndex(idealPoint,hyperplaneIntercept);
		}	
		boolean[] sopsFlag = new boolean[popSize];
		List<MoChromosome> initRestIndPop = new ArrayList<MoChromosome>(popSize);
		for(int n = 0 ; n < popSize; n ++) {
			if(sops.get(n).sectorialIndex == sops.get(n).ind.belongSubproblemIndex ){
				sopsFlag[sops.get(n).sectorialIndex] = true;
			} else {
				MoChromosome ind = sops.get(n).ind;
				// maybe something wrong happend cause two "ind" Nov 18
				while(true) {
					sops[ind.belongSubproblemIndex] = true;
					if(false == hyperVolumeCompareSectorialGrid(ind,ind)) 	break;
				}
			}
			initRestIndPop.add(ind);
		}
		int sizeOfRestInd = initRestIndPop.size();
		List<double[]> vObjRestInd = new ArrayList<double[]>(sizeOfRestInd);
		double[] calVObj ;
		for(int i = 0 ; i < sizeOfRestInd; i ++) {
			calVObj = initRestIndPop.get(i).calVObj(idealPoint,hyperplaneIntercept);
			vObjRestInd.add(calVObj);
		}
		for(int i = 0 ; i < popSize; i ++) {
			if(false  == sopsFlag[i] ) {
				SOP subproblem = sops.get(i);
				int minIndexDist = 0;
				for(int j = 0 ; j < objectiveDimesion; j ++) {
					// Nov 18 maybe wrong in this place because of wrong define minIndexDist.
					// for every objectiveDimesion value, should find the minium value, but below couldn't
					// I don't know the use of minIndexDist. so don't know howto modify.
					minIndexDist = Math.pow(vObjRestInd.get(0)[j] - subproblem.objectiveValue[j], 2.0);
				}
				int minDiffIndex = 0 ;
				int restSize = initRestIndPop.size();
				for( int k = 1; k < restSize; k ++ ) {
					int indexDist = 0 ;
					for (int j = 0 ; j < objectiveDimesion; j ++ ) {
						indexDist = Math.pow(vObjRestInd.get(k)[j] - subproblem.objectiveDimesion[j], 2.0);
					}
					if(indexDist < minIndexDist || (indexDist == minIndexDist && PRNG.nextDouble() > 0.5) ) {
						minIndexDist = indexDist;
						minDiffIndex = k;
					}
				}

				sops.get(i).ind = initRestIndPop.get(minIndexDist);
				sopsFlag[i] = true;

				// don't know the use.  Nov 18
				initRestIndPop.get(minDiffIndex) = initRestIndPop.get(restSize - 1);
				vObjRestInd.get(minDiffIndex) = vObjRestInd.get(restSize - 1);
				initRestIndPop.remove(initRestIndPop.size() - 1);
				vObjRestInd.remove(vObjRestInd.size() - 1);
			}
		}
	}


	public void population2front(List<SOP> sops,List<double[]> popFront) {}

	public void updateFixWeight(SOP subproblem,boolean delivery) {}

	public boolean hyperVolumeCompareSectorialGrid(MoChromosome c1,MoChromosome c2) {
		
	}



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
