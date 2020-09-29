package model;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	private final int nbNode;
	private final int nbArc;
	private final ArrayList<Peer> peers;
	private final ArrayList<Node> nodes;
	private final int[][] matrice;
	
	/**
    A graph is a set of Nodes linked by Arcs and will give us matrices
    @param nbNode : the number of node found in the graph
    @param nbArc : the number of arcs found in the graph
    @param peers : all the peer in the graph
    @param nodes : all the nodes in gthe graph
    @param matrice : the graph represented by an adjacence matrice
	*/
	public Graph(int nbNode, int nbArc, ArrayList<Peer> peers, ArrayList<Node> nodes, int[][] matrice) {
		this.nbNode = nbNode;
		this.nbArc = nbArc;
		this.peers = peers;
		this.nodes = nodes;
		this.matrice = matrice;
	}
	
	/**
    @return a StringBuilder containing all the peers in the graph
	*/
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Peer p : peers) {
			s.append(p);
		}
		return s.toString();
	}
	
	/** 
    @return the number of nodes that the graph contains
	*/
	public int getSize() {
		return nbNode;
	}

	/** 
    @return the number of arcs that the graph contains
	*/
	public int getNbArc() {
		return nbArc;
	}

	/** 
    @return an ArrayList which is composed by all the peers in the graph
	*/
	public ArrayList<Peer> getPeers() {
		return peers;
	}
	
	/** 
    @return an ArrayList which is composed by all the nodes in the graph
	*/
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	/** 
    @return an adjacent matrice initiate in the constructore corresponding to the graph
	*/
	public int[][] getMatrice() {
		return matrice;
	}
}
