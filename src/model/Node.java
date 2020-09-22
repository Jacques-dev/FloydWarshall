package model;

import java.util.Objects;

public class Node implements Comparable<Node>{
	private final int id;
	
	public Node(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return Integer.toString(id);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Node)) {return false;}
		Node n = (Node) o;
		return id == n.id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public int compareTo(Node n) {
		return this.id - n.id;
	}
}
