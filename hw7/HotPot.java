package HotPot;
import java.util.ArrayList;
public abstract class HotPot {
    String name;
    String meat;
    String vegetables;
    ArrayList<String> toppings = new ArrayList<String>();
    void prepare() {
        System.out.println("准备 " + name);
        System.out.println("添加肉...");
        System.out.println("添加蔬菜...");
        for (String material : toppings) {
            System.out.println("   " + material);
        }
    }
    void cook() {
        System.out.println("烹饪60分钟");
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuffer display = new StringBuffer();
        display.append("----------" + name + "----------\n");
        display.append(meat + "\n");
        display.append(vegetables + "\n");
        for (String material : toppings) {
            display.append(material + "\n");
        }
        return display.toString();
    }
}
