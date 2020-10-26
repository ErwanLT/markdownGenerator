package fr.eletutour.markdown.acceptance.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.eletutour.markdown.file.Markdown;
import fr.eletutour.markdown.file.MarkdownFile;
import fr.eletutour.markdown.generator.FileGenerator;
import fr.eletutour.markdown.section.FileSection;
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

    private File generatedEmptyFile;

    private File generatedTitleFile;

    @Before
    public void setUp() throws IOException {
        tearDown();
        emptyMarkdownFile = new Markdown("EmptyTest", null, new FileSection(), new FileGenerator());
        titleMarkdownFile = new Markdown("TitleTest", "Title", new FileSection(), new FileGenerator());
    }

    public void tearDown() throws IOException {
        FileUtils.forceDelete(new File("EmptyTest.md"));
        FileUtils.forceDelete(new File("TitleTest.md"));
    }

    @When("^i ask to generate the file$")
    public void i_ask_to_generate_the_file() throws Throwable {
        generatedEmptyFile = emptyMarkdownFile.generateFile();
        generatedTitleFile = titleMarkdownFile.generateFile();
    }

    @Then("^i should have an empty file$")
    public void i_should_have_an_empty_file() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedEmptyFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedEmptyFile.getPath()));
        assertThat(content).isEmpty();
    }

    @Then("^i should have a file with title$")
    public void i_should_have_a_file_with_title() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedTitleFile.getName())).isEqualTo("md");
        String content = Files.readString(Path.of(generatedTitleFile.getPath()));
        assertThat(content).isNotEmpty().contains("# Title");
    }
}
