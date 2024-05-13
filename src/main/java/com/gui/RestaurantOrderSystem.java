package com.gui;

import com.gui.food.Burger;
import com.gui.food.Drink;
import com.gui.food.Food;
import com.gui.food.SideDish;
import com.gui.guiTools.FoodFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main frame;
 * */
public class RestaurantOrderSystem extends JFrame {
    private final JTextArea totalDisplay; //Area that displays text

    private final Cart<Food> cart= new Cart<>(this);

    public RestaurantOrderSystem() {
        //Creation of Interface & Buttons
        super("Restaurant Order System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton burgerButton = new JButton("Order Burger");
        burgerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FoodFrame(Burger.class, cart);
            }
        });

        JButton sideDishButton = new JButton("Order Side Dish");
        sideDishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FoodFrame(SideDish.class, cart);
            }
        });

        JButton DrinkButton = new JButton("Order Drink");
        DrinkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FoodFrame(Drink.class, cart);
            }
        });

        totalDisplay = new JTextArea(5, 30); //size creation
        totalDisplay.setEditable(false);
        cart.updateOutput();

        add(burgerButton);
        add(sideDishButton);
        add(DrinkButton);
        add(new JScrollPane(totalDisplay));

        setVisible(true);
    }


    public JTextArea getTotalDisplay() {
        return totalDisplay;
    }


    public static void main(String[] args) { //Main method
        new RestaurantOrderSystem();
    }
}
