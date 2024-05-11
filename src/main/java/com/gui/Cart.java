package com.gui;

import com.gui.food.Food;

import java.util.ArrayList;

public class Cart<E extends Food> extends ArrayList<E> {

    private final RestaurantOrderSystem app;

    public Cart(RestaurantOrderSystem app){
        this.app = app;
    }

    public void updateOutput(){
        double total = 0;
        for (E f: this) {
            total+=f.calculatePrice();
        }
        app.getTotalDisplay().setText("Total: $" + String.format("%.2f", total));
    }

}
