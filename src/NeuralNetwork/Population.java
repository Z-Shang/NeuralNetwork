package NeuralNetwork;


public class Population {
	
	private Gene[] individuals;
	private int pop_size;
	
	Population(int pop_size,int gene_size, boolean initialize){
		individuals = new Gene[pop_size];
		this.pop_size = pop_size;
		
		if (initialize){
			for (int i = 0; i < individuals.length; i++){
				individuals[i] = new Gene(gene_size,true);
			}
		}
		
	}
	
	public Gene getGene(int index){
		return individuals[index];
	}
	
	public int getPopSize(){
		return pop_size;
	}
	
	public Gene getFittest(){
		Gene fittest = individuals[0];
		for (int i = 0; i < individuals.length; i++){
			if (fittest.getFitnessLevel() <= individuals[i].getFitnessLevel()){
				fittest = individuals[i];
			}
		}
		return fittest;
	}
	
	
	public Gene getUnFittest(){
		Gene unFittest = individuals[0];
		for (int i = 1; i < individuals.length; i++){
			if (unFittest.getFitnessLevel() > individuals[i].getFitnessLevel()){
				unFittest = individuals[i];
			}
		}
		return unFittest;
	}
	
	public void storeGene(int index, Gene individual){
		individuals[index] = individual;
	}
	
}
