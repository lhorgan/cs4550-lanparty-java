package webdev.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import webdev.models.Food;

@Repository("customFoodRepository")
public class CustomFoodRepository implements FoodRepository {
	@Autowired
    @Qualifier("foodRepository") // inject Spring implementation here
    private FoodRepository foodRepository;

	@Override
	public long count() {
		return foodRepository.count();
	}

	@Override
	public void delete(Food food) {
		foodRepository.delete(food);
	}

	@Override
	public void deleteAll() {
		foodRepository.deleteAll();
	}

	@Override
	public void deleteAll(Iterable<? extends Food> foods) {
		foodRepository.deleteAll(foods);
	}

	@Override
	public void deleteById(Integer id) {
		foodRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return foodRepository.existsById(id);
	}

	@Override
	public Iterable<Food> findAll() {
		return foodRepository.findAll();
	}

	@Override
	public Iterable<Food> findAllById(Iterable<Integer> id) {
		return foodRepository.findAllById(id);
	}

	@Override
	public Optional<Food> findById(Integer id) {
		return foodRepository.findById(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <S extends Food> S save(S food) {
		/*System.out.println("SAVING FOOD!");
		Optional<Food> existingFood = foodRepository.findFoodByLabel(food.getLabel());
		if(existingFood.isPresent()) {
			Food existing = existingFood.get();
			existing.addIngredients(food.getIngredients());
			System.out.println("SAVING EXISTING FOOD!");
			return (S) foodRepository.save(existing);
		}
		System.out.println("SAVING NEEW FOOD!");*/
		Iterable<Food> foods = foodRepository.findFoodByLabel(food.getLabel());
		for(Food existingFood : foods) {
			existingFood.addIngredients(food.getIngredients());
			return (S) foodRepository.save(existingFood);
		}
		
		return foodRepository.save(food);
	}

	@Override
	public <S extends Food> Iterable<S> saveAll(Iterable<S> foods) {
		System.out.println("SAVING ALL FOOD!");
		return foodRepository.saveAll(foods);
	}

	@Override
	public Iterable<Food> findFoodByLabel(String label) {
		return foodRepository.findFoodByLabel(label);
	}
}