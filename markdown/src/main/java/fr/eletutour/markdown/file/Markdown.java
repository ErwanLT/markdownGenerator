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
package fr.eletutour.markdown.file;

import fr.eletutour.markdown.generator.FileGenerator;
import fr.eletutour.markdown.section.FileSection;
import fr.eletutour.markdown.section.Section;

import java.io.File;

public class Markdown implements MarkdownFile{

    private String name;

    private String title;

    private FileSection sections;

    private FileGenerator fileGenerator;

    public Markdown(){
        this.fileGenerator = new FileGenerator();
        this.sections = new FileSection();
    }

    public Markdown(String name, String title, FileSection sections, FileGenerator fileGenerator){
        this.name = name;
        this.title = title;
        this.sections = sections;
        this.fileGenerator = fileGenerator;
    }

    @Override
    public void addName(String name) {
        this.name = name;
    }

    @Override
    public void addTitle(String title) {
        this.title = title;
    }

    @Override
    public void addSection(Section section) {
        this.sections.addSection(section);
    }

    @Override
    public File generateFile() {
        return fileGenerator.generateFile(this);
    }

    @Override
    public String toString() {
        if(title != null){
            return "# " + title + "\n"
                    + sections.toString();
        } else {
            return "";
        }
    }

    public String getName(){
        return this.name;
    }
}
