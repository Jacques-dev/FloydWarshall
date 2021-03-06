package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Graph;
import model.Node;
import model.Peer;

public class Controler {

	private Graph graph;
	private String[][] paths;
	
	public String[][] getPaths() {
		return paths;
	}

	public void setPaths(String[][] paths) {
		this.paths = paths;
	}

	/**
	Controler is the controler of the program, his methodes are used in the Main 
	@param file : a text file contanining a graph to study
	*/
	public Controler(String file) {
		try {
			this.graph = FileManager.read(file);
			paths = new String[graph.getSize()][graph.getSize()];
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
		
		System.out.println();
		
		int[][] matrice = graph.getMatrice();
		
		s.append("  ");
		for (Node n : nodes) {
			s.append(n.getId() + " ");
		}
		
		s.append("\n");
		
		for (int i = 0 ; i < size ; i++) {
			s.append(i + " ");
			for (int j = 0 ; j < size ; j++) {
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
			System.out.print( "Montrer tous les plus courts chemins : y |Tester chemin par chemin : n\n" );
			Scanner scanner = new Scanner(System.in);
			if (scanner.nextLine().equals("n")) {
				OneByOne(FloydWarshall());
						
				System.out.print( "Essayer un autre chemin y/n : " );
				Scanner scanner2 = new Scanner(System.in);
				while (scanner2.nextLine().equals("y")) {
					OneByOne(FloydWarshall());
					System.out.print( "Essayer un autre chemin y/n : " );
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
		int graphValue[][] = getMatriceValeur();

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
				if (theRouteIsAppropriate(floydWarshallGraph[i][j])) {
					s.append("x\t");
				}
				else {
					s.append(floydWarshallGraph[i][j] + "\t");
				}
			}
			s.append("\n");
		}
		s.append("\n");
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!paths[i][j].equals("x") && i!=j) {
					int x = Integer.valueOf((paths[i][j].substring(paths[i][j].length() - 2, paths[i][j].length() - 1)));
					s.append(paths[i][j] + " --> " + graphValue[x][j] + " --> (" + j + ")\n");
				}
			}
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
		int graphValue[][] = getMatriceValeur();
		
		Scanner scanner = new Scanner(System.in);
        System.out.print( "Entrer le premier noeud : " );
        int n1 = Integer.parseInt(scanner.nextLine());
        while (n1 >= size || n1 < 0) {
        	System.out.println("Vous avez entrer un noeud hors champ!\nEntrer le premier noeud : ");
        	n1 = Integer.parseInt(scanner.nextLine());
        }
        Scanner scanner2 = new Scanner(System.in);
        System.out.print( "Entrer le second noeud : " );
        int n2 = Integer.parseInt(scanner2.nextLine());
        while (n2 >= size || n2 < 0) {
        	System.out.println("Vous avez entrer un noeud hors champ!\nEntrer le second noeud : ");
        	n2 = Integer.parseInt(scanner2.nextLine());
        }
        
        StringBuilder s = new StringBuilder();
        
        if (theRouteIsAppropriate(floydWarshallGraph[n1][n2])) {
        	s.append("\nCe chemin n'existe pas...\n\n");
        }
        else {
        	int x = Integer.valueOf((paths[n1][n2].substring(paths[n1][n2].length() - 2, paths[n1][n2].length() - 1)));
        	s.append(paths[n1][n2] + " --> " + graphValue[x][n2] + " --> (" + n2 + ")\n");
        }
		
		System.out.println(s.toString());
		
	}
	
	/**
	Use the algorithm of FloydWarshall to search for the shortest route between two points 
	@return a second degree tab of int
	*/
	public int[][] FloydWarshall() {
		int size = graph.getSize();
		int graphValue[][] = getMatriceValeur();
		int graphStaticValue[][] = getMatriceValeur();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				paths[i][j] = "x";
				if (graphValue[i][j] != 9999) {
					paths[i][j] = "(" + Integer.toString(i) + ")";
				}
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					
					
					if (graphValue[j][i] + graphValue[i][k] < graphValue[j][k]) {
						if (graphValue[j][i] == 9999 || graphValue[i][k] == 9999) {
							
						} else {
							graphValue[j][k] = graphValue[j][i] + graphValue[i][k];
							
							int start = Integer.valueOf((paths[j][i].substring(paths[j][i].length() - 2, paths[j][i].length() - 1)));
							int end = Integer.valueOf((paths[i][k].substring(1, 2)));
							
							paths[j][k] = paths[j][i] + " --> " + graphStaticValue[start][end] + " --> " + paths[i][k];
						}
					}
				}
			}
		}
		return graphValue;
	}
	
	/**
	Check if the route can be used in the calculations for the FloydWarshall algorithm
	@return a boolean which indicate if the next route can be taking in account
	*/
	private boolean theRouteIsAppropriate(int x) {
		return x == 9999;
	}

	/**
	Check if the graph contains absorbant circuits
	*/
	public boolean isNotAnAbsorberCircuit() {
		
		int[][] matrice = FloydWarshall();
		
		for (int i = 0; i < matrice.length; i++) {
			for(int j = 0; j < matrice.length; j++) {
				if (matrice[i][j] < 0 && i == j) {
					return false;
				}
			}
		}
		
		return true;
	}

}
