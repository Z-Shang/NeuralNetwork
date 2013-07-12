package NeuralNetwork;

public class Layer {

	private Neuron[] neurons;
	private String layer_name;
	private int amountOfNeurons;
	private int amountOfConnections;
	
	Layer(int amountOfNeurons, String layer_name){
		this.layer_name = layer_name;
		this.amountOfNeurons = amountOfNeurons;
		neurons = new Neuron[amountOfNeurons];
		for (int i = 0; i < amountOfNeurons; i++){
			neurons[i] = new Neuron();
			neurons[i].setNeuronName("Neuron: " + i);
		}
	}
	
	public String getLayerName(){
		return this.layer_name;
	}
	
	public int getAmountOfNeurons(){
		return this.amountOfNeurons;
	}
	
	public int getAmountOfConnections(){
		return this.amountOfConnections;
	}
	
	public Neuron getNeuron(int index){
		return neurons[index];
	}
}
