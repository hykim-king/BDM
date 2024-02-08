package com.test.bdm.nutrient.domain;

import com.test.bdm.cmn.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutrientVO extends DTO {
	private String code;
	private String name;
	private int kcal;
	private double carbohydrate;
	private double protein;
	private double fat;
	private double sugars;

	@Override
	public String toString() {
		return "NutrientVO [code=" + code + ", name=" + name + ", kcal=" + kcal + ", carbohydrate=" + carbohydrate
				+ ", protein=" + protein + ", fat=" + fat + ", sugars=" + sugars + ", toString()=" + super.toString()
				+ "]";
	}

}
