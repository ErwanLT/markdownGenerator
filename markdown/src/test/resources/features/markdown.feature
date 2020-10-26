Feature: Markdown file generator

  Scenario: Generate empty file
    When i ask to generate the file
    Then i should have an empty file

  Scenario: Generate file with title
    When i ask to generate the file
    Then i should have a file with title

  Scenario: Generate file with section and list item
    When i ask to generate a file whith section and list item
    Then i should have a file with section and list item