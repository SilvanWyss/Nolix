//package declaration
package ch.nolix.core.valuecreator;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class CreateMediator<S, V> {
	
	//attribute
	private final SpecificValueCreator<S, V> parentSpecificValueCreator;
	
	//constructor
	CreateMediator(final SpecificValueCreator<S, V> parentSpecificValueCreator) {
		
		GlobalValidator.assertThat(parentSpecificValueCreator).thatIsNamed("parent SpecificValueCreator").isNotNull();
		
		this.parentSpecificValueCreator = parentSpecificValueCreator;
	}
	
	//method
	public V createFrom(final S source) {
		return parentSpecificValueCreator.createFrom(source);
	}
}
