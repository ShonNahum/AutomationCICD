@tag
Feature: Error Validation
	
	@ErrorValidation
	Scenario Outline: Positive Test of Submitting the order	
		Given i landed on Ecommerce Page
		When logged in with username <name> and password <password>
		Then "Incorrect email or password." message is displayed
		
		Examples:
		| name			     	    | password    |
		| shon304082928@gmail.com   | Shonking12354 | 
	

	