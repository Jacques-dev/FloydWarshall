package test;


import java.util.Scanner;

import controler.Controler;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		      
    	try {
    		
    		while( true ) {
    			
	    		@SuppressWarnings("resource")
//				Scanner scanner = new Scanner(System.in);
//	            System.out.print( "Entrer le nom du fichier (ex : test.txt): " );
//	            String file = scanner.nextLine();
	            
				Controler data = new Controler("test4.txt");
				
				data.print();
				
				if (data.isNotAnAbsorberCircuit()) {
					System.out.println("--Algorithme de FloydWarshall--");
					data.FloydAlgoritm();
				} else {
					System.out.println("Ce graph poss�de un circuit...");
				}
				
				return;
    		}
		
    	} catch(Exception e) {
    		System.out.println("");
    	}
	}

}
