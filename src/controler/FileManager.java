package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import model.Arc;
import model.Graph;
import model.Node;
import model.Peer;

public class FileManager {

	/**
	Read permit to read a text file and give the corresponding graph
	@param file : a string given by the user at the begining of the program which has to correspond to a real text file
    @return the graph which one will be used for the rest of the instruction of the program
	*/
	public static Graph read(String file) throws IOException {
		
		BufferedReader text = null;
		
		try {
			text = new BufferedReader(new FileReader("Graphs/"+file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		
		
		String line;
		int lineIndex = 0;//selection of the line searched
		int nbNode = 0, nbArc = 0;
		ArrayList<Peer> peers = new ArrayList<Peer>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		while ((line = text.readLine()) != null) {
			switch (lineIndex) {
				case 0:
					nbNode = Integer.parseInt(line);
					break;
				case 1:
					nbArc = Integer.parseInt(line);
					break;
				default:
					peers = addPeer(peers, line);
					nodes = addNode(nodes, line);
					break;
			}
			lineIndex +=1;
		}
		text.close();
		
		int[][] matrice = setMatrice(nbNode,peers);
		
		Graph g = new Graph(nbNode,nbArc,peers,nodes,matrice);
		
		return g;
	}
	
	/**
	addPeer is used to update an ArrayList of peers
	@param peers : an ArrayList of peers which one will be updated
	@param line : a String which will be used to extract from it a Peer
    @return the ArrayList given in parameter updated with a new Peer
	*/
	private static ArrayList<Peer> addPeer(ArrayList<Peer> peers, String line) {
		peers.add(readPeer(line));
		
		Collections.sort(peers);
		
		return peers;
	}
	
	/**
	addNode is used to update an ArrayList of nodes
	@param nodes : an ArrayList of nodes which one will be updated
	@param line : a String which will be used to extract from it a Node
    @return the ArrayList given in parameter updated with a new Node
	*/
	private static ArrayList<Node> addNode(ArrayList<Node> nodes, String line) {
		ArrayList<Node> tab = readPeer(line).getNodes();
		
		if (!nodes.contains(tab.get(0))) {
			nodes.add(tab.get(0));
		}
		
		if (!nodes.contains(tab.get(1)) ){
			nodes.add(tab.get(1));
		}
		
		Collections.sort(nodes);
		
		return nodes;
	}

	/**
	readPeer is used to read a String extracted from the text file and give a Peer
	@param line : a String 
    @return a Peer
	*/
	private static Peer readPeer(String line) {
		ArrayList<String> tab = new ArrayList<String>(Arrays.asList(line.split(" ")));
		
		Arc arc = new Arc(Integer.parseInt(tab.get(2))); //we get the arc of those two nodes
		tab.remove(2); //we remove this arc to add them in the graph
		
		Node n1 = new Node(Integer.parseInt(tab.get(0)));
		
		Node n2 = new Node(Integer.parseInt(tab.get(1)));
		
		Peer p = new Peer(arc, n1, n2);
		
		return p;
	}
	
	/**
	setMatrice create a adjacent matrice corresponding to a graph
	@param nbNode : the number of nodes contained in a graph
	@param peers : the set of peers in a graph
    @return a second degree tab of int which look's like the matrice of a graph
	*/
	private static int[][] setMatrice(int nbNode, ArrayList<Peer> peers) {
		int[][] mat = new int[nbNode][nbNode];
		
		for (int i = 0 ; i < nbNode ; i++) {
			for (int j = 0 ; j < nbNode ; j++) {
				for (int k = 0 ; k < peers.size() ; k++) {
					Node n1 = peers.get(k).getNodes().get(0);
					Node n2 = peers.get(k).getNodes().get(1);
					
					if (n1.getId() == i && n2.getId() == j) {
						mat[i][j] = 1;
						break;
					} else {
						mat[i][j] = 0;
					}
				}
			}
		}
		
		return mat;
	}

	
}
