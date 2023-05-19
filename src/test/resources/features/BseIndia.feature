@StoreValues
Feature: Store the values in Excel sheet

Scenario: In Gainers fetch the number of rows
Given  User is on bseindia homepage
When User clicks on Gainers 
And Expand by clicking on arrow button
Then Fetch the total number of rows displayed in all pages
And Store the values of every fifth in to Excel
