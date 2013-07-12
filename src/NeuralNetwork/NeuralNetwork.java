package NeuralNetwork;

public class NeuralNetwork {
	
	Layer[] layers;
	Dendrite[] connections;
	private int numberOfLayers = 3;
	private int numberOfConnections = 0;
	int pop_size;
	Population pop;
	
	NeuralNetwork(int pop_size){
		this.pop_size = pop_size;
		initializeNeuralNetwork(0);
	}
	
	public void initializeNeuralNetwork(int index){
		
		layers = new Layer[numberOfLayers];
		
		for (int i = 0; i < numberOfLayers; i++){
			if (i == 0){
				layers[i] = new Layer(5,"Input Layer");
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setPos(501 + j*150, i*150);
				}
			}
			else if (i == (numberOfLayers-1)){
				layers[i] = new Layer(2, "Output Layer");
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setPos(501 + j*150, i*150);
				}
			}
			else{
				layers[i] = new Layer(8, "Hidden Layer " + i);
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setPos(501 + j*100, i*150);
				}
			}
		}
		
		for (int i = 0; i < numberOfLayers-1; i++){
			numberOfConnections = numberOfConnections + (layers[i].getAmountOfNeurons()*layers[i+1].getAmountOfNeurons());
		}
		
		connections = new Dendrite[numberOfConnections];
		
		pop = new Population(pop_size,numberOfConnections,true);
	
		initializeNeuralNetworkConnections(pop, index);

	}
	
	public void initializeNeuralNetworkConnections(Population pop, int popIndex){	
		int connectionsIndex = 0;
		
		for (int i = 0; i < (numberOfLayers-1); i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				for (int k = 0; k < layers[i+1].getAmountOfNeurons(); k++){
				connections[connectionsIndex] = new Dendrite();
				connections[connectionsIndex].setDendriteWeight(pop.getGene(popIndex).getBase(connectionsIndex));
				connections[connectionsIndex].setInputNeuron(layers[i].getNeuron(j));
				connections[connectionsIndex].getInputNeuron().setNeuronName("Layer: " + i + " Neuron Index: " + j);
				connections[connectionsIndex].setOutputNeuron(layers[i+1].getNeuron(k));
				connections[connectionsIndex].getOutputNeuron().addInputConnections(connections[connectionsIndex]);
				connections[connectionsIndex].getInputNeuron().setNeuronName("Layer: " + (i+1) + " Neuron Index: " + k);
				connectionsIndex = connectionsIndex + 1;
				}
			}
		}
	}
	
	public Layer[] getLayers(){
		return layers;
	}
	
	public Dendrite[] getConnections(){
		return connections;
	}
	
	public Population getPop(){
		return pop;
	}
	
	public void setPop(Population pop){
		this.pop = pop;
	}
}
