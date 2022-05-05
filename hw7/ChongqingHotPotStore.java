package HotPot;

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
