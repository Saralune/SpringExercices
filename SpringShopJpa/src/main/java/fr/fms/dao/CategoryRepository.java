/**
 * 
 */
package fr.fms.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Category;

/**
 * @author Stagiaires10P
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	 public Category findByName(String name);
	 
	 public List<Category> findAllByOrderByNameAsc();
	 public List<Category> findAllByOrderByNameDesc();

	 public Optional<Category> findById(Long id);
}
