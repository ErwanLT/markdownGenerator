package fr.eletutour.markdown.acceptance.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.eletutour.markdown.file.Markdown;
import fr.eletutour.markdown.file.MarkdownFile;
import fr.eletutour.markdown.generator.FileGenerator;
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

    private File generatedEmptyFile;
    private File generatedTitleFile;
    private File generatedListItemFile;

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
}
