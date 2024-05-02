package com.gui.food;

import lombok.Data;

@Data
public class Burger implements Food{

    private final int BASE_PRICE;

    private Meat meat;

    private Sauce sauce;

    public int calculatePrice() {
        return BASE_PRICE + meat.price + sauce.price;
    }

    enum Meat{
        Chicken("chicken", 2), Beef("beef", 3);

        final int price;
        final String value;

        Meat (String value, int price){
            this.value=value;
            this.price=price;
        }

    }

    enum Sauce{
        Mustard("Mustard", 1), BbqSauce("Bbq Sauce", 2);

        final int price;
        final String value;

        Sauce (String value, int price){
            this.value=value;
            this.price=price;
        }

    }
}
