package HotPot;

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
