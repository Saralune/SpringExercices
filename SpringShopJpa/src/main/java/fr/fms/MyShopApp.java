package fr.fms;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.business.IMyShopAppImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class MyShopApp implements CommandLineRunner {	
	private static Scanner scan = new Scanner(System.in);
	
	@Autowired
	private IMyShopAppImpl business;
	
	public static void main(String[] args) {
		SpringApplication.run(MyShopApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
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
		System.out.println("[1] -- Afficher tous les articles. ");
		System.out.println("[2] -- Afficher tous les articles (par pages). ");
		System.out.println("[3] -- Gérer un article. ");
		System.out.println("[4] -- Gérer une catégorie. ");
		System.out.println("[5] -- Afficher tous les articles d'une catégorie.");
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
	public void displayArticles() {
		String format = "%1$-4s | %2$-35s | %3$-30s | %4$-8s |\n";
		List<Object[]> allArticles = business.readArticles();

		System.out.println(String.join("", Collections.nCopies(88, "-")));
		System.out.format(format, "REF", "DESCRIPTION", "MARQUE", "PRIX");
		System.out.format(format, "----", String.join("", Collections.nCopies(35, "-")),
				String.join("", Collections.nCopies(30, "-")), "--------");

		 allArticles.forEach((n) ->
			 System.out.format(format, n[0], n[1], n[2], n[3] + "€" )); 
		 //+ "\n" + String.join("", Collections.nCopies(71, "-"))

		System.out.println(String.join("", Collections.nCopies(88, "-")));
	}
	
	public void displayCategories() {
		String format = "%1$-4s | %2$-35s\n";
		List<Category> allCat = business.readCat();
		
		System.out.println(String.join("", Collections.nCopies(42, "-")));
		System.out.format(format, "REF", "NOM");
		allCat.forEach((n) ->
		 System.out.format(format, n.getId(), n.getName())); 
		System.out.println(String.join("", Collections.nCopies(42, "-")));
	}
	
	/**
	 * 
	 */
	public void menuArticles() {
		int choice = 5;
		
		while(choice != 0) {
			displayCRUDMenu("un article");
			choice = scanInt();
			
			switch (choice) {
				case 0:
					break;

				case 1: //ajouter //ok
					addOneArticle();
					break;
					
				case 2: //supprimer	 //ok
					deleteOneArticle();
					break;
					
				case 3: //mettre à jour //ok
					updateOneArticle();
					break;
					
				case 4: //afficher //ok
					displayOneArticle();
					break;
			}
		}
	}
	
	public void displayOneArticle() {
		String format = "%1$-4s | %2$-30s | %3$-30s | %4$-8s |\n";
		
		System.out.println("Veuillez saisir l'id de l'article à afficher.");
		
		int id = scanInt();
		Article article = business.getArticleById(id);

		if(article != null) {
			System.out.println(String.join("", Collections.nCopies(83, "-")));
			System.out.format(format, "REF", "DESCRIPTION", "MARQUE", "PRIX");
			System.out.format(format, "----", String.join("", Collections.nCopies(30, "-")),
					String.join("", Collections.nCopies(30, "-")), "--------");
	
			System.out.format(format, id, article.getDescription(), article.getBrand(), article.getPrice() + "€" ); 
	
			System.out.println(String.join("", Collections.nCopies(83, "-")));
		} else {
			System.out.println("Cet article n'existe pas en base de données.");
		}
		

	}

	public void deleteOneArticle() {
		System.out.println("Quel article voulez-vous supprimer ?");
		displayArticles();
		int choice = scanInt();
		
		System.out.println("Confirmer ? O/N");
		String confirm = scan.next();
		if(confirm.equals("o") || confirm.equals("O")) {
			business.deleteArticle(business.getArticleById(choice));
			System.out.println("L'article " + business.getArticleById(choice).getDescription() + " a bien été supprimé.");
		} else {
			System.out.println("Supression abandonnée.");
		}
	}
	
	public void updateOneArticle() {
		System.out.println("Quel article souhaitez-vous mettre à jour ?");
		displayArticles();
		int choice = scanInt();
		
		System.out.println("Merci de renseigner sa description : ");
		scan.nextLine();
		String desc = scan.nextLine();
		
		System.out.println("Merci de renseigner sa marque : ");
		String brand = scan.nextLine();
		
		System.out.println("Merci de renseigner son prix : ");
		double price  = scan.nextDouble();
		
		System.out.println("Merci de choisir une catégorie : ");
		displayCategories();
		int cat = scanInt();
		
		business.updateArticle(new Article((long) choice, desc, brand, price, business.getCatById(cat).get()));
		
		System.out.println("L'article " + desc + " a bien été mis à jour.");
	}

	public void addOneArticle() {
		System.out.println("Vous allez ajouter un article.");
		System.out.println("Merci de renseigner sa description : ");
		scan.nextLine();
		String desc = scan.nextLine();
		
		System.out.println("Merci de renseigner sa marque : ");
		scan.nextLine();
		String brand = scan.nextLine();
		
		System.out.println("Merci de renseigner son prix : ");
		double price  = scan.nextDouble();
		
		System.out.println("Merci de choisir une catégorie : ");
		displayCategories();
		int cat = scanInt();
		
		business.addArticle(new Article(desc, brand, price, business.getCatById(cat).get()));
		
		System.out.println("L'article " + desc + " a bien été ajouté.");
	}
	
	/**
	 * print all articles from DB by page
	 */
	public static void displayArticlesByPage() {
		
	}
}