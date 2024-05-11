package com.gui;

import com.gui.food.Burger;
import com.gui.food.Drink;
import com.gui.food.Food;
import com.gui.guiTools.FoodFrame;
import com.gui.guiTools.PanelBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RestaurantOrderSystem extends JFrame { // access of gui components
    private JTextArea totalDisplay; //Area that displays text

    private Cart<Food> cart;

    public RestaurantOrderSystem() {
        //Creation of Interface & Buttons
        super("Restaurant Order System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        cart = new Cart<>(this);

        JButton burgerButton = new JButton("Order Burger");
        burgerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FoodFrame(Burger.class, cart);
            }
        });

//        JButton sideDishButton = new JButton("Order Side Dish");
//        sideDishButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                initBuilder(Burger.class);
//            }
//        });

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
//        add(sideDishButton);
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
