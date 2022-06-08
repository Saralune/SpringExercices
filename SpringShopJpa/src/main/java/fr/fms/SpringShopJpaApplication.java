//package fr.fms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import fr.fms.dao.ArticleRepository;
//import fr.fms.dao.CategoryRepository;
//import fr.fms.entities.Article;
//import fr.fms.entities.Category;
//
//@SpringBootApplication
//public class SpringShopJpaApplication implements CommandLineRunner  {
//	
//	@Autowired
//	private CategoryRepository categoryRepository;
//	
//	@Autowired
//	private ArticleRepository articleRepository;
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringShopJpaApplication.class, args);
//	}
//	
//	@Override
//	public void run(String... args) throws Exception {
//		//categoryRepository.save(new Category("Smartphone"));
//		//articleRepository.save(new Article("iPhone 3 - Lumière Stellaire", "Apple", 1209));
//		//articleRepository.save(new Article("S20", "Samsung", 250));
//		//articleRepository.save(new Article("S10+", "Samsung", 28));
//		
////		for(Article article: articleRepository.findByBrand("Apple")) {
////			System.out.println(article.getDescription());
////		}
////		for(Category cat: categoryRepository.findAll()) {
////			System.out.println(cat.getName());
////		}
////		
////		Article art1 = articleRepository.getArticleById((long) 1);
////		System.out.println(art1);
//		
////		for(Article article: articleRepository.findByBrandContains("a")) {
////			System.out.println(article.getBrand());
////		}
////		System.out.println("-------");
////		for(Article article: articleRepository.findByBrandAndPrice("Apple", 2500)) {
////			System.out.println(article);
////		}
//		
////		Category smartphone = categoryRepository.save(new Category("Smartphone"));
////		Category tablet = categoryRepository.save(new Category("Tablet"));
////		Category pc = categoryRepository.save(new Category("PC"));
////		
////		articleRepository.save(new Article("S10+", "Samsung", 350, smartphone));
////		articleRepository.save(new Article("MI10", "Xiaomi", 100, smartphone));
////		
////		articleRepository.save(new Article("GalaxyTab", "Samsung", 450, tablet));
////		articleRepository.save(new Article("Ipad Pro", "Samsung", 850, tablet));
////		
////		articleRepository.save(new Article("R510", "Asus", 600, pc));
//		
////		for(String article: articleRepository.findByCategoryId((long) 4)) {
////			System.out.println(article);
////		}
//		
//		//EXO 1.2
//		System.out.println("Exo 1.2");
//		System.out.println(articleRepository.getArticleById((long) 1));
//		System.out.println(articleRepository.findById((long) 1));
//		System.out.println("------------------");
//		
//		//EXO 1.3
//		System.out.println("Exo 1.3");
//		System.out.println(articleRepository.findByDescriptionAndBrand("MacBook", "Apple"));
//		System.out.println("--------");
//
//		//EXO 1.4
//		//articleRepository.deleteById((long) 5);
//		
//		//EXO 1.5
//		System.out.println("Exo 1.5");
//		Category smartphone = categoryRepository.findByName("Smartphone");
//		articleRepository.save(new Article((long) 3, "iPhone 13 - Lumière Stellaire", "Apple", 1209, smartphone));
//		//articleRepository.saveOrUpdate(new Article((long) 3, "iPhone 13 - Lumière Stellaire", "Apple", 1209, smartphone));
//		System.out.println("--------");
//		
//		//EXO 1.6
//		System.out.println("Exo 1.6");
//		for(Category cat : categoryRepository.findAllByOrderByNameDesc()){
//			System.out.println(cat);
//		}
//		System.out.println("--------");
//		for(Category cat : categoryRepository.findAllByOrderByNameAsc()){
//			System.out.println(cat);
//		}
//		System.out.println("--------");
//	}
//	
//}
