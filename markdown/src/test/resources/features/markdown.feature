Feature: Markdown file generator

  Scenario: Generate empty file
    When i ask to generate the file
    Then i should have an empty file

  Scenario: Generate file
    When i ask to generate the file
    Then i should have a file with content