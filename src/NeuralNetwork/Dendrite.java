package NeuralNetwork;

public class Dendrite {
	
	private Neuron input_neuron;
	private Neuron output_neuron;
	private double dendrite_weight;
	
	public void setInputNeuron(Neuron input_neuron){
		this.input_neuron = input_neuron;
	}
	
	public void setOutputNeuron(Neuron output_neuron){
		this.output_neuron = output_neuron;
	}
	
	public void setDendriteWeight(double dendrite_weight){
		this.dendrite_weight = dendrite_weight;
	}
	
	public Neuron getInputNeuron(){
		return input_neuron;
	}
	
	public Neuron getOutputNeuron(){
		return output_neuron;
	}
	
	public double getDendriteWeight(){
		return dendrite_weight;
	}
	
	
}
