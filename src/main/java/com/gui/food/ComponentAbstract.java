package com.gui.food;

/**
 * Parent class for all customizable fields of {@link com.gui.food.Food}
 *
 * Must have the String[] options field containing all possible variants of var value in format of: "{name} ${price}"
 * */
public abstract class ComponentAbstract {

    public String value;

    /**
     * Extracts the price in double form from String stored in value field
     * */
    public double price() { //sets Strings to doubles and collects only the numbers
        String pricePart = value.substring(value.indexOf("$") + 1);
        return Double.parseDouble(pricePart);
    }

}
