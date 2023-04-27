@mobileFeature
Feature: Verify the product quantity and amount matches
@productTest
Scenario: Product verify

Given User Search for a item and Check that the product is displayed
#When Check the product details match the expected values fetched from excel sheet
Then Add the item to the cart
Then Delete the item from the cart and verify that item is deleted




#@quantityTest
#Scenario: verify quantity and price
#Given User Search for a item 
#When Check the product details
#Then Add item to the cart
#Then Verify that quantity and amount matches