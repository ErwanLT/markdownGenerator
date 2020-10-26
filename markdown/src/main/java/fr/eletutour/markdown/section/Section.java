package fr.eletutour.markdown.section;

import fr.eletutour.markdown.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Section {

    private String sectionTitle;

    List<Item> items;

    public Section(String sectionTitle){
        this.sectionTitle = sectionTitle;
        this.items = new ArrayList<>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder()
                .append("## " + sectionTitle)
                .append("\n");

        for (Item i:items) {
            sb.append(i.toString())
                    .append("\n");
        }

        return sb.toString();
    }
}
