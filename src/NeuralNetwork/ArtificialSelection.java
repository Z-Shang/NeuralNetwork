package NeuralNetwork;

import java.util.Random;

public class ArtificialSelection {
	
	private Random r = new Random();
	private double mutationRate = .65; //.175
	private int breedSelectionSize = 5;
	
	public Gene mutateGene(Gene originalGene){
		for (int i = 0; i < originalGene.getSize(); i++){
			if (r.nextDouble() <= mutationRate){
				originalGene.setBase(i,r.nextFloat()*1);
			}
		}
		Gene newGene = originalGene;
		return newGene;
	}
	
	public Gene crossOver(Gene Gene, Gene otherGene, int crossoverType){
		Gene newGene = new Gene(Gene.getSize(),false);
		switch(crossoverType){
		case 1:
			int crossoverPoint = r.nextInt(Gene.getSize());
			for (int i = 0; i < crossoverPoint; i++){
				newGene.setBase(i,Gene.getBase(i));
			}
			for (int i = (crossoverPoint + 1); i < otherGene.getSize(); i++){
				newGene.setBase(i,otherGene.getBase(i));
			}
			break;
			// Single-Point
		case 2:
			int crossoverPoint1 = r.nextInt(otherGene.getSize()/2);
			int crossoverPoint2 = crossoverPoint1 + r.nextInt(otherGene.getSize()/2);
			for (int i = 0; i < crossoverPoint1; i++){
				newGene.setBase(i,Gene.getBase(i));
			}
			for (int i = crossoverPoint1; i < crossoverPoint2; i++){
				newGene.setBase(i,otherGene.getBase(i));
			}
			for (int i = crossoverPoint2; i < Gene.getSize(); i++){
				newGene.setBase(i,Gene.getBase(i));
			}
			break;
			// Two-Point
		case 3:
			for (int i = 0; i < newGene.getSize(); i++){
				if (r.nextDouble() < .75){
					newGene.setBase(i,Gene.getBase(i));
				}
				else
					newGene.setBase(i,otherGene.getBase(i));
			}
			break;
			// Uniform
		case 4:
			break;
			// Arithmetic
		}
		return newGene;
	}
	
	public Gene breedSelection(Population pop){
		
		Population selection = new Population(breedSelectionSize,pop.getGene(0).getSize(),false);
		
		for (int i = 0; i < breedSelectionSize; i++){
			selection.storeGene(i, pop.getFittest());
		}
		
		Gene breedSelectionFit = selection.getFittest();
		return breedSelectionFit;
	}
	
}
