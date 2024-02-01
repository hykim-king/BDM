package com.test.bdm.nutrient.domain;

import com.test.bdm.cmn.DTO;

public class NutrientVO extends DTO {
	private String code;
	private String name;
	private int norm;
	private int kcal;
	private double carbohydrate;
	private double protein;
	private double fat;
	private double sugars;
	private int weight;
	public NutrientVO() {
		super();
	}

	public NutrientVO(String code, String name, int norm, int kcal, double carbohydrate, double protein, double fat,
			double sugars, int weight) {
		super();
		this.code = code;
		this.name = name;
		this.norm = norm;
		this.kcal = kcal;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.sugars = sugars;
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNorm() {
		return norm;
	}

	public void setNorm(int norm) {
		this.norm = norm;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public double getCarbohydrate() {
		return carbohydrate;
	}

	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public double getSugars() {
		return sugars;
	}

	public void setSugars(double sugars) {
		this.sugars = sugars;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "NutrientVO [code=" + code + ", name=" + name + ", norm=" + norm + ", kcal=" + kcal + ", carbohydrate="
				+ carbohydrate + ", protein=" + protein + ", fat=" + fat + ", sugars=" + sugars + ", weight=" + weight
				+ ", toString()=" + super.toString() + "]";
	}

}
