package com.gui.food;

public class Burger extends Food {

    private final Meat meat = new Meat();

    private final Sauce sauce = new Sauce();

    private final Topping topping = new Topping();

    public Burger() {
    }

    public void setMeat(String value) {
        this.meat.value = value;
    }

    public void setSauce(String value) {
        this.sauce.value = value;
    }

    public void setTopping(String value) {
        this.topping.value = value;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "meat=" + meat.value +
                ", sauce=" + sauce.value +
                ", topping=" + topping.value +
                ", price=" + calculatePrice() +
                '}';
    }

    public double calculatePrice() {
        return meat.price() + sauce.price() + topping.price();
    }


    static class Sauce extends ComponentAbstract {

        public static final String[] options = {"Ketchup $1", "Mayonnaise $1", "Mustard $1", "None $0"};

    }

    static class Meat extends ComponentAbstract {

        public static final String[] options = {"Beef $5", "Pork $4", "Chicken $3", "Vegan $6"};

    }

    static class Topping extends ComponentAbstract {

        public static final String[] options = {"Lettuce $0.50", "Tomato $0.50", "Cheese $0.75"};

    }
}
