import pos.machine.PosMachine;

import java.util.List;

public class main {
    public static void main(String[] args) {
        PosMachine posMachine = new PosMachine();
        System.out.println(posMachine.printReceipt(List.of("ITEM000000", "ITEM000000", "ITEM000000","ITEM000001","ITEM000004","ITEM000004","ITEM000004")));
    }
}
