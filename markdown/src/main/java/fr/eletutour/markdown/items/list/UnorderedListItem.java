package fr.eletutour.markdown.items.list;

import fr.eletutour.markdown.items.Item;

public class UnorderedListItem implements Item {

    private String value;

    public UnorderedListItem(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "* " + this.value;
    }
}
