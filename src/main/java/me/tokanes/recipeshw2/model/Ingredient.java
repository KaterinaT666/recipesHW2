package me.tokanes.recipeshw2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Ingredient {

	private String title;
	private int amount;
	private String measureUnit;

	@Override
	public String toString() {
		return title+ " ─ "+ amount+" "+measureUnit;
	}
}
