package fr.eletutour.markdown.generator;

import fr.eletutour.markdown.section.FileSection;

import java.io.File;

public class FileGenerator {

    private static final String FILE_EXTENSION = ".md";

    public File generateFile(String name, FileSection sections) {
        return new File(name + FILE_EXTENSION);
    }
}
