# ����Ĺ�������ģʽ
## һ����������ģʽ
- ���壺��������ģʽ����һ�����ڴ�������Ľӿڣ����������ʵ������һ���ࡣ��������ʹһ�����ʵ�����ӳٵ������ࡣ

- ʵ�ʣ���ģʽ�ĺ��ľ����Ƿ�װ���б仯�Ĳ��֣���ȡ���и��Ի��Ʊ�Ĳ���Ϊ�����࣬ͨ������ע���Դﵽ������úͷ������ά����չ��Ŀ�ġ����ĺ��Ľṹ���ĸ���ɫ���ֱ��ǳ��󹤳������幤���������Ʒ�������Ʒ��

- �ŵ㣺������ֻ��ע������Ľӿڶ��壬������Ĳ�Ʒʵ����������Ĺ�������ȥ��������ϵͳ��Ҫ����һ����Ʒ�ǣ������޸�����ϵͳ���룬ֻ��Ҫ���һ�������Ʒ������Ӧ�Ĺ������࣬ʹϵͳ����չ�Ա�úܺã�������������̵Ŀ���ԭ��

- ʹ�ó�����
     - ����ĳ����Ʒ�������������֪��Ӧ��ʹ���ĸ����幤������ʵ�����þ��幤��������������Ĳ�Ʒ����Java Collection�е�iterator() �������������������

     - ֻ����Ҫһ�ֲ�Ʒ��������֪��Ҳ����Ҫ֪���������ĸ�����Ϊ�����ģ�������ѡ���ĸ����幤���ľ���Ȩ��������һ�������Ǹ��ݵ�ǰϵͳ�������ʵ����һ������Ĺ������ظ�ʹ���ߣ���������߹��������ʹ������˵��͸����

## ��������Ĺ�������ģʽʵ��
### 1. ����������

```
public abstract class HotPotStore {
    abstract HotPot createHotPot(String item);
    public HotPot orderHotPot(String type) {
        HotPot hotPot = createHotPot(type);
        System.out.println("---------- ����" + hotPot.getName() + "----------");
        hotPot.prepare();
        return hotPot;
    }
}
```
2. ���������
```
import java.util.ArrayList;
public abstract class HotPot {
    String name;
    String meat;
    String vegetables;
    ArrayList<String> toppings = new ArrayList<String>();
    void prepare() {
        System.out.println("׼�� " + name);
        System.out.println("�����...");
        System.out.println("����߲�...");
        for (String material : toppings) {
            System.out.println("   " + material);
        }
    }
    void cook() {
        System.out.println("���60����");
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
3. ������������
- ��������

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
- �ɶ������

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
4. ����������

- �����ζԧ����

```
public class ChongQingStyleYuanyangHotPot extends HotPot{
    public ChongQingStyleYuanyangHotPot() {
        name = "����ԧ����";
        meat = "����";
        vegetables = "�ײ�";
        toppings.add("���� ����");
    }
}
```

- �����ζë�ǻ��

```
public class ChongQingStyleMaoduHotPot extends HotPot {
    public ChongQingStyleMaoduHotPot() {
        name = "����ë�ǻ��";
        meat = "ë��";
        vegetables = "�۲�";
        toppings.add("���� ����");
    }
}

```

- �����ζ������

```
public class ChongQingStyleYangrouHotPot extends HotPot {
    public ChongQingStyleYangrouHotPot() {
        name = "����������";
        meat = "����";
        vegetables = "����";
        toppings.add("���� ����");
    }
}
```

- �ɶ���ζԧ����

```
public class ChengDuStyleYuanyangHotPot extends HotPot{
    public ChengDuStyleYuanyangHotPot() {
        name = "�ɶ�ԧ����";
        meat = "����";
        vegetables = "����";
        toppings.add("���� ֥����");
    }
}
```

- �ɶ���ζë�ǻ��

```
public class ChengDuStyleMaoduHotPot extends HotPot{
    public ChengDuStyleMaoduHotPot() {
        name = "�ɶ�ë�ǻ��";
        meat = "ë��";
        vegetables = "�۲�";
        toppings.add("������ ����");
    }
}
```

- �ɶ���ζ������

```
public class ChengDuStyleYangrouHotPot extends HotPot{
    public ChengDuStyleYangrouHotPot() {
        name = "�ɶ�������";
        meat = "����";
        vegetables = "����";
        toppings.add("�� ��");
    }
}
```

### 5.���Ժ���

```
public class HotPotTest {
    public static void main(String[] args) {
        HotPotStore ChongQingStore = new ChengduHotPotStore();
        HotPotStore ChengDuStore = new ChongqingHotPotStore();

        HotPot hotPot = ChongQingStore.orderHotPot("Yuanyang");
        System.out.println("һ�Ź˿Ͷ���һ��" + hotPot.getName() + "\n");

        hotPot = ChengDuStore.orderHotPot("Yuanyang");
        System.out.println("���Ź˿Ͷ���һ��" + hotPot.getName() + "\n");

        hotPot = ChongQingStore.orderHotPot("Maodu");
        System.out.println("���Ź˿Ͷ���һ��" + hotPot.getName() + "\n");

        hotPot = ChengDuStore.orderHotPot("Maodu");
        System.out.println("�ĺŹ˿Ͷ���һ��" + hotPot.getName() + "\n");
    }
}
```