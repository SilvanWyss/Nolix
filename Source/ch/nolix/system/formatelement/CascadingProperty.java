//package declaration
package ch.nolix.system.formatelement;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class CascadingProperty<S extends Enum<S>, V> extends MaterializedProperty<S, V> {
	
	//attribute
	private final V defaultValue;
	
	//optional attribute
	private CascadingProperty<S, V> parentProperty;
	
	//constructor
	public CascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator);
		
		GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
		this.defaultValue = defaultValue;
	}
	
	//constructor
	public CascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final I2ElementTaker<S, V> setterMethod,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator, setterMethod);
		
		GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
		this.defaultValue = defaultValue;
	}
	
	//method
	@Override
	protected V getValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.hasValueOrIsEmpty()) {
			return stateProperty.getValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.hasValueOrIsEmpty()) {
			return baseStateProperty.getValue();
		}
		
		if (hasParentProperty()) {
			return parentProperty.getValueWhenHasState(state);
		}
		
		return defaultValue;
	}
	
	//method
	@Override
	protected boolean hasValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.hasValueOrIsEmpty()) {
			return stateProperty.hasValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.hasValueOrIsEmpty()) {
			return baseStateProperty.hasValue();
		}
		
		return hasParentProperty() && parentProperty.hasValueWhenHasState(state);
	}
		
	//method
	@SuppressWarnings("unchecked")
	void setParentProperty(final CascadingProperty<S, ?> parentProperty) {
		
		GlobalValidator.assertThat(parentProperty).thatIsNamed("parent property").isNotNull();
		
		this.parentProperty = (CascadingProperty<S, V>)parentProperty;
	}
	
	//method
	private boolean hasParentProperty() {
		return (parentProperty != null);
	}
}
