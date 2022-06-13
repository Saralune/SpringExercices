package fr.fms.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@Service
public class IMyShopAppArticleImpl implements IMyShopApp<Article> {
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public List<Object[]> readAllByColumnName() {
		return articleRepository.findDescriptionAndBrandAndPrice();
	}

	@Override
	public void addOne(Article article) {
		articleRepository.save(article);
	}
	
	@Override
	public void updateOne(Article article) {
		articleRepository.save(article);		
	}

	@Override
	public void deleteOne(Article article)throws Exception {
		articleRepository.delete(article);	
	}

	@Override
	public Optional<Article> getOneById(int id) {
		return articleRepository.getArticleById((long) id);
	}

	@Override
	public List<Article> readAll() {
		return articleRepository.findAll();
	}

	public List<Article> readAllById(int id) {
		return articleRepository.findAllByCategoryId((long) id);
	}

	//Page<Article> articles = articleRepository.findAll(PageRequest.of(2, 5));
}
