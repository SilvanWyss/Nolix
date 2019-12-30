//package declaration
package ch.nolix.common.valueCreator;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;

//class
public final class RegisterMediator<S, V> {
	
	//attributes
	private final ValueCreator<S> parentValueCreator;
	private final Class<V> valueClass;
	
	//constructor
	RegisterMediator(final ValueCreator<S> parentValueCreator, final Class<V> valueClass) {
		
		Validator.suppose(parentValueCreator).thatIsNamed("parent value creator").isNotNull();
		Validator.suppose(valueClass).thatIsNamed("value Class").isNotNull();
		
		this.parentValueCreator = parentValueCreator;
		this.valueClass = valueClass;
	}
	
	//method
	public boolean canCreateValues() {
		return parentValueCreator.canCreateValuesOf(valueClass);
	}
	
	//method
	public void registerCreator(final IElementTakerElementGetter<S, V> creator) {
		parentValueCreator.registerSpecificValueCreator(
			new SpecificValueCreator<>(valueClass, creator)
		);
	}
}
