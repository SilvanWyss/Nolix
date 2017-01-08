//package declaration
package ch.nolix.common.test;

import ch.nolix.common.zetaValidator.ZetaValidator;

//class
public final class Accessor {

	private Test test;
	
	public Accessor(final Test test) {
		
		ZetaValidator.supposeThat(test).thatIsInstanceOf(Test.class);
		
		this.test = test;
	}
	
	public void addCurrentTestMethodError(final String currentTestMethodError) {
		test.addCurrentTestMethodError(currentTestMethodError);
	}
}
