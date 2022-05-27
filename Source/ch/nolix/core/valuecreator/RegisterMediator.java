//package declaration
package ch.nolix.core.valuecreator;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;

//class
public final class RegisterMediator<S, V> {
	
	//attributes
	private final ValueCreator<S> parentValueCreator;
	private final Class<V> valueClass;
	
	//constructor
	RegisterMediator(final ValueCreator<S> parentValueCreator, final Class<V> valueClass) {
		
		Validator.assertThat(parentValueCreator).thatIsNamed("parent value creator").isNotNull();
		Validator.assertThat(valueClass).thatIsNamed("value Class").isNotNull();
		
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
