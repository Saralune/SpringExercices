/**
 * 
 */
package fr.fms.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Category;

/**
 * @author Stagiaires10P
 *
 */
@Service
public class IMyShopAppCategoryImpl implements IMyShopApp<Category> {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void addOne(Category cat) {
		categoryRepository.save(cat);
	}

	@Override
	public List<Category> readAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void updateOne(Category cat) {
		categoryRepository.save(cat);
	}

	@Override
	public void deleteOne(Category cat) throws Exception{
		categoryRepository.delete(cat);	
	}

	@Override
	public Optional<Category> getOneById(int id) {
		return categoryRepository.findById((long)id);
	}

	@Override
	public List<Object[]> readAllByColumnName() {
		return null;
	}

	public Category findByName(String string) {
		return categoryRepository.findByName(string);
	}

}
