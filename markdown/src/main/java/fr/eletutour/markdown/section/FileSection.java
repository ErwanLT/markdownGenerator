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
package fr.eletutour.markdown.section;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulation class for the file sections
 *
 * @author ewanletutour
 * @since v1.0
 */
public class FileSection {

    /**
     * the list of sections in the file
     */
    private final List<Section> sections;

    /**
     * constructor
     */
    public FileSection() {
        this.sections = new ArrayList<>();
    }

    /**
     * add a section to the sections list
     *
     * @param section the section to add
     */
    public void addSection(Section section) {
        this.sections.add(section);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Section s : sections) {
            sb.append(s.toString())
                    .append("\n");
        }
        return sb.toString();
    }
}
