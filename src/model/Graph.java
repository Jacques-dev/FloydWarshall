package model;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	private final int nbNode;
	private final int nbArc;
	private final ArrayList<Peer> peers;
	private final ArrayList<Node> nodes;
	private final int[][] matrice;
	
	public Graph(int nbNode, int nbArc, ArrayList<Peer> peers, ArrayList<Node> nodes, int[][] matrice) {
		this.nbNode = nbNode;
		this.nbArc = nbArc;
		this.peers = peers;
		this.nodes = nodes;
		this.matrice = matrice;
	}
	
	public void add(Peer peer) {
		peers.add(peer);
		
		ArrayList<Node> tab = peer.getNodes();
		
		if (!nodes.contains(tab.get(0)) && !nodes.contains(tab.get(1))) {
			nodes.add(tab.get(0));
			nodes.add(tab.get(1));
		}
		
		Collections.sort(peers);
		Collections.sort(nodes);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Peer p : peers) {
			s.append(p);
		}
		return s.toString();
	}
	
	public int getSize() {
		return getNbNode();
	}
	
	public int getNbNode() {
		return nbNode;
	}

	public int getNbArc() {
		return nbArc;
	}

	public ArrayList<Peer> getPeers() {
		return peers;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	public int[][] getMatrice() {
		return matrice;
	}
}
