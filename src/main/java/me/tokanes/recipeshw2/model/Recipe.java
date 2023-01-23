package me.tokanes.recipeshw2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Recipe {

	private String title;
	private int cookingTime;
	private List<Ingredient> ingredients;
	private List<String> steps;

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(title).append("\n")
				.append("Время приготовления: ").append(cookingTime).append("минут").append("\n")
				.append("Ингредиенты: ").append("\n");
		for (Ingredient ingredient: ingredients){
			stringBuilder.append("☻ ").append(ingredient).append("\n");
		}
		stringBuilder.append("Инструкция проготовления: ").append("\n");
		int counter =1;
		for (String step: steps){
			stringBuilder.append(counter++).append(". ").append(step).append("\n");
		}
		return stringBuilder.toString();
	}
}
