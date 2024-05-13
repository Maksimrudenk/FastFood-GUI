package com.gui.food;

/**
 * Abstract Class for all food types to be included in the application
 * restrictions:
 * All customizable fields must be implemented via children of {@link com.gui.food.ComponentAbstract}
 * For each field there are must be a set-method that set the chosen variant to the unit's value-field of {@link com.gui.food.ComponentAbstract}, stored in this field
 * Must implement calculatePrice() method that considers all vars that impact the price of the food unit
 * */
public abstract class Food {

    public abstract double calculatePrice();

}
