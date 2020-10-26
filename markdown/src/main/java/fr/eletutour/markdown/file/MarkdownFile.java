package fr.eletutour.markdown.file;

import fr.eletutour.markdown.section.Section;

import java.io.File;

public interface MarkdownFile {

    void addName(String name);

    void addTitle(String title);

    void addSection(Section section);

    File generateFile();
}
