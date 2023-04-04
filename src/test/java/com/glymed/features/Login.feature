@GlyMed
Feature: Login and Registration scenarios

  @proUserLogin
  Scenario: Validated that user is able to login with "PRO_USER" user and able to place a order with two different products
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
#    Then move down to the back bar option
    And set the trial and travel values as "1"
    When user click on add to cart button
    And user click on close button for checkout screen
    And user click on glymed label from dashboard
    When user click on products option from the dashboard
    Then user click one more product in the cart
    And enter the quantity for retail as "1"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    And user click on shipping dropdown
    Then user select the shipping option as standard
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    And validate user has payment method available as card
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen
    And validate that shipping method is same as per the user selection
#    And validate that amount should be equal before and after order place

  @proUserLogin
  Scenario Outline: Verify when base "<rim>" account place order for "Exfoliator" category
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "<rim>"
    Then user enters the password as "<account>"
    And user click on sign in button
    Then validate that user is present on dashboard Page
    When user click on products option from the dashboard
    And validate that user can see addictive label
    When user enters "Nail Polish" in the search field
    Then validate that user is displayed no products message
    And user click exfoliator check mark
    And validate that exfoliator title is displayed
    Then select first product from exfoliator category
    And enter the amount in the retail text field as "1"
    And enter the amount in the trial text field as "1"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    And user click on shipping dropdown
    Then user select the shipping option as standard
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    And validate user has payment method available as card
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen

    Examples:
      | rim         | account         |
      | PRO_USER    | PRO_PASSWORD    |
      | RETAIL_USER | RETAIL_PASSWORD |

  @proUserLogin
  Scenario: Verify that user is able to purchase the product using redeem points functionality
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "PRO_USER"
    Then user enters the password as "PRO_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page
    When user click on products option from the dashboard
    And validate that user can see addictive label
    Then user click on serum product checkmark
    And user select daily life serum from list
    And user enters the value for retail purchase as "1"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    Then user get redeemable points of the customer
    And user checkMark to use the points
    And user click on shipping dropdown
    And user selects the shipping option by Air
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen
    And validate that shipping method is same as per the user selection

  @proUserLogin
  Scenario: Verify Shipping option and select next day delivery shipping option to purchase
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "PRO_USER"
    Then user enters the password as "PRO_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page
    When user click on products option from the dashboard
    And validate that user can see addictive label
    Then user click on serum product checkmark
    And user select daily life serum from list
    And user enters the value for retail purchase as "1"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    And user click on shipping dropdown
    And user selects the shipping option by Air
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    And validate user has payment method available as card
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen

  @proUserLogin
  Scenario: Verify Shipping option and select By Air delivery shipping option to purchase
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "PRO_USER"
    Then user enters the password as "PRO_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page
    When user click on products option from the dashboard
    And validate that user can see addictive label
    Then user click on moisturizer product checkmark
    When user select atraxi product
    And user enters the value for retail moisturiser as "1"
    And user enters the value for black bar moisturiser as "1"
    When user click on add to cart button
    Then validate checkout screen is displayed and validate the amount
    And user click on checkout button
    And validate user is navigated to checkout screen
    And user click on shipping dropdown
    And user selects the shipping option by second day
    And user enter the instruction as "Please ship it as soon as possible"
    Then user click on continue to payment button
    And validate user has payment method available as card
    When user click review your order button
    Then user click place your order button
    And user click on dont use points button
    And user agrees the terms and conditions
    Then validate user is navigated to the success screen

  @RetailUserLogin
  Scenario: Validated that user is able to login with "RETAIL_USER" user
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "RETAIL_USER"
    Then user enters the password as "RETAIL_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page

  @SchoolUserLogin
  Scenario: Validated that user is able to login with "SCHOOL_USER" user
    Given user navigate to glymed home page
    Then validate user is present on sign in screen
    When user enters the user name as "SCHOOL_USER"
    Then user enters the password as "SCHOOL_PASSWORD"
    And user click on sign in button
    Then validate that user is present on dashboard Page