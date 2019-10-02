//package declaration
package ch.nolix.common.valueCreator;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;

//class
public final class CreateMediator<V> {
	
	//attribute
	private final SpecificValueCreator<V> parentSpecificValueCreator;
	
	//package-visible constructor
	CreateMediator(final SpecificValueCreator<V> parentSpecificValueCreator) {
		
		Validator.suppose(parentSpecificValueCreator).thatIsNamed("parent SpecificValueCreator").isNotNull();
		
		this.parentSpecificValueCreator = parentSpecificValueCreator;
	}
	
	//method
	public V createFromSpecification(final BaseNode specification) {
		return parentSpecificValueCreator.createFromSpecification(specification);
	}
	
	//method
	public V createFromString(final String string) {
		return parentSpecificValueCreator.createFromString(string);
	}
}
