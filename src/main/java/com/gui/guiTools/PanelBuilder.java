package com.gui.guiTools;

import com.gui.Cart;
import com.gui.food.Food;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Is object that fill builder panels
 */
public class PanelBuilder {

    private final Cart<Food> cart;

    private final JFrame frame;

    /**
     * @param mainPanel panel that user returns to after process of customization finishes
     * @param mainFrame frame on each action is performed
     */
    public PanelBuilder(Cart<Food> cart, JFrame frame) {
        this.cart = cart;
        this.frame = frame;
    }

    /**
     * start the process of filling builder panel
     *
     * @param clazz specifies with which type of {@link com.gui.food.Food} panel works
     * @param panel panel to be filled
     */
    public <C extends Food> void initPanel(Class<C> clazz, JPanel panel) throws ReflectiveOperationException, SecurityException {
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
                    cart.add(result);
                    cart.updateOutput();
                    frame.dispose();
                }
            });
            panel.add(submitButton);

        } catch (NoSuchMethodException | NullPointerException exception) {
            throw new ReflectiveOperationException("Reflective exception while filling builder for" + clazz.toString(), exception);
        } catch (SecurityException exception) {
            throw new SecurityException("Security exception while filling builder for" + clazz, exception);
        }
    }

    /**
     * Returns {@link AttributeBox}es for all attributes of provided {@link com.gui.food.Food} type
     */
    private <C extends Food> ArrayList<AttributeBox<?>> createBoxes(Class<C> clazz) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        ArrayList<AttributeBox<?>> result = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            String name = f.getName();
            String first = String.valueOf(name.charAt(0));
            String replace = first.toUpperCase();
            name = name.replaceFirst(first, replace);
            Method method = clazz.getMethod("set" + name, String.class);
            Field oprionsField = f.getType().getField("options");
            oprionsField.setAccessible(true);
            String[] elements = (String[]) oprionsField.get(null);
            AttributeBox<?> box = new AttributeBox<>(method, elements);
            result.add(box);
        }
        return result;
    }
}
