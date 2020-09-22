package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Arc;
import model.Graph;
import model.Node;
import model.Peer;

public class FileManager {

	public static Graph read(String file) throws IOException {
		
		BufferedReader text = null;
		
		Graph g = new Graph();
		
		try {
			text = new BufferedReader(new FileReader("Graphs/"+file));
		} catch (FileNotFoundException e) {
			System.out.println("File not found !");
		}
		
		
		String line;
		int lineIndex = 0;//to avoid to read the two first lines, cause we don't need them.
		
		while ((line = text.readLine()) != null) {
			//if (lineIndex > 1) {
			//	g.add(readNode(line));
			//}
			switch (lineIndex) {
				case 0:
					g.setNbNode(Integer.parseInt(line));
					break;
				case 1:
					g.setNbArc(Integer.parseInt(line));
					break;
				default:
					g.add(readNode(line));
					break;
			}
			lineIndex +=1;
		}
		text.close();
		
		
		return g;
	}
	
	

	private static Peer readNode(String line) {
		ArrayList<String> tab = new ArrayList<String>(Arrays.asList(line.split(" ")));
		
		Arc arc = new Arc(Integer.parseInt(tab.get(2))); //we get the arc of those two nodes
		tab.remove(2); //we remove this arc to add them in the graph
		
		Node n1 = new Node(Integer.parseInt(tab.get(0)));
		
		Node n2 = new Node(Integer.parseInt(tab.get(1)));
		
		Peer p = new Peer(arc, n1, n2);
		
		return p;
	}
}
