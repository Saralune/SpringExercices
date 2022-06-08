package fr.fms;

import java.util.Scanner;

public class MyShopApp {
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue sur notre application de vente de livres neufs et d'occasion.");
		
		int choice = 0;
		while(choice != 7) {
			displayMenu();
			
			choice = scanInt();
			
			switch(choice) {
				case 1:
					
					break;
					
				case 2:
					
					break;
					
				case 3: 
					
					break;
					
				case 4:
					
					break;
					
				case 5:
					
					break;
					
				case 6:
					
					break;
					
				case 7:
					System.out.println("Au revoir !");
					break;
			}
		}	
	}

	/**
	 * Print menu choices 
	 */
	public static void displayMenu() {	
		System.out.println("\nQue souhaitez-vous faire ?");
		System.out.println("1. ");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. Quitter l'application.");
	}
	
	/**
	 * Check if answer of user is an int
	 * @return answer in scan 
	 */
	public static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("Merci de saisir un nombre.");
			scan.next();
		}
		
		return scan.nextInt();
	}
}