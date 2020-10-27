package fr.eletutour.markdown.items.codes;

import fr.eletutour.markdown.items.Item;

public class Code implements Item {
    private String value;

    public Code(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("```")
                .append("\n")
                .append(value)
                .append("\n")
                .append("```")
                .append("\n")
                .toString();
    }
}
