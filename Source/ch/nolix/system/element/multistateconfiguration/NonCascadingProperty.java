//package declaration
package ch.nolix.system.element.multistateconfiguration;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;

//class
public final class NonCascadingProperty<S extends Enum<S>, V> extends MaterializedProperty<S, V> {
	
	//optional attribute
	private final V defaultValue;
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		super(name, stateClass, valueCreator, specificationCreator);
		
		defaultValue = null;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator);
		
		GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
		this.defaultValue = defaultValue;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator,
		final I2ElementTaker<S, V> setterMethod
	) {
		
		super(name, stateClass, valueCreator, specificationCreator, setterMethod);
		
		defaultValue = null;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator,
		final I2ElementTaker<S, V> setterMethod,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator, setterMethod);
		
		GlobalValidator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
		this.defaultValue = defaultValue;
	}
	
	//method
	public boolean hasDefaultValue() {
		return (defaultValue != null);
	}
	
	//method
	public void setEmptyForState(final S state) {
		stateProperties[(getStateOf(state).getIndex())].setEmpty();
	}
	
	//method
	@Override
	protected V getValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.hasValueOrDefinesEmpty()) {
			return stateProperty.getValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.hasValueOrDefinesEmpty()) {
			return baseStateProperty.getValue();
		}
		
		if (hasDefaultValue()) {
			return defaultValue;
		}
		
		throw
		ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(
			this,
			"value for the" + state.getPrefix() + " state"
		);
	}
	
	//method
	@Override
	protected boolean hasValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.hasValueOrDefinesEmpty()) {
			return stateProperty.hasValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.hasValueOrDefinesEmpty()) {
			return baseStateProperty.hasValue();
		}
		
		return false;
	}
}