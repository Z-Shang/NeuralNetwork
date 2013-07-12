package NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class Neuron {

	private String neuron_name;
	private double neuron_weight;
	private double activation_function_result;
	private float pos_x;
	private float pos_y;
	private double sigmoid_function_result;
	private double hyperbolic_tangent_function_result;
	private double arithmetic_function_result;
	
	
	private ArrayList<Dendrite> input_connections = new ArrayList<Dendrite>();
	
	
	
	public void setNeuronWeight(double neuron_weight){
		this.neuron_weight = neuron_weight;
	}
	
	public double getNeuronWeight(){
		return this.neuron_weight;
	}
	
	public void setNeuronName(String neuron_name){
		this.neuron_name = neuron_name;
	}
	
	public String getNeuronName(){
		return this.neuron_name;
	}
	
	public double activation_function(){
		activation_function_result = 0;
		for (int i = 0; i < input_connections.size(); i++){
			activation_function_result = activation_function_result + (input_connections.get(i).getDendriteWeight() 
										   * input_connections.get(i).getInputNeuron().getNeuronWeight());
		}
		return activation_function_result;
	}
	
	public double sigmoid_function(){
		sigmoid_function_result = (1/(1 + Math.exp(-activation_function_result)));
		return sigmoid_function_result;
	}
	
	public double hyperbolic_tangent_function(){
		hyperbolic_tangent_function_result = (Math.exp(2*activation_function_result) - 1)/(Math.exp(2*activation_function_result) + 1);
		return hyperbolic_tangent_function_result;
	}
	
	public double arithmetic_function(){
		return arithmetic_function_result;
	}
	
	public void setPos(float pos_x, float pos_y ){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}	
	
	public float getPosX(){
		return this.pos_x;
	}
	
	public float getPosY(){
		return this.pos_y;
	}
	
	public void addInputConnections(Dendrite input_connection){
		input_connections.add(input_connection);
	}
	
	public void removeConnections(){
		input_connections.removeAll(input_connections);
	}
	
	public int countConnections(){
		int k = 0;
		for (int i = 0; i < input_connections.size(); i++){
			k = k + 1;
		}
		return k;
	}
	
	public ArrayList<Dendrite> getConnections(){
		return this.input_connections;
	}
	
	public double getActivationFunctionResult(){
		return activation_function_result;
	}
}
