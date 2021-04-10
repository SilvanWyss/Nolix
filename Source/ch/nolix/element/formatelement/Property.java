//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.I2ElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public abstract class Property<S extends Enum<S>, V> implements Named {
	
	//constant
	private static final String NONE_HEADER = "None";
	
	//attributes
	private final String name;
	private FormatElement<?, S> parent;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//optional attributes
	protected CascadingProperty<S, V> parentProperty;
	private final I2ElementTaker<S, V> setterMethod;
	
	//multi-attribute
	protected final StateProperty<V>[] stateProperties;
	
	//constructor
	@SuppressWarnings("unchecked")
	public Property(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		stateProperties = new StateProperty[stateClass.getEnumConstants().length];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		setterMethod = null;
		
		extractStateProperties();
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public Property(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final I2ElementTaker<S, V> setterMethod
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		Validator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();
		
		this.name = name;
		stateProperties = new StateProperty[stateClass.getEnumConstants().length];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		this.setterMethod = setterMethod;
		
		extractStateProperties();
	}
	
	//method
	public final AssignmentType getAssignmentTypeForState(final S state) {
		return stateProperties[(getStateOf(state).getIndex())].getAssignmentType();
	}
	
	//method
	@Override
	public final String getName() {
		return name;
	}
	
	//method
	public final V getValue() {
		return getValueWhenHasState(parent.getCurrentStateObject());
	}
	
	//method
	public final V getValueOfState(final S state) {
		return stateProperties[getStateOf(state).getIndex()].getValue();
	}
	
	//method
	public final boolean hasValue() {
		return hasValueWhenHasState(parent.getCurrentStateObject());
	}
	
	//method
	public final boolean hasSetterMethod() {
		return (setterMethod != null);
	}
	
	//method
	public final boolean hasValueOrIsEmptyForState(final S state) {
		return stateProperties[getStateOf(state).getIndex()].hasValueOrIsEmpty();
	}
	
	//method
	public void setUndefined() {
		for (final var sp : stateProperties) {
			sp.setUndefined();
		}
	}
	
	//method
	public void setUndefinedForState(final S state) {
		stateProperties[getStateOf(state).getIndex()].setUndefined();
	}
	
	//method
	public final void setValueForState(final S state, final V value) {
		stateProperties[getStateOf(state).getIndex()].setValue(value);
	}
	
	//method
	protected final StateProperty<V> getRefBaseStateProperty() {
		return stateProperties[parent.getBaseStateObject().getIndex()];
	}
	
	//method
	protected final State<S> getStateOf(final S state) {
		return parent.getStateObjectFor(state);
	}

	//method declaration
	protected abstract V getValueWhenHasState(State<S> state);

	//method
	protected final boolean hasParentProperty() {
		return (parentProperty != null);
	}

	//mehod declaration
	protected abstract boolean hasValueWhenHasState(State<S> currentStateObject);

	//method
	final void fillUpValuesSpecificationInto(final LinkedList<Node> list) {
		for (final var s : parent.getAvailableStates()) {
			
			final var stateProperty = stateProperties[s.getIndex()];
			
			switch (stateProperty.getAssignmentType()) {
				case VALUE:
					
					list.addAtEnd(
						specificationCreator.getOutput(stateProperty.getValue()).setHeader(s.getPrefix() + getName())
					);
					
					break;
				case NO_VALUE:
					
					list.addAtEnd(Node.withHeaderAndAttribute(s.getPrefix() + getName(), NONE_HEADER));
						
					break;
				case UNDEFINED:
					break;
				
			}
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	final void setFrom(final Property<S, ?> property) {
		for (var i = 0; i < stateProperties.length; i++) {
			switch (property.stateProperties[i].getAssignmentType()) {
				case VALUE:
					stateProperties[i].setValue((V)property.stateProperties[i].getValue());
					break;
				case NO_VALUE:
					stateProperties[i].setEmpty();
					break;
				case UNDEFINED:
					stateProperties[i].setUndefined();
					break;
			}
		}
	}
	
	//method
	final void setParent(final FormatElement<?, S> parent) {
		
		Validator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
		
		this.parent = parent;
	}
	
	//method
	@SuppressWarnings("unchecked")
	final void setParentProperty(final Property<S, ?> parentProperty) {
		
		Validator.assertThat(parentProperty).thatIsNamed("parent property").isNotNull();
		
		this.parentProperty = (CascadingProperty<S, V>)parentProperty;
	}
	
	//method
	final void setValueFromSpecification(final BaseNode specification) {
		
		for (final var s : parent.getAvailableStates()) {
			if (specification.getHeader().startsWith(s.getPrefix())) {
				setValueFromSpecificationToState(s, specification);
				return;
			}
		}
		
		throw new InvalidArgumentException(LowerCaseCatalogue.SPECIFICATION, specification, "is not valid");
	}
	
	//method
	private void extractStateProperties() {
		for (var i = 0; i < stateProperties.length; i++) {
			stateProperties[i] = new StateProperty<>();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void setValueForStateUsingSetterMethod(final S state, final V value) {
		if (setterMethod == null) {
			setValueForState(state, value);
		} else {
			setterMethod.run(state, value);
		}
	}
	
	//method
	private void setValueFromSpecificationToState(final State<S> state,	final BaseNode specification) {
		if (specification.getOneAttributeHeader().equals(NONE_HEADER)) {
			stateProperties[state.getIndex()].setEmpty();
		} else {
			setValueForStateUsingSetterMethod(state.getEnumValue(), valueCreator.getOutput(specification));
		}
	}
}
