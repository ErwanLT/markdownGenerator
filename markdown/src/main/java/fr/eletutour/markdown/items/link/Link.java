/*-
 * ========================LICENSE_START=================================
 * markdown
 * ======================================================================
 * Copyright (C) 2020 Erwan Le Tutour
 * ======================================================================
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * =========================LICENSE_END==================================
 */
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
