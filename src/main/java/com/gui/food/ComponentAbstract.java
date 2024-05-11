package com.gui.food;

public abstract class ComponentAbstract {

    public String value;

    public double price() { //sets Strings to doubles and collects only the numbers
        String pricePart = value.substring(value.indexOf("$") + 1);
        return Double.parseDouble(pricePart);
    }

}
