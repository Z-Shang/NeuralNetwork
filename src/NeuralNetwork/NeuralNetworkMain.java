package NeuralNetwork;

import javax.swing.JFrame;

public class NeuralNetworkMain {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Draw panel = new Draw();
		frame.setResizable(false);
		frame.setTitle("Neural Network Project");
		frame.setSize(1365,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}

}

//Test Change
