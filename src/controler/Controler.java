package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Graph;
import model.Node;
import model.Peer;

public class Controler {

	private Graph graph;
	
	public Controler() {
		this.graph = new Graph();
	}
	
	public void start(String file) {
		try {
			graph = FileManager.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		return graph.toString();
	}
	
	public void print() {
		fileView();
		matriceAdjacence();
		patriceValeur();
	}

	private void fileView() {
		System.out.println("-----GRAPH-----\n");
		System.out.println(toString());
		System.out.println("---------------\n");
	}

	private void matriceAdjacence() {
		StringBuilder s = new StringBuilder();
		
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
						matrice[i][j] = 1;
						break;
					} else {
						matrice[i][j] = 0;
					}
				}
			}
		}
		
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
	
	private void patriceValeur() {
		StringBuilder s = new StringBuilder();
		
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
						matrice[i][j] = 0;
					}
				}
			}
		}
		
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
		
		System.out.println("----VALEURS----\n");
		System.out.println(s.toString());
		System.out.println("---------------\n");
	}

	

	public void FloydAlgoritm() {
		try {
			System.out.print( "Show all the route y/n : " );
			Scanner scanner = new Scanner(System.in);
			if (scanner.nextLine().equals("n")) {
				OneByOne();
			} else {
				AllOfThem();
			}
    	} catch(Exception e) {
    		System.out.println("");
    	}
	}
	
	private void AllOfThem() {
		
		
	}

	private void OneByOne() {
		Scanner scanner = new Scanner(System.in);
        System.out.print( "Enter the first node : " );
        int n1 = Integer.parseInt(scanner.nextLine());
        System.out.print( "Enter the second node : " );
        int n2 = Integer.parseInt(scanner.nextLine());
        
        
		
	}
	
	public void FloydWarshall() {
		
	}

	public boolean isTheGraphAnAbsorberCircuit() {
		
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

	public void showRoute() {
		// TODO Auto-generated method stub
		
	}

}
