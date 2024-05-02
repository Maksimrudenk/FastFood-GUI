package com.gui;

import com.gui.food.Food;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AttributeBox<E> extends JComboBox<E> {

    private final Method method;

    public AttributeBox(Method method, E[] elements){
        super(elements);
        this.method=method;
    }

    public void setAttribute(Food obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj, super.getSelectedItem());
    }

}
