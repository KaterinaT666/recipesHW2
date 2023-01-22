package me.tokanes.recipeshw2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.tokanes.recipeshw2.model.Ingredient;
import me.tokanes.recipeshw2.service.IngredientService;
import me.tokanes.recipeshw2.service.ValidateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "IngredientController", description = "Api для рецептов")

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

	private final IngredientService ingredientService;
	private final ValidateService validateService;


	public IngredientController(IngredientService ingredientService,
								ValidateService validateService) {

		this.ingredientService = ingredientService;
		this.validateService = validateService;
	}

	@Operation(summary = "Добавление рецепта", description = "Добавление рецепта")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Добавление прошло успешно"),
			@ApiResponse(responseCode = "400", description = "Некорректные параметры рецепта")
	})
	@PostMapping
	public ResponseEntity <Ingredient> add(@RequestBody Ingredient ingredient){
		if (!validateService.isNotValid(ingredient)){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(ingredientService.add(ingredient));
	}

	@GetMapping("/{id}")
	public ResponseEntity <Ingredient> get(@PathVariable long id){
		return ResponseEntity.of(ingredientService.get(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity <Ingredient> update(@PathVariable long id,
							 @RequestBody Ingredient ingredient){
		if (validateService.isNotValid(ingredient)){
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.of(ingredientService.update(id, ingredient));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity <Ingredient> delete(@PathVariable long id){
		return ResponseEntity.of(ingredientService.delete(id));
	}

	@GetMapping
	public Map<Long, Ingredient> getAll(){
		return ingredientService.getAll();
	}
}
