package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Graph;
import model.Node;
import model.Peer;

public class Controler {

	private Graph graph;
	
	/**
	Controler is the controler of the program, his methodes are used in the Main 
	@param file : a text file contanining a graph to study
	*/
	public Controler(String file) {
		try {
			this.graph = FileManager.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	@return a String which show the graph studied
	*/
	public String toString() {
		return graph.toString();
	}
	
	/**
	print is used in the Main, it show three different version of the graph
	*/
	public void print() {
		fileView();
		matriceAdjacence();
		matriceValeur();
	}

	/**
	fileView is the basic view of the graph, it show's the different peers created
	*/
	private void fileView() {
		System.out.println("-----GRAPH-----\n");
		System.out.println(toString());
		System.out.println("---------------\n");
	}

	/**
	matriceAdjacence show the adjacent matrice of the graph
	*/
	private void matriceAdjacence() {
		StringBuilder s = new StringBuilder();
		
		ArrayList<Node> nodes = graph.getNodes();
		
		int size = nodes.size();
		
		int[][] matrice = graph.getMatrice();
		
		s.append("  ");
		for (Node n : nodes) {
			s.append(n.getId() + " ");
		}
		
		s.append("\n");
		
		for (int i = 0 ; i != size ; i++) {
			s.append(i + " ");
			for (int j = 0 ; j != size ; j++) {
				s.append(matrice[i][j] + " ");
			}
			s.append("\n");
		}
		
		System.out.println("---ADJACENCE---\n");
		System.out.println(s.toString());
		System.out.println("---------------\n");
	}
	
	/**
	matriceValeur show the value matrice of the graph
	*/
	private void matriceValeur() {
		StringBuilder s = new StringBuilder();
		
		ArrayList<Node> nodes = graph.getNodes();
		
		int size = nodes.size();
		
		int[][] matrice = getMatriceValeur();
		
		
		s.append("\t");
		for (Node n : nodes) {
			s.append(n.getId() + "\t");
		}
		
		s.append("\n");
		
		for (int i = 0 ; i != size ; i++) {
			s.append(i + "\t");
			for (int j = 0 ; j != size ; j++) {
				if (matrice[i][j] == 9999) {
					s.append("NULL\t");
				}
				else {
					s.append(matrice[i][j] + "\t");
				}
			}
			s.append("\n");
		}
		
		System.out.println("----VALEURS----\n");
		System.out.println(s.toString());
		System.out.println("---------------\n");
	}
	
	/**
	Give a matrice containing the value of each link/Arc between nodes of the graph
	@return a second degree tab of int
	*/
	private int[][] getMatriceValeur() {
		ArrayList<Peer> peers = graph.getPeers();
		
		ArrayList<Node> nodes = graph.getNodes();
		
		int size = nodes.size();
		
		int[][] matrice = new int[size][size];
		
		for (int i = 0 ; i != size ; i++) {
			for (int j = 0 ; j != size ; j++) {
				for (int k = 0 ; k != peers.size() ; k++) {
					Node n1 = peers.get(k).getNodes().get(0);
					Node n2 = peers.get(k).getNodes().get(1);
					
					if (n1.getId() == i && n2.getId() == j) {
						matrice[i][j] = peers.get(k).getArc().getValue();
						break;
					} else {
						matrice[i][j] = 9999;
					}
				}
			}
		}
		
	return matrice;
	}

	/**
	Give the choice to the user to chose if he/she wants to have all the shortest route in the graph between each nodes 
	or just check one route of his/her choice
	*/
	@SuppressWarnings("resource")
	public void FloydAlgoritm() {
		try {
			System.out.print( "Show all the route ? y/n : " );
			Scanner scanner = new Scanner(System.in);
			if (scanner.nextLine().equals("n")) {
				OneByOne(FloydWarshall());
						
				System.out.print( "Try an other route ? y/n : " );
				Scanner scanner2 = new Scanner(System.in);
				while (scanner2.nextLine().equals("y")) {
					OneByOne(FloydWarshall());
					System.out.print( "Try an other route ? y/n : " );
					scanner2 = new Scanner(System.in);
				}
			} else {
				AllOfThem(FloydWarshall());
			}
    	} catch(Exception e) {
    		System.out.println("");
    	}
		System.out.println("\n\n\n");
	}
	
	/**
	Show all the shortest route in the graph between each nodes
	*/
	private void AllOfThem(int floydWarshallGraph[][]) {
		int size = graph.getSize();

		StringBuilder s = new StringBuilder();
		
		ArrayList<Node> nodes = graph.getNodes();
		
		s.append("\t");
		for (Node n : nodes) {
			s.append(n.getId() + "\t");
		}
		
		s.append("\n\n");
		
		for (int i = 0; i < size; i++) {
			s.append(i + "\t");
			for (int j = 0; j < size; j++) {
				if (floydWarshallGraph[i][j] == 9999) {
					s.append("x\t");
				}
				else {
					s.append(floydWarshallGraph[i][j] + "\t");
				}
			}
			s.append("\n");
		}
		
		System.out.println("----Floyd Warshall----\n");
		System.out.println(s.toString());
		System.out.println("----------------------\n");
	}

	/**
	Ask the user for which shortest route he/she want to see and do it
	*/
	@SuppressWarnings("resource")
	private void OneByOne(int floydWarshallGraph[][]) {
		int size = graph.getSize();
		Scanner scanner = new Scanner(System.in);
        System.out.print( "Enter the first node : " );
        int n1 = Integer.parseInt(scanner.nextLine());
        while (n1 >= size) {
        	System.out.println("You typed an out of bound number!\nEnter the first node : ");
        	n1 = Integer.parseInt(scanner.nextLine());
        }
        System.out.print( "Enter the second node : " );
        int n2 = Integer.parseInt(scanner.nextLine());
        while (n2 >= size) {
        	System.out.println("You typed an out of bound number!\nEnter the second node : ");
        	n2 = Integer.parseInt(scanner.nextLine());
        }
        
        StringBuilder s = new StringBuilder();
		
        s.append("(" + n1 + ") - " + (floydWarshallGraph[n1][n2] == 9999 ? "path does not exist" : floydWarshallGraph[n1][n2]) + " -> (" + n2 + ")");
        s.append("\n");
		
		System.out.println(s.toString());
		
	}
	
	/**
	Use the algorithm of Floyd Marshall to search for the shortest route between two points 
	@return a second degree tab of int
	*/
	public int[][] FloydWarshall() {
		int size = graph.getSize();
		int graphValue[][] = getMatriceValeur();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if (theRouteIsAppropriate(graphValue[j][k])) {
						if (graphValue[j][i] + graphValue[i][k] < graphValue[j][k]) {
							graphValue[j][k] = graphValue[j][i] + graphValue[i][k];
						}
					}
				}
			}
		}
		
		return graphValue;
	}
	
	/**
	Check if the route can be used in the calculations for the Floyd Marshall algorithm
	@return a boolean which indicate if the next route can be taking in account
	*/
	private boolean theRouteIsAppropriate(int x) {
		return x != 9999;
	}

	/**
	Check if the graph contains circuits
	@return true or false
	*/
	public boolean isNotAnAbsorberCircuit() {
		
		boolean isAbsorber = false;
		
		int counter = 0;
		
		ArrayList<Peer> list = graph.getPeers();
		
		for (Peer p : list) {
			counter += p.getArc().getValue();
		}
		
		if (counter > 0) {
			isAbsorber = true;
		}
		
		return isAbsorber;
	}

}
