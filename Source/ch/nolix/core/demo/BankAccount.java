//package declaration
package ch.nolix.core.demo;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;

//class
public final class BankAccount {
	
	//attribute
	private int amount;
	
	//method
	public synchronized void deposit(final int amount) {
		
		GlobalValidator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isPositive();
		
		this.amount += amount;
	}
	
	//method
	public synchronized int getAmount() {
		return amount;
	}
	
	//method
	public synchronized void withdraw(final int amount) {
		
		GlobalValidator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isPositive();
		GlobalValidator.assertThat(amount).thatIsNamed(PascalCaseCatalogue.AMOUNT).isSmallerThanOrEquals(this.amount);
		
		this.amount -= amount;
	}
}
