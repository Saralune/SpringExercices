/**
 * 
 */
package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;

/**
 * @author Stagiaires10P
 *
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

	public Article getArticleById(Long id);
	public List<Article> findByBrand(String brand);
	public List<Article> findByBrandContains(String brand);
	public List<Article> findByBrandAndPrice(String brand, double price);
	public List<Article> findByDescriptionAndBrand(String description, String brand);
	
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x") String kw, @Param("y") double price);
	
	@Query("select A.brand, A.description, A.price, C.name from Article A JOIN Category C ON (A.category = C.id) WHERE C.id = :x")
	public List<String> findByCategoryId(@Param("x") Long catId);
	//public List<Article> findByCategoryId(Long catId);

	public void deleteById(Long id);
	
	@Query("SELECT A.id, A.description, A.brand, A.price FROM Article A")
	public List<Object[]> findDescriptionAndBrandAndPrice();	
}

