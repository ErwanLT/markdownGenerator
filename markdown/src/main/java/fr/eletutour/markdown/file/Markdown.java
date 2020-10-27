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
