package com.gui.food;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Burger implements Food{

    private final int BASE_PRICE = 3;

    private Meat meat;

    public Burger() {
    }

    @Override
    public String toString() {
        return "Burger{" +
                "meat=" + meat +
                ", sauce=" + sauce +
                ", price=" + calculatePrice() +
                '}';
    }

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
