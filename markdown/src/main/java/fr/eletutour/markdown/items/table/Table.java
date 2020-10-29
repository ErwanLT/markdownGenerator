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
package fr.eletutour.markdown.items.table;

import fr.eletutour.markdown.items.Item;


/**
 * This class describes a markdown table
 *
 * @author Brian Evans
 */
public class Table implements Item {
    /**
     * the header labels for the top row of the table
     */
    private final String[] headers;
    /**
     * the rows of data in the table
     */
    private final String[][] contents;

    /**
     * constructor
     *
     * @param headers  array of String headers
     * @param contents two dimensional array of strings of the data in the table
     */
    public Table(String[] headers, String[][] contents) {
        this.headers = headers;
        this.contents = contents;
    }

    /**
     * @return markdown representation of the table
     */
    @Override
    public String toString() {
        StringBuilder table = new StringBuilder("|");

        for (String header : headers)
            table.append(header).append("|");

        table.append("\n|").append("---|".repeat(headers.length));

        for (String[] row : contents) {
            table.append("\n|");
            for (String data : row)
                table.append(data).append("|");
        }

        return table.toString();
    }
}
