package me.tokanes.recipeshw2.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.tokanes.recipeshw2.model.Ingredient;
import me.tokanes.recipeshw2.model.Recipe;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

	private Map<Long, Recipe> recipes = new HashMap<>();
	private long idGenerator = 1;
	private final FileService fileService;

	public RecipeService(FileService fileService) {
		this.fileService = fileService;
	}

	@PostConstruct
	private  void init(){
		readFromFile();
	}

	public Recipe add(Recipe recipe){
		recipes.put(idGenerator++, recipe);
		saveToFile();
		return recipe;
	}
	public Optional<Recipe> get(long id){

		return Optional.ofNullable(recipes.get(id));
	}

	public Optional <Recipe> update(long id, Recipe recipe) {
		saveToFile();
		return Optional.ofNullable(recipes.replace(id, recipe));
	}

	public Optional <Recipe> delete(long id) {
		return Optional.ofNullable(recipes.remove(id));
	}

	public Map<Long, Recipe> getAll() {
		return new HashMap<>(recipes);
	}

	private void saveToFile(){
		try {
			String json = new ObjectMapper().writeValueAsString(recipes);
			fileService.saveToFile(json);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private void  readFromFile(){
		try {
			String json = fileService.readFromFile();
			recipes = new ObjectMapper().readValue(json, new TypeReference<HashMap<Long, Recipe>>() {
			});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
