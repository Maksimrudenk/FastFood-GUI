package com.gui.food;

import lombok.Data;

@Data
public class Drink extends Food{

    private final DrinkType drink = new DrinkType();

    public double calculatePrice() {
        return drink.price();
    }

    public void setDrink(String value) {
        this.drink.value=value;
    }

    static class DrinkType extends ComponentAbstract{

        public static final String[] options = {"Water $1", "Coke $2", "Orange Juice $1.50","None $0"};

    }

}
