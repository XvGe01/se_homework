# 火锅的工厂方法模式
## 一、工厂方法模式
- 定义：工厂方法模式定义一个用于创建对象的接口，让子类决定实例化哪一个类。工厂方法使一个类的实例化延迟到其子类。

- 实质：此模式的核心精神是封装类中变化的部分，提取其中个性化善变的部分为独立类，通过依赖注入以达到解耦、复用和方便后期维护拓展的目的。它的核心结构有四个角色，分别是抽象工厂；具体工厂；抽象产品；具体产品。

- 优点：核心类只关注工厂类的接口定义，而具体的产品实例交给具体的工厂子类去创建。当系统需要新增一个产品是，无需修改现有系统代码，只需要添加一个具体产品类和其对应的工厂子类，使系统的扩展性变得很好，符合面向对象编程的开闭原则。

- 使用场景：
     - 对于某个产品，调用者清楚地知道应该使用哪个具体工厂服务，实例化该具体工厂，生产出具体的产品来。Java Collection中的iterator() 方法即属于这种情况；

     - 只是需要一种产品，而不想知道也不需要知道究竟是哪个工厂为生产的，即最终选用哪个具体工厂的决定权在生产者一方，它们根据当前系统的情况来实例化一个具体的工厂返回给使用者，而这个决策过程这对于使用者来说是透明的

## 二、火锅的工厂方法模式实现
### 1. 火锅店抽象类

```
public abstract class HotPotStore {
    abstract HotPot createHotPot(String item);
    public HotPot orderHotPot(String type) {
        HotPot hotPot = createHotPot(type);
        System.out.println("---------- 制作" + hotPot.getName() + "----------");
        hotPot.prepare();
        return hotPot;
    }
}
```
2. 火锅抽象类
```
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
```
3. 具体火锅店子类
- 重庆火锅店

```
public class ChongqingHotPotStore extends HotPotStore{
    HotPot createHotPot(String item) {
        if (item.equals("Yuanyang")) {
            return new ChongQingStyleYuanyangHotPot();
        } else if (item.equals("Maodu")) {
            return new ChongQingStyleMaoduHotPot();
        } else if (item.equals("Yangrou")) {
            return new ChongQingStyleYangrouHotPot();
        }  else return null;
    }
}
```
- 成都火锅店

```
public class ChengduHotPotStore extends HotPotStore{
    HotPot createHotPot(String item) {
        if (item.equals("Yuanyang")) {
            return new ChengDuStyleYuanyangHotPot();
        } else if (item.equals("Maodu")) {
            return new ChengDuStyleMaoduHotPot();
        } else if (item.equals("Yangrou")) {
            return new ChengDuStyleYangrouHotPot();
        }  else return null;
    }
}
```
4. 具体火锅子类

- 重庆口味鸳鸯火锅

```
public class ChongQingStyleYuanyangHotPot extends HotPot{
    public ChongQingStyleYuanyangHotPot() {
        name = "重庆鸳鸯火锅";
        meat = "猪肉";
        vegetables = "白菜";
        toppings.add("花椒 辣椒");
    }
}
```

- 重庆口味毛肚火锅

```
public class ChongQingStyleMaoduHotPot extends HotPot {
    public ChongQingStyleMaoduHotPot() {
        name = "重庆毛肚火锅";
        meat = "毛肚";
        vegetables = "芹菜";
        toppings.add("胡椒 辣椒");
    }
}

```

- 重庆口味羊肉火锅

```
public class ChongQingStyleYangrouHotPot extends HotPot {
    public ChongQingStyleYangrouHotPot() {
        name = "重庆羊肉火锅";
        meat = "羊肉";
        vegetables = "菠菜";
        toppings.add("酱油 辣椒");
    }
}
```

- 成都口味鸳鸯火锅

```
public class ChengDuStyleYuanyangHotPot extends HotPot{
    public ChengDuStyleYuanyangHotPot() {
        name = "成都鸳鸯火锅";
        meat = "猪肉";
        vegetables = "菠菜";
        toppings.add("胡椒 芝麻油");
    }
}
```

- 成都口味毛肚火锅

```
public class ChengDuStyleMaoduHotPot extends HotPot{
    public ChengDuStyleMaoduHotPot() {
        name = "成都毛肚火锅";
        meat = "毛肚";
        vegetables = "芹菜";
        toppings.add("花生油 酱油");
    }
}
```

- 成都口味羊肉火锅

```
public class ChengDuStyleYangrouHotPot extends HotPot{
    public ChengDuStyleYangrouHotPot() {
        name = "成都羊肉火锅";
        meat = "羊肉";
        vegetables = "土豆";
        toppings.add("蒜 葱");
    }
}
```

### 5.测试函数

```
public class HotPotTest {
    public static void main(String[] args) {
        HotPotStore ChongQingStore = new ChengduHotPotStore();
        HotPotStore ChengDuStore = new ChongqingHotPotStore();

        HotPot hotPot = ChongQingStore.orderHotPot("Yuanyang");
        System.out.println("一号顾客订购一份" + hotPot.getName() + "\n");

        hotPot = ChengDuStore.orderHotPot("Yuanyang");
        System.out.println("二号顾客订购一份" + hotPot.getName() + "\n");

        hotPot = ChongQingStore.orderHotPot("Maodu");
        System.out.println("三号顾客订购一份" + hotPot.getName() + "\n");

        hotPot = ChengDuStore.orderHotPot("Maodu");
        System.out.println("四号顾客订购一份" + hotPot.getName() + "\n");
    }
}
```