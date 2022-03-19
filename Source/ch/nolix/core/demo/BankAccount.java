//package declaration
package ch.nolix.core.demo;

//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class BankAccount {
	
	//attribute
	private int amount;
	
	//method
	public synchronized void deposit(final int amount) {
		
		Validator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isPositive();
		
		this.amount += amount;
	}
	
	//method
	public synchronized int getAmount() {
		return amount;
	}
	
	//method
	public synchronized void withdraw(final int amount) {
		
		Validator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isPositive();
		Validator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isSmallerThanOrEquals(this.amount);
		
		this.amount -= amount;
	}
}
