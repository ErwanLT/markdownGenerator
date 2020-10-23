package fr.eletutour.markdown.acceptance.steps;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.eletutour.markdown.file.Markdown;
import fr.eletutour.markdown.file.MarkdownFile;
import fr.eletutour.markdown.generator.FileGenerator;
import org.apache.commons.io.FilenameUtils;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownGeneratorFileStepsDefinition {

    private MarkdownFile markdownFile;

    private File generatedFile;

    @Before
    public void setUp(){
        markdownFile = new Markdown("Test", null, new FileGenerator());
    }

    @When("^i ask to generate the file$")
    public void i_ask_to_generate_the_file() throws Throwable {
        generatedFile = markdownFile.generateFile();
    }

    @Then("^i should have an empty file$")
    public void i_should_have_an_empty_file() throws Throwable {
        assertThat(FilenameUtils.getExtension(generatedFile.getName())).isEqualTo("md");
        assertThat(generatedFile.length()).isEqualTo(0);
    }
}
