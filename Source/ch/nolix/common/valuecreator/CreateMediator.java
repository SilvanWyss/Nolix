//package declaration
package ch.nolix.common.valuecreator;

import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class CreateMediator<S, V> {
	
	//attribute
	private final SpecificValueCreator<S, V> parentSpecificValueCreator;
	
	//constructor
	CreateMediator(final SpecificValueCreator<S, V> parentSpecificValueCreator) {
		
		Validator.assertThat(parentSpecificValueCreator).thatIsNamed("parent SpecificValueCreator").isNotNull();
		
		this.parentSpecificValueCreator = parentSpecificValueCreator;
	}
	
	//method
	public V createFrom(final S source) {
		return parentSpecificValueCreator.createFrom(source);
	}
}
