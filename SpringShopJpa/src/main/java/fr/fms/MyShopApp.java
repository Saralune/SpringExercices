package fr.fms;

import java.util.Collections;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.IMyShopAppImpl;

@SpringBootApplication
public class MyShopApp {	
	private static Scanner scan = new Scanner(System.in);
	private static IMyShopAppImpl business = new IMyShopAppImpl();
	
	public static void main(String[] args) {
		SpringApplication.run(MyShopApp.class, args);
		
		System.out.println("Bonjour.");

		int choice = 0;
		while (choice != 6) {
			displayMenu();

			choice = scanInt();

			switch (choice) {
			case 1:
				displayArticles();
				break;

			case 2:
				displayArticlesByPage();
				break;

			case 3:
				menuArticles();
				break;

			case 4:
				displayCRUDMenu("une catégorie");
				break;

			case 5:

				break;

			case 6:
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
		System.out.println("[1] -- Afficher tous les articles en base. ");
		System.out.println("[2] -- Afficher tous les articles (par pages). ");
		System.out.println("[3] -- Gérer un article. ");
		System.out.println("[4] -- Gérer une catégorie. ");
		System.out.println("[5] -- Afficher tous les articles d'une catégorie ");
		System.out.println("[6] --  Quitter l'application.");
	}

	/**
	 * Print CRUD choices
	 */
	public static void displayCRUDMenu(String name) {
		System.out.println("\nQue souhaitez-vous faire ?");
		System.out.println("[1] -- Ajouter " + name + ". ");
		System.out.println("[2] -- Supprimer " + name + ". ");
		System.out.println("[3] -- Mettre à jour " + name + ". ");
		System.out.println("[4] -- Afficher le détail d'" + name + ". ");
		System.out.println("[0] --  Retour à l'accueil.");
	}

	/**
	 * Check if answer of user is an int
	 * 
	 * @return answer in scan
	 */
	public static int scanInt() {
		while (!scan.hasNextInt()) {
			System.out.println("Merci de saisir un nombre.");
			scan.next();
		}

		return scan.nextInt();
	}

	/**
	 * print all article from DB
	 */
	public static void displayArticles() {
		String format = "%1$-4s | %2$-20s | %3$-30s | %4$-8s |\n";

		System.out.println(String.join("", Collections.nCopies(73, "-")));
		System.out.format(format, "REF", "DESCRIPTION", "MARQUE", "PRIX");
		System.out.format(format, "----", String.join("", Collections.nCopies(20, "-")),
				String.join("", Collections.nCopies(30, "-")), "--------");

		 business.readArticles().forEach((n) ->
			 System.out.format(format, n.getId(), n.getDescription(), n.getBrand(), 
			 n.getPrice() + "€" )); 
		 //+ "\n" + String.join("", Collections.nCopies(71, "-"))

		System.out.println(String.join("", Collections.nCopies(73, "-")));
	}
	
	/**
	 * 
	 */
	public static void menuArticles() {
		int choice = 5;
		
		while(choice != 0) {
			displayCRUDMenu("un article");
			choice = scanInt();
			
			switch (choice) {
				case 0:
					break;

				case 1:
					System.out.println("case 1");
					break;
					
				case 2:
					System.out.println("case 2");
					break;
					
				case 3:
					System.out.println("3");
					break;
					
				case 4:
					System.out.println('4');
					break;
			}
		}
	}

	/**
	 * print all articles from DB by page
	 */
	public static void displayArticlesByPage() {
		
	}
}