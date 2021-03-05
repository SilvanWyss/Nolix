//package declaration
package ch.nolix.commontest.demotest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.demo.BankAccount;
import ch.nolix.common.test.Test;

//class
public final class BankAccountTest extends Test {
	
	//method
	public void testCase_creation() {
		
		//execution
		final var testUnit = new BankAccount();
		
		//verification
		expect(testUnit.getAmount()).isEqualTo(0);
	}
	
	//method
	@TestCase
	public void testCase_deposit() {
		
		//setup
		final var testUnit = new BankAccount();
		
		//execution
		testUnit.deposit(100);
		
		//verification
		expect(testUnit.getAmount()).isEqualTo(100);
	}
	
	//method
	@TestCase
	public void testCase_withdraw() {
		
		//setup
		final var testUnit = new BankAccount();
		testUnit.deposit(100);
		
		//execution
		testUnit.withdraw(30);
		
		//verification
		expect(testUnit.getAmount()).isEqualTo(70);
	}
}
