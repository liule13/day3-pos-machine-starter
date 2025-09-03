package pos.machine;

import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        List<ReceiptItem> receiptItems = decodeToItems(barcodes);
        return null;
    }
    private List<ReceiptItem> decodeToItems(List<String> barcodes) {
        List<Item> items = ItemsLoader.loadAllItems();
        List<ReceiptItem> receiptItems = null;


        for (String barcode : barcodes) {
            for (Item item : items) {
                if (!item.getBarcode().equals(barcode)) {
                    System.out.println("Barcode " + barcode + " not found in items.");
                }
            }
        }
        //转换为ReceiptItem
        for (String barcode : barcodes) {
            Item item = findItemByBarcode(barcode, items);
            if (item != null) {
                ReceiptItem receiptItem = findReceiptItemByName(item.getName(), receiptItems);
                if (receiptItem != null) {
                    int newQuantity = receiptItem.getQuantity() + 1;
                    receiptItems.remove(receiptItem);
                    receiptItems.add(new ReceiptItem(item.getName(), newQuantity, item.getPrice(), 0));
                } else {
                    receiptItems.add(new ReceiptItem(item.getName(), 1, item.getPrice(), item.getPrice()));
                }
            }
        }

        return receiptItems;
    }
    private ReceiptItem findReceiptItemByName(String name, List<ReceiptItem> receiptItems) {
        for (ReceiptItem receiptItem : receiptItems) {
            if (receiptItem.getName().equals(name)) {
                return receiptItem;
            }
        }
        return null;
    }

    private Item findItemByBarcode(String barcode, List<Item> items) {
        for (Item item : items) {
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        return null;
    }

}
