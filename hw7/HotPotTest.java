package HotPot;

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
