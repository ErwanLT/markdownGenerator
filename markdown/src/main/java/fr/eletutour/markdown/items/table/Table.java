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
