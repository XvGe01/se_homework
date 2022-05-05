package HotPot;

public abstract class HotPotStore {
    abstract HotPot createHotPot(String item);
    public HotPot orderHotPot(String type) {
        HotPot hotPot = createHotPot(type);
        System.out.println("---------- 制作" + hotPot.getName() + "----------");
        hotPot.prepare();
        return hotPot;
    }
}
