package NeuralNetwork;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.*;
import java.util.Random;

public class Draw extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int x = 340;
	private int y = 70;
	private double theta = Math.PI;
	private double line1_input = 0;
	private double line2_input = 0;
	private double line3_input = 0;
	private double line4_input = 0;
	private double line5_input = 0;
	private int i = 0;
	private Layer[] layers;
	private Dendrite[] connections;
	private NeuralNetwork network;
	private ArtificialSelection algorithm = new ArtificialSelection();

	
	boolean con = true;
	
	Random r = new Random();

	Rectangle.Float car = new Rectangle.Float(x,y,25,10);
	Rectangle.Float rec2 = new Rectangle.Float(0,0,50,500);
	Rectangle.Float rec3 = new Rectangle.Float(0,0,500,50);
	Rectangle.Float rec4 = new Rectangle.Float(450,0,500,500);
	Rectangle.Float rec5 = new Rectangle.Float(0,450,500,500);
	Rectangle.Float rec8 = new Rectangle.Float(120,105,250,285);
	
	Rectangle rec6 = new Rectangle(500,0,1365,500);
	Rectangle rec7 = new Rectangle(0,500,1365,900);
	
	Line2D line;
	Line2D line2;
	Line2D line3;
	Line2D line4;
	Line2D line5;
	
	Timer timer = new Timer(1,this);
	
	Draw(){
		setBackground(Color.white);
		network = new NeuralNetwork(15);
		setNeuralNetwork(network.getLayers(),network.getConnections());
		outPutNeuralNetwork();
		timer.start();
		
		
		for (int i = 0; i < network.pop_size; i++){
			System.out.println("Gene: " + (i+1) + " \n");
			for (int j = 0; j < network.getPop().getGene(i).getSize(); j++){
				System.out.println(network.getPop().getGene(i).getBase(j));
			}
			System.out.println("\n");
		}
	
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;	
		g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
		initializeTrack(g2d);
		g2d.setColor(Color.blue.brighter());
		g2d.fill(rec6);
		g2d.fill(rec7);
		runNeuralNetwork(g2d);
		g2d.setColor(Color.green);
		AffineTransform transformCar = new AffineTransform();
		Area transformBox = new Area(car);
		transformCar.rotate(theta,car.x + 12.5,car.y + 5);
		transformBox.transform(transformCar);
		g2d.fill(transformCar.createTransformedShape(car));
		g2d.draw(transformBox.getBounds());
		g2d.setColor(Color.red);
		paintNeuralNetwork(g2d);
		g2d.setColor(Color.red);
		g2d.draw(transformBox.getBounds());
		g2d.setColor(Color.blue);
		g2d.draw(car);

		line = new Line2D.Float(car.x + (float)car.getWidth()/2 ,
				car.y + (float)car.getHeight()/2,
				car.x + (float)Math.cos(theta)*(50)  + (float) (car.getWidth()/2),
				car.y + (float)Math.sin(theta)*(50) +  (float) (car.getHeight()/2));
		
		line2 = new Line2D.Float(car.x + (float)car.getWidth()/2 ,
				car.y + (float)car.getHeight()/2,
				car.x + (float)Math.cos(theta + Math.PI/2)*(50)  + (float) (car.getWidth()/2),
				car.y + (float)Math.sin(theta + Math.PI/2)*(50) +  (float) (car.getHeight()/2));
		
		line3 = new Line2D.Float(car.x + (float)car.getWidth()/2 ,
				car.y + (float)car.getHeight()/2,
				car.x + (float)Math.cos(theta - Math.PI/2)*(50)  + (float) (car.getWidth()/2),
				car.y + (float)Math.sin(theta - Math.PI/2)*(50) +  (float) (car.getHeight()/2));
		
		line4 = new Line2D.Float(car.x + (float)car.getWidth()/2 ,
				car.y + (float)car.getHeight()/2,
				car.x + (float)Math.cos(theta - Math.PI/4)*(50)  + (float) (car.getWidth()/2),
				car.y + (float)Math.sin(theta - Math.PI/4)*(50) +  (float) (car.getHeight()/2));
		
		line5 = new Line2D.Float(car.x + (float)car.getWidth()/2 ,
				car.y + (float)car.getHeight()/2,
				car.x + (float)Math.cos(theta + Math.PI/4)*(50)  + (float) (car.getWidth()/2),
				car.y + (float)Math.sin(theta + Math.PI/4)*(50) +  (float) (car.getHeight()/2));
		
		g2d.draw(line);
		g2d.draw(line2);
		g2d.draw(line3);
		g2d.draw(line4);
		g2d.draw(line5);
	
		collision(g2d,transformBox);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (layers[layers.length-1].getNeuron(0).getNeuronWeight() - layers[layers.length-1].getNeuron(1).getNeuronWeight() > 0){
			theta = theta + ((layers[layers.length-1].getNeuron(0).getNeuronWeight()/2)*Math.PI/180);
		}
		else if (layers[layers.length-1].getNeuron(0).getNeuronWeight() - layers[layers.length-1].getNeuron(1).getNeuronWeight() < 0) {
			theta = theta - ((layers[layers.length-1].getNeuron(1).getNeuronWeight()/2)*Math.PI/180);
		}
		car.x = (float) (car.x + (Math.cos(theta))*3);
		car.y = (float) (car.y + (Math.sin(theta))*3);
		repaint();
	}
	
	public void collision(Graphics g2d, Area transformBox){
		
		checkLineCollision(line,rec2,g2d);
		checkLineCollision(line,rec3,g2d);
		checkLineCollision(line,rec4,g2d);
		checkLineCollision(line,rec5,g2d);
		checkLineCollision(line2,rec2,g2d);
		checkLineCollision(line2,rec3,g2d);
		checkLineCollision(line2,rec4,g2d);
		checkLineCollision(line2,rec5,g2d);
		checkLineCollision(line3,rec2,g2d);
		checkLineCollision(line3,rec3,g2d);
		checkLineCollision(line3,rec4,g2d);
		checkLineCollision(line3,rec5,g2d);
		checkLineCollision(line4,rec2,g2d);
		checkLineCollision(line4,rec3,g2d);
		checkLineCollision(line4,rec4,g2d);
		checkLineCollision(line4,rec5,g2d);
		checkLineCollision(line5,rec2,g2d);
		checkLineCollision(line5,rec3,g2d);
		checkLineCollision(line5,rec4,g2d);
		checkLineCollision(line5,rec5,g2d);
		checkLineCollision(line,rec8,g2d);
		checkLineCollision(line2,rec8,g2d);
		checkLineCollision(line3,rec8,g2d);
		checkLineCollision(line4,rec8,g2d);
		checkLineCollision(line5,rec8,g2d);
		
		
		if (rec2.intersects(transformBox.getBounds())){
			line1_input = 0;
			line2_input = 0;
			line3_input = 0;
			line4_input = 0;
			line5_input = 0;
			for (int i = 1; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setNeuronWeight(0);
				}	
			}
			network.getPop().getGene(i).setFitnessLevel(Math.sqrt((car.x - 340)*(car.x - 340) + (car.y - 75)*(car.y - 75)));
			System.out.println(network.getPop().getGene(i));
			System.out.println(network.getPop().getGene(i).getFitnessLevel());
			System.out.println("Gene: " + i);
			for (int i = 0; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).removeConnections();
				}
			}
			if (i < 14){
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i += 1;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				}
			else {
				evolvePopulation();
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i = 0;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				System.out.println(network.getPop().getGene(i));
			}
		}
		if (rec3.intersects(transformBox.getBounds())){
			line1_input = 0;
			line2_input = 0;
			line3_input = 0;
			line4_input = 0;
			line5_input = 0;
			for (int i = 1; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setNeuronWeight(0);
				}	
			}
			network.getPop().getGene(i).setFitnessLevel(Math.sqrt((car.x - 340)*(car.x - 340) + (car.y - 75)*(car.y - 75)));
			System.out.println(network.getPop().getGene(i));
			System.out.println(network.getPop().getGene(i).getFitnessLevel());
			System.out.println("Gene: " + i);
			for (int i = 0; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).removeConnections();
				}
			}
			if (i < 14){
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i += 1;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				}
			else{ 
				evolvePopulation();
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i = 0;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				System.out.println(network.getPop().getGene(i));
			}
		}
		if (rec4.intersects(transformBox.getBounds())){
			line1_input = 0;
			line2_input = 0;
			line3_input = 0;
			line4_input = 0;
			line5_input = 0;
			for (int i = 1; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setNeuronWeight(0);
				}	
			}
			network.getPop().getGene(i).setFitnessLevel(Math.sqrt((car.x - 340)*(car.x - 340) + (car.y - 75)*(car.y - 75)));
			System.out.println(network.getPop().getGene(i));
			System.out.println(network.getPop().getGene(i).getFitnessLevel());
			System.out.println("Gene: " + i);
			for (int i = 0; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).removeConnections();
				}
			}
			if (i < 14){
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i += 1;
				network.initializeNeuralNetworkConnections(network.pop,i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				}
			else{
				evolvePopulation();
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i = 0;
				network.initializeNeuralNetworkConnections(network.pop,i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				System.out.println(network.getPop().getGene(i));
			}
		}
		if (rec5.intersects(transformBox.getBounds())){
			line1_input = 0;
			line2_input = 0;
			line3_input = 0;
			line4_input = 0;
			line5_input = 0;
			for (int i = 1; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setNeuronWeight(0);
				}	
			}
			network.getPop().getGene(i).setFitnessLevel(Math.sqrt((car.x - 340)*(car.x - 340) + (car.y - 75)*(car.y - 75)));
			System.out.println(network.getPop().getGene(i));
			System.out.println(network.getPop().getGene(i).getFitnessLevel());
			System.out.println("Gene: " + i);
			for (int i = 0; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).removeConnections();
				}
			}
			if (i < 14){
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i += 1;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				}
			else{
				evolvePopulation();
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i = 0;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				System.out.println(network.getPop().getGene(i));
			}
		   }
		if (rec8.intersects(transformBox.getBounds())){
			line1_input = 0;
			line2_input = 0;
			line3_input = 0;
			line4_input = 0;
			line5_input = 0;
			for (int i = 1; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).setNeuronWeight(0);
				}	
			}
			network.getPop().getGene(i).setFitnessLevel(Math.sqrt((car.x - 340)*(car.x - 340) + (car.y - 75)*(car.y - 75)));
			System.out.println(network.getPop().getGene(i));
			System.out.println(network.getPop().getGene(i).getFitnessLevel());
			System.out.println("Gene: " + i);
			for (int i = 0; i < layers.length; i++){
				for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
					layers[i].getNeuron(j).removeConnections();
				}
			}
			if (i < 14){
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i += 1;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				}
			else{
				evolvePopulation();
				car.x = 340;
				car.y = 75;
				theta = Math.PI;
				i = 0;
				network.initializeNeuralNetworkConnections(network.getPop(),i);
				setNeuralNetwork(network.getLayers(),network.getConnections());
				System.out.println(network.getPop().getGene(i));
			}
		   }
		}	
	
	
	public void initializeTrack(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.draw(rec2);
		g2d.fill(rec2);
		g2d.draw(rec3);
		g2d.fill(rec3);
		g2d.draw(rec4);
		g2d.fill(rec4);
		g2d.draw(rec5);
		g2d.fill(rec5);
		g2d.draw(rec8);
		g2d.fill(rec8);
	}
	
	public void setNeuralNetwork(Layer[] layers,Dendrite[] connections){
		this.layers = layers;
		this.connections = connections;
	}
	
	public void paintNeuralNetwork(Graphics2D g2d){
		int index = 0;
		for (int i = 0; i < (layers.length); i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				g2d.drawOval((int)layers[i].getNeuron(j).getPosX(), (int)layers[i].getNeuron(j).getPosY(), 50, 50);
			}
		}
		for (int i = 0; i < connections.length; i++){
			if (connections[i].getDendriteWeight() >= 5){
				g2d.setColor(Color.black);
			}
			else g2d.setColor(Color.green);
				g2d.drawLine((int)connections[i].getInputNeuron().getPosX() + 25,
							 (int)connections[i].getInputNeuron().getPosY() + 25,
							 (int)connections[i].getOutputNeuron().getPosX() + 25,
							 (int)connections[i].getOutputNeuron().getPosY() + 25);
			index = index + 1;
		}
	}
	
	public void outPutNeuralNetwork(){
		int index = 0;
		for (int i = 0; i < (layers.length-1); i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				for (int k = 0; k < layers[i+1].getAmountOfNeurons(); k++){
				System.out.println(layers[i].getNeuron(j).getNeuronName() + "   " + layers[i].getLayerName());
				System.out.println(layers[i+1].getNeuron(k).getNeuronName() + "   " + layers[i+1].getLayerName());
				System.out.printf("%.3f\n",connections[index].getDendriteWeight());
				index = index + 1;
				}
			}
		}
	}
	
	public void runNeuralNetwork(Graphics2D g2d){
		layers[0].getNeuron(0).setNeuronWeight(line1_input/50);
		layers[0].getNeuron(1).setNeuronWeight(line2_input/50);
		layers[0].getNeuron(2).setNeuronWeight(line3_input/50);
		layers[0].getNeuron(3).setNeuronWeight(line4_input/50);
		layers[0].getNeuron(4).setNeuronWeight(line5_input/50);
		for (int i = 1; i < layers.length; i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				layers[i].getNeuron(j).setNeuronWeight(layers[i].getNeuron(j).activation_function());
			}	
		}
		for (int i = 0; i < layers.length; i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				String result = String.format("%.2f", layers[i].getNeuron(j).getNeuronWeight());
				g2d.setColor(Color.white.brighter());
				g2d.drawString(result,layers[i].getNeuron(j).getPosX() + 25 , layers[i].getNeuron(j).getPosY() + 25);
			}
		}
	}

	public void evolvePopulation(){
		Population newPopulation = new Population(15,connections.length,false);
		newPopulation.storeGene(0, network.getPop().getFittest());
		for (int i = 1; i < network.getPop().getPopSize(); i++){
			Gene gene1 = algorithm.breedSelection(network.getPop());
			Gene gene2 = algorithm.breedSelection(network.getPop());
			Gene bredGene = algorithm.crossOver(gene1, gene2, 3);
			bredGene = algorithm.mutateGene(bredGene);
			newPopulation.storeGene(i, bredGene);
		}
		for (int i = 0; i < layers.length; i++){
			for (int j = 0; j < layers[i].getAmountOfNeurons(); j++){
				layers[i].getNeuron(j).removeConnections();
			}
		}
		network.setPop(newPopulation);
	}
	
	public void checkLineCollision(Line2D line, Rectangle.Float rec, Graphics g2d){
		if (line.intersects(rec)){
			double x = line.getX2() - line.getX1();
			double y = line.getY2() - line.getY1();
			double intx = 0;
			double inty = 0;
			double slope = y/x;

			con = true;
			
			while (con == true){
				
			  if (x != 0){
				if (rec.contains(line.getX2() - intx, line.getY2() - (slope*intx))){
					if (x < 0)
						intx = intx - .1;
					else 
						intx = intx + .1;
				}
				else{
					con = false;
					if (line == this.line){
						line1_input = Math.sqrt(Math.pow(line.getX2() - intx - line.getX1(),2) + Math.pow(line.getY2() - (slope*intx) - line.getY1(),2));
					}
					else if (line == this.line2){
						line2_input = Math.sqrt(Math.pow(line.getX2() - intx - line.getX1(),2) + Math.pow(line.getY2() - (slope*intx) - line.getY1(),2));
					}
					else if (line == this.line3){
						line3_input = Math.sqrt(Math.pow(line.getX2() - intx - line.getX1(),2) + Math.pow(line.getY2() - (slope*intx) - line.getY1(),2));
					}
					else if (line == this.line4){
						line4_input = Math.sqrt(Math.pow(line.getX2() - intx - line.getX1(),2) + Math.pow(line.getY2() - (slope*intx) - line.getY1(),2));
					}
					else if (line == this.line5){
						line5_input = Math.sqrt(Math.pow(line.getX2() - intx - line.getX1(),2) + Math.pow(line.getY2() - (slope*intx) - line.getY1(),2));
					}
					g2d.setColor(Color.red);
					//Shape l = new Line2D.Double(car.x + car.getWidth()/2, car.y + car.getHeight()/2, line.getX2() - intx, line.getY2() - (slope*intx));
					//((Graphics2D) g2d).draw(l);
					}
			  }
			  else if (x == 0){
				  if(rec.contains(line.getX2(), line.getY2() - inty)){
					  if (y < 0)
						  	inty = inty - .1;
					  else if (y > 0)
					  		inty = inty + .1;
				  }
				  else{ 
					  con = false;
					  if (line == this.line){
						  line1_input = Math.abs(line.getY2() - line.getY1() - inty);
					  }
					  else if (line == this.line2){
						  line2_input = Math.abs(line.getY2() - line.getY1() - inty);
					  }
					  else if (line == this.line3){
						  line3_input = Math.abs(line.getY2() - line.getY1() - inty);
					  }
					  else if (line == this.line4){
						  line4_input = Math.abs(line.getY2() - line.getY1() - inty);
					  }
					  else if (line == this.line5){
						  line5_input = Math.abs(line.getY2() - line.getY1() - inty);
					  }
				  	  g2d.setColor(Color.red);
				  	  //Shape l = new Line2D.Double(car.x + car.getWidth()/2, car.y + car.getHeight()/2, line.getX2(), line.getY2() - inty);
				  	  //((Graphics2D) g2d).draw(l);
				  		}
				  }
			}
			
		}
	}
	
	
}
