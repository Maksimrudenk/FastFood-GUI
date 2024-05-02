package com.gui;

import com.gui.food.Food;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class PanelBuilder {

    private final JPanel panel;

    public PanelBuilder(JPanel panel){
        this.panel=panel;
    }

    public <C extends Food> void initPanel(Class<C> clazz, JFrame mainFrame, JPanel mainPanel) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Field[] fields = clazz.getDeclaredFields();
        ArrayList<AttributeBox<?>> boxes = new ArrayList<>();
        for (int i = 1; i<fields.length;i++) {
            Field f = fields[i];
            String name = f.getName();
            String first = String.valueOf(name.charAt(0));
            String replace = first.toUpperCase();
            name = name.replaceFirst(first, replace);
            Method method = clazz.getMethod("set"+name, f.getType());
            Object[] elements = f.getType().getEnumConstants();
            AttributeBox<?> box = new AttributeBox<>(method,elements);
            boxes.add(box);
            panel.add(box);
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                C result;
                try {
                    result = clazz.getConstructor(new Class[]{}).newInstance();
                } catch (InstantiationException ex) {
                    throw new RuntimeException(ex);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                } catch (InvocationTargetException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                }
                for (AttributeBox<?> b:boxes) {
                    try {
                        b.setAttribute(result);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                System.out.println(result);
                mainFrame.setContentPane(mainPanel);
                mainFrame.revalidate();
            }
        });
        panel.add(submitButton);
    }
}
