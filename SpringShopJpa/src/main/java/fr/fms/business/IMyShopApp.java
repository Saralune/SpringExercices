package fr.fms.business;

import java.util.List;
import java.util.Optional;

public interface IMyShopApp<T>{
	public List<Object[]> readAllByColumnName();
	public List<T> readAll();
	public void addOne(T t);
	public void updateOne(T t);
	public void deleteOne(T t) throws Exception;
	public Optional<T> getOneById(int id);
}
