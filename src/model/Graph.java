package model;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	private int nbNode;
	private int nbArc;
	private ArrayList<Peer> peers;
	private ArrayList<Node> nodes;
	
	public Graph() {
		this.peers = new ArrayList<Peer>();
		this.nodes = new ArrayList<Node>();
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
		return nodes.size();
	}
	
	public int getNbNode() {
		return nbNode;
	}

	public void setNbNode(int nbNode) {
		this.nbNode = nbNode;
	}

	public int getNbArc() {
		return nbArc;
	}

	public void setNbArc(int nbArc) {
		this.nbArc = nbArc;
	}

	public void setPeers(ArrayList<Peer> peers) {
		this.peers = peers;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Peer> getPeers() {
		return peers;
	}
	
	public ArrayList<Node> getNodes() {
		return nodes;
	}
}
