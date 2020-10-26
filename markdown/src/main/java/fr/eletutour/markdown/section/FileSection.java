package fr.eletutour.markdown.section;

import java.util.ArrayList;
import java.util.List;

public class FileSection {

    private final List<Section> sections;

    public FileSection(){
        this.sections = new ArrayList<>();
    }

    public void addSection(Section section){
        this.sections.add(section);
    }

    public String toString(){
        return "";
    }
}
