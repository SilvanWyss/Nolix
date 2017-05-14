//package declaration
package ch.nolix.core.test;

import ch.nolix.core.validator2.Validator;

//class
public final class Accessor {

	private Test test;
	
	public Accessor(final Test test) {
		
		Validator.supposeThat(test).thatIsInstanceOf(Test.class);
		
		this.test = test;
	}
	
	public void addCurrentTestMethodError(final String currentTestMethodError) {
		test.addCurrentTestMethodError(currentTestMethodError);
	}
}
