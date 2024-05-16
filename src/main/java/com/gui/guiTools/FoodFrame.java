package com.gui.guiTools;

import com.gui.Cart;
import com.gui.food.Food;

import javax.swing.*;
import java.awt.*;

/**
 * Frame that provides window for creation and customization of any {@link com.gui.food.Food} type
 * */
public class FoodFrame extends JFrame {

    public final PanelBuilder pb;

    /**
     * @param clazz the class of {@link com.gui.food.Food} FoodFrame works with
     * @param cart the storage for created food
     * */
    public FoodFrame(Class<? extends Food> clazz, Cart<Food> cart) {
        setTitle(clazz.getSimpleName());
        setSize(300, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ImageIcon image = new ImageIcon("src/main/resources/images/logo.png");
        setIconImage(image.getImage());
        pb = new PanelBuilder(cart, this);
        JPanel panel = new JPanel(new FlowLayout());
        try {
            pb.initPanel(clazz, panel);
        } catch (Exception e) {
            System.out.println("Exception in initializing panel");
            for (StackTraceElement t : e.getStackTrace()) {
                System.out.println(t.toString());
            }
            panel.add(new JLabel("Exception occurred"));
        }
        setVisible(true);
    }
}
