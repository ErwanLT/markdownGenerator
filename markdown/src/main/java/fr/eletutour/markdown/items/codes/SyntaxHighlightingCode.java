package fr.eletutour.markdown.items.codes;

import fr.eletutour.markdown.items.Item;

public class SyntaxHighlightingCode implements Item {

    private String value;

    private String langage;

    public SyntaxHighlightingCode(String value, String langage){
        this.value = value;
        this.langage = langage;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("```" + langage)
                .append("\n")
                .append(value)
                .append("\n")
                .append("```")
                .append("\n")
                .toString();
    }
}
