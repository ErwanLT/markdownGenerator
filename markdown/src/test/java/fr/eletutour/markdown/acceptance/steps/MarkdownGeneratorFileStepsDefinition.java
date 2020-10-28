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
package fr.eletutour.markdown.acceptance.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.eletutour.markdown.file.Markdown;
import fr.eletutour.markdown.file.MarkdownFile;
import fr.eletutour.markdown.generator.FileGenerator;
import fr.eletutour.markdown.items.codes.Code;
import fr.eletutour.markdown.items.codes.SyntaxHighlightingCode;
import fr.eletutour.markdown.items.list.OrderedListItem;
import fr.eletutour.markdown.items.list.UnorderedListItem;
import fr.eletutour.markdown.section.FileSection;
import fr.eletutour.markdown.section.Section;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownGeneratorFileStepsDefinition {

    private MarkdownFile emptyMarkdownFile;
    private MarkdownFile contentMarkdownFile;

    private File generatedEmptyFile;
    private File generatedFileWithContent;

    @Before
    public void setUp() throws IOException {
        emptyMarkdownFile = new Markdown("EmptyTest", null, new FileSection(), new FileGenerator());
        contentMarkdownFile = new Markdown();
    }

    @When("^i ask to generate the file$")
    public void i_ask_to_generate_the_file() throws Throwable {
        generatedEmptyFile = emptyMarkdownFile.generateFile();

        contentMarkdownFile.addTitle("Title");
        contentMarkdownFile.addName("ContentTest");
        Section sectionList = new Section("List");
        sectionList.addItem(new UnorderedListItem("test"));
        sectionList.addItem(new UnorderedListItem("test 1"));
        sectionList.addItem(new OrderedListItem(1, "value"));
        sectionList.addItem(new OrderedListItem(2, "value 2"));
        contentMarkdownFile.addSection(sectionList);

        Section sectionCode = new Section("Code");
        sectionCode.addItem(new Code("{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Smith\",\n" +
                "  \"age\": 25\n" +
                "}"));
        contentMarkdownFile.addSection(sectionCode);

        Section sectionCodeHighlight = new Section("Syntax Highlighting");
        sectionCodeHighlight.addItem(new SyntaxHighlightingCode("{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Smith\",\n" +
                "  \"age\": 25\n" +
                "}", "json"));
        contentMarkdownFile.addSection(sectionCodeHighlight);

        generatedFileWithContent = contentMarkdownFile.generateFile();
    }

    @Then("^i should have an empty file$")
    public void i_should_have_an_empty_file() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedEmptyFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedEmptyFile.getPath()));
        assertThat(content).isEmpty();

        generatedEmptyFile.delete();
    }

    @Then("^i should have a file with content$")
    public void i_should_have_a_file_with_content() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedFileWithContent.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedFileWithContent.getPath()));
        assertThat(content).isNotEmpty().contains("# Title");

        assertThat(content).contains("## List");
        assertThat(content).contains("* test").contains("* test 1");
        assertThat(content).contains("1. value").contains("2. value 2");

        assertThat(content).contains("## Code");
        assertThat(content).contains("## Syntax Highlighting");

        assertThat(content).contains("```json");

        generatedFileWithContent.delete();
    }
}
