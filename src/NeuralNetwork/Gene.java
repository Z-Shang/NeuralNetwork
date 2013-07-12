package NeuralNetwork;

import java.util.Random;

public class Gene {
	
	private float[] Base;
	private double fitness_level = 0;
	private int gene_size;
	
	private Random r = new Random();
	
	Gene(){
		
	}
	
	Gene(int gene_size,boolean initialize){
		Base = new float[gene_size];
		this.gene_size = gene_size;
		if (initialize){
			for (int i = 0; i < gene_size; i++){
				Base[i] = (r.nextFloat() * 1);
			}
		}
	}
	
	public float getBase(int index){
		float base = Base[index];
		return base;
	}
	
	public void setBase(int index, float value){
		Base[index] = value;
	}
	
	public double getFitnessLevel(){
		return this.fitness_level;
	}
	
	public int getSize(){
		return gene_size;
	}
	
	public void setFitnessLevel(double d){
		this.fitness_level = d;
	}
	
	
}
