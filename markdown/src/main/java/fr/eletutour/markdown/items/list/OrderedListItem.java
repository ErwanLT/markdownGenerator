package fr.eletutour.markdown.items.list;

import fr.eletutour.markdown.items.Item;

public class OrderedListItem implements Item {

    private int index;

    private String value;

    public OrderedListItem(int index, String value){
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(index+". ")
                .append(value)
                .append("\n").toString();
    }
}
