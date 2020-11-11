package model;

import java.util.ArrayList;
import java.util.Objects;

public class Node implements Comparable<Node>{
	private final int id;
	
	/**
	A node is a component which can be connected to an other by an Arc to form a graph
    @param id : a node is define by an id/degree
	*/
	public Node(int id) {
		this.id = id;
	}
	
	/**
    @return the id/degree of the node
	*/
	public int getId() {
		return id;
	}
	
	/**
    @return a String of the id
	*/
	public String toString() {
		return Integer.toString(id);
	}
	
	/**
	@param o : is an object with no type defined
    @return a boolean which significate if this Node is equal to another one
	*/
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Node)) {return false;}
		Node n = (Node) o;
		return id == n.id;
	}
	
	/**
    @return a hash code value for the object
	*/
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	/**
	Compares this object with the specified object for order.
	@param n : the object to be compared.
    @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	*/
	@Override
	public int compareTo(Node n) {
		return this.id - n.id;
	}

	public ArrayList<Node> getSuccessor(int[][] matrice, int id, ArrayList<Node> nodes) {
		ArrayList<Node> list = new ArrayList<Node>();
		for (int i = 0; i < matrice.length; i++) {
			if (matrice[id][i] == 1) {
				
				for (Node n : nodes) {
					if (n.getId() == i) list.add(n);
				}
			}
		}
		if (list.size() == 0) {
			return null;
		}
		return list;
	}
	
	public Arc isAPeer(int[][] matrice, int[][] matriceValeur, Node n2) {
		if (matrice[id][n2.getId()] == 1) {
			return new Arc(matriceValeur[id][n2.getId()]);
		}
		return null;
	}
}
