package com.gui.guiTools;

import com.gui.Cart;
import com.gui.food.Food;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FoodFrame extends JFrame {

    public final PanelBuilder pb;
    public FoodFrame(Class<? extends Food> clazz, Cart<Food> cart){
        setTitle(clazz.getName());
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pb = new PanelBuilder(cart,this);
        JPanel panel= new JPanel(new FlowLayout());
        try {
            pb.initPanel(clazz, panel);
        } catch (Exception e){
            System.out.println("Exception in initializing panel");
            for (StackTraceElement t: e.getStackTrace()) {
                System.out.println(t.toString());
            }
            panel.add(new JLabel("Exception occurred"));
        }
        setContentPane(panel);
        setVisible(true);
    }
}
