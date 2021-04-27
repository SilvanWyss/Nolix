//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.I2ElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public final class NonCascadingProperty<S extends Enum<S>, V> extends MaterializedProperty<S, V> {
	
	//optional attribute
	private final V defaultValue;
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		super(name, stateClass, valueCreator, specificationCreator);
		
		defaultValue = null;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator);
		
		Validator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
		this.defaultValue = defaultValue;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final I2ElementTaker<S, V> setterMethod
	) {
		
		super(name, stateClass, valueCreator, specificationCreator, setterMethod);
		
		defaultValue = null;
	}
	
	//constructor
	public NonCascadingProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final I2ElementTaker<S, V> setterMethod,
		final V defaultValue
	) {
		
		super(name, stateClass, valueCreator, specificationCreator, setterMethod);
		
		Validator.assertThat(defaultValue).thatIsNamed(LowerCaseCatalogue.DEFAULT_VALUE).isNotNull();
		
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
		if (stateProperty.hasValueOrIsEmpty()) {
			return stateProperty.getValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.hasValueOrIsEmpty()) {
			return baseStateProperty.getValue();
		}
		
		if (hasDefaultValue()) {
			return defaultValue;
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "value for the" + state.getPrefix() + " state");
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
		
		return false;
	}
}
