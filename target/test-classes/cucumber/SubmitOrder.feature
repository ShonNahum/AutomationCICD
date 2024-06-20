@tag
Feature: Purchase the order from Ecommerce Website
	
	
	Background:
	Given i landed on Ecommerce Page


	@Regression
	Scenario Outline: Positive Test of Submitting the order	
		Given logged in with username <name> and password <password>
		When i add the product <product> from Cart
		And  Checkout <productName> and submit the order	
		Then "THANKYOU FOR THE ORDER" message is displayed on ConfirmationPage
		
		Examples:
		| name			     	    | password    | productName		|
		| shon304082928@gmail.com   | Shonking123 | ZARA COAT 3  	|
	

	