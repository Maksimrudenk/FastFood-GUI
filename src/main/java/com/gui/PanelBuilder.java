package com.gui;

import com.gui.food.Food;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**Is object that fill builder panels*/
public class PanelBuilder {

    private final JPanel mainPanel;
    private final JFrame mainFrame;

    /**@param mainPanel panel that user returns to after process of customization finishes
     * @param mainFrame frame on each action is performed*/
    public PanelBuilder(JPanel mainPanel, JFrame mainFrame){
        this.mainPanel=mainPanel;
        this.mainFrame=mainFrame;
    }

    /**start the process of filling builder panel
     * @param clazz specifies with which type of {@link com.gui.food.Food} panel works
     * @param panel panel to be filled*/
    public <C extends Food> void initPanel(Class<C> clazz , JPanel panel) throws ReflectiveOperationException, SecurityException{
        try {
            ArrayList<AttributeBox<?>> boxes = createBoxes(clazz);
            for (AttributeBox<?> box : boxes) {
                panel.add(box);
            }

            JButton submitButton = new JButton("Submit");
            submitButton.addMouseListener(new MouseAdapter() {
                @SneakyThrows
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    C result = clazz.getConstructor(new Class[]{}).newInstance();
                    for (AttributeBox<?> b : boxes) {
                        b.setAttribute(result);
                    }
                    System.out.println(result);
                    mainFrame.setContentPane(mainPanel);
                    mainFrame.revalidate();
                }
            });
            panel.add(submitButton);

        }catch (NoSuchMethodException | NullPointerException exception){
            throw new ReflectiveOperationException("Reflective exception while filling builder for"+clazz.toString(),exception);
        } catch (SecurityException exception){
            throw new SecurityException("Security exception while filling builder for"+clazz, exception);
        }
    }

    /**Returns {@link com.gui.AttributeBox}es for all attributes of provided {@link com.gui.food.Food} type*/
    private <C extends Food> ArrayList<AttributeBox<?>> createBoxes(Class<C> clazz) throws NoSuchMethodException {
        ArrayList<AttributeBox<?>> result = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 1; i<fields.length;i++) {
            Field f = fields[i];
            String name = f.getName();
            String first = String.valueOf(name.charAt(0));
            String replace = first.toUpperCase();
            name = name.replaceFirst(first, replace);
            Method method;
            method = clazz.getMethod("set" + name, f.getType());
            Object[] elements = f.getType().getEnumConstants();
            AttributeBox<?> box = new AttributeBox<>(method, elements);
            result.add(box);
        }
        return result;
    }
}
