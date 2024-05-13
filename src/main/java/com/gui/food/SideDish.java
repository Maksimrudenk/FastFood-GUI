package com.gui.food;

import lombok.Data;

@Data
public class SideDish extends Food {

    private final Dish dish = new Dish();

    public double calculatePrice() {
        return dish.price();
    }

    public void setDish(String value) {
        this.dish.value = value;
    }

    static class Dish extends ComponentAbstract {

        public static final String[] options = {"Fries $2", "Salad $3", "Onion Rings $2.50"};

    }

}
