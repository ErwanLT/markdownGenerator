package fr.eletutour.markdown.file;

import fr.eletutour.markdown.generator.FileGenerator;
import fr.eletutour.markdown.section.FileSection;
import fr.eletutour.markdown.section.Section;

import java.io.File;

public class Markdown implements MarkdownFile{

    private String name;

    private final FileSection sections;

    private final FileGenerator fileGenerator;

    public Markdown(String name, FileSection sections, FileGenerator fileGenerator){
        this.name = name;
        this.sections = sections;
        this.fileGenerator = fileGenerator;
    }

    @Override
    public void addTitle(String title) {
        this.name = title;
    }

    @Override
    public void addSection(Section section) {
        this.sections.addSection(section);
    }

    @Override
    public File generateFile() {
        return fileGenerator.generateFile(name, sections);
    }
}
