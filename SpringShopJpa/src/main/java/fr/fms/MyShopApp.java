package fr.fms;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import fr.fms.business.IMyShopAppArticleImpl;
import fr.fms.business.IMyShopAppCategoryImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class MyShopApp<T> implements CommandLineRunner {	
	private static Scanner scan = new Scanner(System.in);
	private String unArticle = "un article";
	private String uneCat = "une catégorie";
	
	@Autowired
	private IMyShopAppArticleImpl articleBusiness;
	@Autowired
	private IMyShopAppCategoryImpl catBusiness;
	
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
				subMenu(unArticle);
				break;

			case 4:
				subMenu(uneCat);
				break;

			case 5:
				displayArticlesByCat();
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
		List<Object[]> allArticles = articleBusiness.readAllByColumnName();

		System.out.println(String.join("", Collections.nCopies(88, "-")));
		System.out.format(format, "REF", "DESCRIPTION", "MARQUE", "PRIX");
		System.out.format(format, "----", String.join("", Collections.nCopies(35, "-")),
				String.join("", Collections.nCopies(30, "-")), "--------");

		 allArticles.forEach((n) ->
			 System.out.format(format, n[0], n[1], n[2], n[3] + "€" )); 

		System.out.println(String.join("", Collections.nCopies(88, "-")));
	}
	
	public void displayCategories() {
		String format = "%1$-4s | %2$-22s |\n";
		List<Category> allCat = catBusiness.readAll();
		
		System.out.println(String.join("", Collections.nCopies(30, "-")));
		System.out.format(format, "REF", "NOM");
		allCat.forEach((n) ->
		 System.out.format(format, n.getId(), n.getName())); 
		System.out.println(String.join("", Collections.nCopies(30, "-")));
	}
	
	/**
	 * 
	 */
	public void displayArticlesByCat() {		
		String format = "%1$-4s | %2$-35s | %3$-30s | %4$-8s |\n";
		
		System.out.println("Choisissez une catégorie :");
		displayCategories();
		
		int choice = scanInt();
		
		if(!catBusiness.getOneById(choice).isEmpty()) {
			System.out.println("Voici les articles correspondants à la catégorie " + catBusiness.getOneById(choice).get().getName() + " :");
			
			List<Article> allArticlesByCat = articleBusiness.readAllById(choice);
			
			System.out.println(String.join("", Collections.nCopies(88, "-")));
			System.out.format(format, "REF", "DESCRIPTION", "MARQUE", "PRIX");
			System.out.format(format, "----", String.join("", Collections.nCopies(35, "-")),
					String.join("", Collections.nCopies(30, "-")), "--------");

			 allArticlesByCat.forEach((n) ->
				 System.out.format(format, n.getId(), n.getDescription(), n.getBrand(), n.getPrice() + "€" )); 

			System.out.println(String.join("", Collections.nCopies(88, "-")));
		} else {
			System.out.println("La catégorie n'existe pas.");
		}

	}
	
	/**
	 * 
	 */
	public void subMenu(String name) {
		int choice = 5;
		
		while(choice != 0) {
			displayCRUDMenu(name);
			choice = scanInt();
			
			switch (choice) {
			case 0:
				break;
				
			case 1: //ajouter 
				addOne(name);			
				break;
				
			case 2: //supprimer	 
				deleteOne(name);
				break;
				
			case 3: //mettre à jour 
				updateOne(name);
				break;
				
			case 4: //afficher 
				displayOne(name);
				break;
			}
		}
	}
	
	public void displayOne(String name) {
		System.out.println("Veuillez saisir l'id d'" + name + " à afficher.");
		int id = scanInt();
		
		if(name.equals(unArticle)) {
			String format = "%1$-4s | %2$-30s | %3$-30s | %4$-8s |\n";

			//la méthode isEmpty (Optional <T>) vérifie qu'il existe bien une valeur en base
			if(!articleBusiness.getOneById(id).isEmpty()) { 
				Article article = articleBusiness.getOneById(id).get();
				
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
		
		else if(name.equals(uneCat)) {
			String format = "%1$-4s | %2$-22s |\n";

			//la méthode isEmpty (Optional <T>) vérifie qu'il existe bien une valeur en base
			if(!catBusiness.getOneById(id).isEmpty()) {
				Category category = catBusiness.getOneById(id).get();
				
				System.out.println(String.join("", Collections.nCopies(30, "-")));
				System.out.format(format, "REF", "NOM");
				System.out.format(format, category.getId(), category.getName()); 
				System.out.println(String.join("", Collections.nCopies(30, "-")));
			} else {
				System.out.println("Cette catégorie n'existe pas en base de données.");
			}
		}
	}

	public void deleteOne(String name) {
		System.out.println("Choisissez la référence d'" + name +" que vous souhaitez supprimer :");
		
		if(name.equals(unArticle))	displayArticles();
		else if (name.equals(uneCat)) displayCategories();
		
		int choice = scanInt();
		
		System.out.println("Confirmer ? O/N");
		String confirm = scan.next();
		
		if(confirm.equalsIgnoreCase("o")) {
			//article
			if(name.equals(unArticle)) {
				if(!articleBusiness.getOneById(choice).isEmpty()) {
					Article articleToDelete = articleBusiness.getOneById(choice).get();
					articleBusiness.deleteOne(articleToDelete);
					System.out.println("L'article " + articleToDelete.getDescription() + " a bien été supprimé.");
				} else {
					System.out.println("L'article selectionné n'existe pas");
				}
			}
			
			//category
			else if(name.equals(uneCat)) {
				if(!catBusiness.getOneById(choice).isEmpty()) {
					Category catToDelete = catBusiness.getOneById(choice).get();
					catBusiness.deleteOne(catToDelete);
					System.out.println("La catégorie " + catToDelete.getName() + " a bien été supprimée.");
				}
			}

		} else {
			System.out.println("Supression abandonnée.");
		}
	}
	
	public void updateOne(String name) {
		System.out.println("Choisissez la référence d'" + name + "à mettre à jour :");
		
		if(name.equals(unArticle))	displayArticles();
		else if (name.equals(uneCat)) displayCategories();
		
		int choice = scanInt();
		
		//vérifie que l'article ou la cat existe
		if((articleBusiness.getOneById(choice).isEmpty() && name.equals(unArticle)) 
				|| (catBusiness.getOneById(choice).isEmpty()) && name.equals(uneCat)) {
			System.out.println("La référence demandée n'existe pas.");
			return;
		}
		
		System.out.println("Merci de renseigner sa description : ");
		scan.nextLine();
		String desc = scan.nextLine();
		
		//article
		if(name.equals(unArticle)) {
			System.out.println("Merci de renseigner sa marque : ");
			String brand = scan.nextLine();
			
			System.out.println("Merci de renseigner son prix : ");
			double price  = scan.nextDouble();
			
			System.out.println("Merci de choisir une catégorie : ");
			displayCategories();
			int cat = scanInt();
			
			if(!catBusiness.getOneById(cat).isEmpty()) {
				articleBusiness.updateOne(new Article((long) choice, desc, brand, price, catBusiness.getOneById(cat).get()));
				System.out.println("L'article " + desc + " a bien été mis à jour.");
			} else {
				System.out.println("La catégorie n'existe pas.");
			}
		}
		
		//category
		else if(name.equals(uneCat)) {
			catBusiness.updateOne(new Category((long) choice, desc));
			System.out.println("La cétgorie " + desc + " a bien été mise à jour.");
		}
		
	}

	public void addOne(String name) {
		System.out.println("Vous allez ajouter " + name + ".");
		
		System.out.println("Merci de renseigner sa description : ");
		scan.nextLine();
		String desc = scan.nextLine();
		
		if(name.equals(unArticle)) {
			System.out.println("Merci de renseigner sa marque : ");
			String brand = scan.nextLine();
			
			System.out.println("Merci de renseigner son prix : ");
			double price  = scan.nextDouble();
			
			System.out.println("Merci de choisir une catégorie : ");
			displayCategories();
			int cat = scanInt();
			
			if(!catBusiness.getOneById(cat).isEmpty()) {
				articleBusiness.addOne(new Article(desc, brand, price, catBusiness.getOneById(cat).get()));
				System.out.println("L'article " + desc + " a bien été ajouté.");
			} else {
				System.out.println("La catégorie n'existe pas. Merci de recommencer.");
			}
		}
		else if(name.equals(uneCat)) {
			catBusiness.addOne(new Category(desc));
			System.out.println("La catégorie " + desc + " a bien été ajoutée.");
		}
		
	}
	
	/**
	 * print all articles from DB by page
	 */
	public static void displayArticlesByPage() {
		
	}
}