//package declaration
package ch.nolix.element.formatelement;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTakerElementGetter;

//class
public final class CascadingProperty<S extends Enum<S>, V> implements Named {
	
	//constant
	private static final String NONE_HEADER = "None";
	
	//attributes
	private final String name;
	private FormatElement<S> parent;
	private final ValueDetermination valueDetermination;
	private final IElementTakerElementGetter<BaseNode, V> valueCreator;
	private final IElementTakerElementGetter<V, Node> specificationCreator;
	
	//optional attributes
	private CascadingProperty<S, V> parentProperty;
	
	//multi-attribute
	private final StateProperty<V>[] stateProperties;
	
	//constructor
	public CascadingProperty(
		final String name,
		final ValueDetermination valueDetermination,
		final Class<S> stateClass,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		this(name, valueDetermination, stateClass.getEnumConstants().length, valueCreator, specificationCreator);
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	private CascadingProperty(
		final String name,
		final ValueDetermination valueDetermination,
		final int stateCount,
		final IElementTakerElementGetter<BaseNode, V> valueCreator,
		final IElementTakerElementGetter<V, Node> specificationCreator
	) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
		Validator.assertThat(valueDetermination).thatIsNamed(ValueDetermination.class).isNotNull();
		Validator.assertThat(stateCount).thatIsNamed("number of states").isPositive();
		Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		Validator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		this.name = name;
		this.valueDetermination = valueDetermination;
		stateProperties = new StateProperty[stateCount];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		
		for (var i = 0; i < stateProperties.length; i++) {
			stateProperties[i] = new StateProperty<>();
		}
	}
	
	//method
	public void clearForState(final S state) {
		stateProperties[(getStateOf(state).getIndex())].clear();
	}
	
	//method
	public AssignmentType getAssignmentTypeForState(final S state) {
		return stateProperties[(getStateOf(state).getIndex())].getAssignmentType();
	}
	
	//method
	public String getName() {
		return name;
	}
	
	//method
	public ValueDetermination getValueDetermination() {
		return valueDetermination;
	}
	
	//method
	public V getValue() {
		return getValueWhenHasState(parent.getCurrentStateObject());
	}
	
	//method
	public V getValueOfState(final S state) {
		return stateProperties[getStateOf(state).getIndex()].getValue();
	}
	
	//method
	public boolean hasParentProperty() {
		return (parentProperty != null);
	}
	
	//method
	public boolean hasValue() {
		return hasValueWhenHasState(parent.getCurrentStateObject());
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
	public void setValueForState(final S state, final V value) {
		stateProperties[getStateOf(state).getIndex()].setValue(value);
	}
	
	//method
	void fillUpValuesSpecificationInto(final LinkedList<Node> list) {
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
	void setParent(final FormatElement<S> parent) {
		
		Validator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
		
		this.parent = parent;
	}
	
	//method
	@SuppressWarnings("unchecked")
	void setParentProperty(final CascadingProperty<S, ?> parentProperty) {
		
		Validator.assertThat(parentProperty).thatIsNamed("parent property").isNotNull();
		
		this.parentProperty = (CascadingProperty<S, V>)parentProperty;
	}
	
	//method
	void setValueFromSpecification(final BaseNode specification) {
		
		for (final var s : parent.getAvailableStates()) {
			if (specification.getHeader().startsWith(s.getPrefix())) {
				setValueFromSpecificationToStateProperty(stateProperties[s.getIndex()], specification);
				return;
			}
		}
		
		throw new InvalidArgumentException(LowerCaseCatalogue.SPECIFICATION, specification, "is not valid");
	}
	
	//method
	private StateProperty<V> getRefBaseStateProperty() {
		return stateProperties[parent.getBaseStateObject().getIndex()];
	}
	
	//method
	private State<S> getStateOf(final S state) {
		return parent.getStateObjectFor(state);
	}
	
	//method
	private V getValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.isDefined()) {
			return stateProperty.getValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.isDefined()) {
			return baseStateProperty.getValue();
		}
		
		switch (getValueDetermination()) {
			case NON_CASCADING:
				break;
			case CASCADING:
				if (hasParentProperty()) {
					return parentProperty.getValueWhenHasState(state);
				}
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "value for the" + state.getPrefix() + " state");
	}

	//method
	private boolean hasValueWhenHasState(final State<S> state) {
		
		final var stateProperty = stateProperties[state.getIndex()];
		if (stateProperty.isDefined()) {
			return stateProperty.hasValue();
		}
		
		final var baseStateProperty = getRefBaseStateProperty();
		if (baseStateProperty.isDefined()) {
			return baseStateProperty.hasValue();
		}
				
		switch (getValueDetermination()) {
			case NON_CASCADING:
				return false;
			case CASCADING:
				return hasParentProperty() && parentProperty.hasValueWhenHasState(state);
			default:
				throw new InvalidArgumentException(this);
		}
	}
	
	//method
	private void setValueFromSpecificationToStateProperty(
		final StateProperty<V> stateProperty,
		final BaseNode specification
	) {
		if (specification.getOneAttributeHeader().equals(NONE_HEADER)) {
			stateProperty.clear();
		} else {
			stateProperty.setValue(valueCreator.getOutput(specification));
		}
	}	
}
