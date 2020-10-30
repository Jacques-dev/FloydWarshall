package model;

import java.util.Objects;

public class Arc {
	private final int value;
	
	/**
	An arc is the "link" between two nodes
    @param value an arc is define by a value
	*/
	public Arc(int value) {
		this.value = value;
	}
	
	/**
    @return the value of the arc
	*/
	public int getValue() {
		return value;
	}
	
	/**
    @return the String of the value
	*/
	public String toString() {
		return Integer.toString(value);
	}
	
	/**
	@param o : is an object with no type defined
    @return a boolean which significate if this Arc is equal to another one
	*/
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Arc)) {return false;}
		Arc a = (Arc) o;
		return value == a.value;
	}
	
	/**
    @return a hash code value for the object
	*/
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
