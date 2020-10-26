package fr.eletutour.markdown.generator;

import fr.eletutour.markdown.file.Markdown;
import fr.eletutour.markdown.section.FileSection;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileGenerator {

    private static final String FILE_EXTENSION = ".md";

    public File generateFile(Markdown markdown) {
        File markdownFile = new File(markdown.getName() + FILE_EXTENSION);
        boolean result = false;

        try {
            result = markdownFile.createNewFile();
            if(result){
                if(!markdown.toString().isEmpty()){
                    Files.writeString(Path.of(markdownFile.getPath()), markdown.toString(), StandardOpenOption.APPEND);
                }
            } else {
                System.out.println("File already exist at location: "+markdownFile.getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return markdownFile;
    }
}
