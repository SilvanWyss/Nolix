//package declaration
package ch.nolix.common.valuecreator;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;

//class
public final class SpecificValueCreator<S, V> {
	
	//attributes
	private final Class<V> valueClass;
	private final IElementTakerElementGetter<S, V> creator;
	private final CreateMediator<S, V> createMediator;
	
	//constructor
	public SpecificValueCreator(final Class<V> valueClass, final IElementTakerElementGetter<S, V> creator) {
		
		Validator.assertThat(valueClass).thatIsNamed("value Class").isNotNull();
		Validator.assertThat(creator).thatIsNamed(VariableNameCatalogue.CREATOR).isNotNull();
		
		this.valueClass = valueClass;
		this.creator = creator;
		
		createMediator = new CreateMediator<>(this);
	}
	
	//method
	public boolean canCreateValuesOf(final Class<?> type) {
		return (valueClass == type);
	}

	//method
	public boolean canCreateValuesOf(final String type) {
		return valueClass.getSimpleName().equals(type);
	}

	//method
	public V createFrom(final S source) {
		return creator.getOutput(source);
	}
	
	//method
	public CreateMediator<S, V> getRefCreateMediator() {
		return createMediator;
	}
	
	//method
	public Class<?> getRefValueClass() {
		return valueClass;
	}

	//method
	public String getValueType() {
		return valueClass.getSimpleName();
	}
}
