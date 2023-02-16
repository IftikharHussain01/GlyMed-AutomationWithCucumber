@GlyMed
Feature: Login and Registration scenarios

  @proUserLogin
  Scenario: Validated that user is able to login with "PRO_USER" user and purchase cleanser product
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "PRO_USER"
    Then user enters the password as "PRO_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page
    When user click on products option from the dashboard
    And validate that user can see addictive label
    Then user selects the category as Cleanser
    And validate cleanser title is displayed
    Then user selects first cleanser product
    Then move down to the back bar option
    And set the trial and travel values as "2"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    Then user select the shipping option as standard
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    And validate user has payment method available as card
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen
    And validate that amount should be equal before and after order place