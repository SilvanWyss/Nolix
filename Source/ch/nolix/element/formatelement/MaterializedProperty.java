//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.I2ElementTaker;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public abstract class MaterializedProperty<S extends Enum<S>, V> extends Property<S> {
	
	//constant
	private static final String NONE_HEADER = "None";
	
	//attributes
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//optional attribute
	private final I2ElementTaker<S, V> setterMethod;
	
	//multi-attribute
	protected final StateProperty<V>[] stateProperties;
	
	//constructor
	@SuppressWarnings("unchecked")
	public MaterializedProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		super(name);
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		stateProperties = new StateProperty[stateClass.getEnumConstants().length];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		setterMethod = null;
		
		extractStateProperties();
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	public MaterializedProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator,
		final I2ElementTaker<S, V> setterMethod
	) {
		
		super(name);
		
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		Validator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();
		
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
	public final SingleContainer<V> getOptionalValue() {
		
		if (!hasValue()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(getValue());
	}
	
	//method
	public final SingleContainer<V> getOptionalValueOfState(final S state) {
		
		final var stateProperty = stateProperties[getStateOf(state).getIndex()];
		
		if (!stateProperty.hasValue()) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(stateProperty.getValue());
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
	public final boolean hasSetterMethod() {
		return (setterMethod != null);
	}
	
	//method
	public final boolean hasValue() {
		return hasValueWhenHasState(parent.getCurrentStateObject());
	}
	
	//method
	public final boolean hasValueForState(final S state) {
		return stateProperties[getStateOf(state).getIndex()].hasValue();
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
	@Override
	protected final void fillUpValuesSpecificationInto(final LinkedList<Node> list) {
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
	protected final StateProperty<V> getRefBaseStateProperty() {
		return stateProperties[parent.getBaseStateObject().getIndex()];
	}
	
	//method
	protected final State<S> getStateOf(final S state) {
		return parent.getStateObjectFor(state);
	}

	//method declaration
	protected abstract V getValueWhenHasState(State<S> state);
	
	//mehod declaration
	protected abstract boolean hasValueWhenHasState(State<S> currentStateObject);
	
	@Override
	@SuppressWarnings("unchecked")
	protected void setFrom(Property<S> property) {
		setFrom((MaterializedProperty<S, V>)property);
	}
	
	//method
	@Override
	protected final void setValueFromSpecification(final BaseNode specification) {
		
		for (final var s : parent.getAvailableStates()) {
			if (GlobalStringHelper.startsWithIgnoringCase(specification.getHeader(), s.getPrefix())) {
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
	private void setFrom(final MaterializedProperty<S, V> materializedProperty) {
		for (var i = 0; i < stateProperties.length; i++) {
			switch (materializedProperty.stateProperties[i].getAssignmentType()) {
				case VALUE:
					stateProperties[i].setValue((V)materializedProperty.stateProperties[i].getValue());
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
	//For a better performance, this implementation does not use all comfortable methods.
	private void setValueForStateUsingSetterMethod(final S state, final V value) {
		if (setterMethod == null) {
			setValueForState(state, value);
		} else {
			setterMethod.run(state, value);
		}
	}
	
	//method
	private void setValueFromSpecificationToState(final State<S> state, final BaseNode specification) {
		if (specification.getOneAttributeHeader().equals(NONE_HEADER)) {
			stateProperties[state.getIndex()].setEmpty();
		} else {
			setValueForStateUsingSetterMethod(state.getEnumValue(), valueCreator.getOutput(specification));
		}
	}
}
