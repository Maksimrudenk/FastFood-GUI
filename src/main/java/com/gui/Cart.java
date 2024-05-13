package com.gui;

import com.gui.food.Food;

import java.util.ArrayList;

/**
 * The storage for created {@link com.gui.food.Food}
 * */
public class Cart<E extends Food> extends ArrayList<E> {

    private final RestaurantOrderSystem app;

    /**
     * @param app the main frame of the application
     * */
    public Cart(RestaurantOrderSystem app) {
        this.app = app;
    }

    /**
     * updates the output in the total display of the main frame
     * */
    public void updateOutput() {
        double total = 0;
        for (E f : this) {
            total += f.calculatePrice();
        }
        app.getTotalDisplay().setText("Total: $" + String.format("%.2f", total));
    }

}
