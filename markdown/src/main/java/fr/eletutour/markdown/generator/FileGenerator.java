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
package fr.eletutour.markdown.generator;

import fr.eletutour.markdown.file.Markdown;

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
