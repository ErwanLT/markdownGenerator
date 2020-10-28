package fr.eletutour.markdown.items.link;

import fr.eletutour.markdown.items.Item;

/**
 * Class describes a hyperlink in a markdown file
 */
public class Link implements Item {
    /**
     * the string that corresponds to the hyperlink location
     */
    private final String value;
    /**
     * the string that corresponds to the links display text
     */
    private final String text;
    /**
     * a string to add to the tooltip of the link
     */
    private final String tooltip;

    /**
     * constructor without tooltip
     *
     * @param value hyperlink location
     * @param text  the links display text
     */
    public Link(String value, String text) {
        this.value = value;
        this.text = text;
        this.tooltip = "";
    }

    /**
     * constructor with tooltip
     *
     * @param value   hyperlink location
     * @param text    the links display text
     * @param tooltip string to append to the tooltip
     */
    public Link(String value, String text, String tooltip) {
        this.value = value;
        this.text = text;
        this.tooltip = tooltip;
    }

    @Override
    public String toString() {
        if (tooltip.isEmpty())
            return '[' + text + "](" + value + ")";
        else
            return '[' + text + "](" + value + " \"" + tooltip + "\")";
    }
}
