package com.gui.guiTools;

import com.gui.food.Food;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**Special type of {@link JComboBox} each represent specific attribute of provided class*/
public class AttributeBox<E> extends JComboBox<E> {

    private final Method method;

    public AttributeBox(Method method, E[] elements){
        super(elements);
        this.method=method;
    }

    /**Sets the chosen value of ComboBox to the provided object' attribute
     * @param obj object, attribute of which, to be set*/
    public void setAttribute(Food obj) throws InvocationTargetException, IllegalAccessException {
        method.invoke(obj, super.getSelectedItem());
    }

}
