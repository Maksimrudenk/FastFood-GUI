package com.gui;

import com.gui.food.Burger;
import com.gui.food.Food;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private final JFrame mainFrame;
    private JPanel main;
    private JButton burgerButton;
    private JButton food2Button;
    private JButton totalButton;

    private ArrayList<Food> cart;


    public Main(JFrame frame) {
        mainFrame = frame;
        burgerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                builder(Burger.class);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main(frame).main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {

    }

    private void builder(Class<? extends Food> clazz){
        JPanel panel = new JPanel();

        PanelBuilder pb = new PanelBuilder(panel);
        try {
            pb.initPanel(clazz, mainFrame, main);
        } catch (Exception e){
            System.out.println("Exception in initializing panel");
            for (StackTraceElement t: e.getStackTrace()) {
                System.out.println(t.toString());
            }
        }
        mainFrame.setContentPane(panel);
        mainFrame.revalidate();

    }
}
