package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.*;

public class MapShoppingCart implements ShoppingCart {

    private Map<Item, Integer> items = new HashMap<>();

    @Override
    public Collection<Item> getUniqueItems() {
        return items.keySet();
    }

    @Override
    public void addItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (items.containsKey(item)) {
            int occurrences = items.get(item);
            items.put(item, ++occurrences);
        } else {
            items.put(item, 1);
        }
    }

    @Override
    public void removeItem(Item item) throws ItemNotFoundException {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (!items.containsKey(item)) {
            throw new ItemNotFoundException();
        }
        int occurrences = items.get(item);
        if (occurrences == 1) {
            items.remove(item);
        }
        items.put(item, --occurrences);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice();
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        Set<Item> itemsSet = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (items.get(o1) > items.get(o2)) {
                    return -1;
                }
                return 1;
            }
        });
        itemsSet.addAll(items.keySet());
        return itemsSet;
    }
}