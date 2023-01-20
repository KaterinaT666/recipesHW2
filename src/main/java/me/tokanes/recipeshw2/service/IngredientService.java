package me.tokanes.recipeshw2.service;

import me.tokanes.recipeshw2.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientService {

	private final Map<Long, Ingredient> ingredients = new HashMap<>();
	private long idGenerator =1;

	public Ingredient add(Ingredient ingredient){
		ingredients.put(idGenerator++, ingredient);
		return ingredient;
	}

	public Optional <Ingredient> get(long id){

		return Optional.ofNullable(ingredients.get(id));
	}

	public Optional <Ingredient> update(long id, Ingredient ingredient) {
		return Optional.ofNullable(ingredients.replace(id, ingredient));
	}

	public Optional <Ingredient> delete(long id) {
		return Optional.ofNullable(ingredients.remove(id));
	}

	public Map<Long, Ingredient> getAll() {
		return new HashMap<>(ingredients);
	}
}
