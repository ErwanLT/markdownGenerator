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
    private MarkdownFile titleMarkdownFile;
    private MarkdownFile listItemFile;
    private MarkdownFile codeItemFile;

    private File generatedEmptyFile;
    private File generatedTitleFile;
    private File generatedListItemFile;
    private File generatedCodeItemFile;

    @Before
    public void setUp() throws IOException {
        emptyMarkdownFile = new Markdown("EmptyTest", null, new FileSection(), new FileGenerator());
        titleMarkdownFile = new Markdown("TitleTest", "Title", new FileSection(), new FileGenerator());
        listItemFile = new Markdown("ListTest", "Title", new FileSection(), new FileGenerator());
    }

    @When("^i ask to generate the file$")
    public void i_ask_to_generate_the_file() throws Throwable {
        generatedEmptyFile = emptyMarkdownFile.generateFile();
        generatedTitleFile = titleMarkdownFile.generateFile();
    }

    @When("^i ask to generate a file whith section and list item$")
    public void i_ask_to_generate_a_file_whith_section_and_list_item() throws Throwable {
        Section section = new Section("Section Title");
        section.addItem(new UnorderedListItem("test"));
        section.addItem(new UnorderedListItem("test 1"));

        section.addItem(new OrderedListItem(1, "value"));
        section.addItem(new OrderedListItem(2, "value 2"));

        listItemFile.addSection(section);
        generatedListItemFile = listItemFile.generateFile();
    }

    @When("^i ask to generate a file with code block$")
    public void i_ask_to_generate_a_file_with_code_block() throws Throwable {
        codeItemFile = new Markdown();
        codeItemFile.addName("codeItemTest");
        codeItemFile.addTitle("Code Item File");

        Section section = new Section("Code");
        section.addItem(new Code("{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Smith\",\n" +
                "  \"age\": 25\n" +
                "}"));
        codeItemFile.addSection(section);

        Section section1 = new Section("Syntax Highlighting");
        section1.addItem(new SyntaxHighlightingCode("{\n" +
                "  \"firstName\": \"John\",\n" +
                "  \"lastName\": \"Smith\",\n" +
                "  \"age\": 25\n" +
                "}", "json"));
        codeItemFile.addSection(section1);

        generatedCodeItemFile = codeItemFile.generateFile();
    }

    @Then("^i should have an empty file$")
    public void i_should_have_an_empty_file() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedEmptyFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedEmptyFile.getPath()));
        assertThat(content).isEmpty();

        FileUtils.forceDelete(generatedEmptyFile);
    }

    @Then("^i should have a file with title$")
    public void i_should_have_a_file_with_title() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedTitleFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedTitleFile.getPath()));
        assertThat(content).isNotEmpty().contains("# Title");


        FileUtils.forceDelete(generatedTitleFile);
    }

    @Then("^i should have a file with section and list item$")
    public void i_should_have_a_file_with_section_and_list_item() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedListItemFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedListItemFile.getPath()));
        assertThat(content).isNotEmpty().contains("# Title");
        assertThat(content).contains("## Section Title");

        assertThat(content).contains("* test").contains("* test 1");
        assertThat(content).contains("1. value").contains("2. value 2");

        FileUtils.forceDelete(generatedListItemFile);
    }

    @Then("^i should have a file with section and code block$")
    public void i_should_have_a_file_with_section_and_code_block() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedCodeItemFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedCodeItemFile.getPath()));
        assertThat(content).isNotEmpty().contains("# Code Item File");
        assertThat(content).contains("## Code");
        assertThat(content).contains("## Syntax Highlighting");

        assertThat(content).contains("```json");

        FileUtils.forceDelete(generatedCodeItemFile);
    }
}
