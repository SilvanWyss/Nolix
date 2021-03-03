//package declaration
package ch.nolix.common.demoobject;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class Account {
	
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
