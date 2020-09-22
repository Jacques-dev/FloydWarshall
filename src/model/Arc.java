package model;

import java.util.Objects;

public class Arc {
	private final int value;
	
	public Arc(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return Integer.toString(value);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Arc)) {return false;}
		Arc a = (Arc) o;
		return value == a.value;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
