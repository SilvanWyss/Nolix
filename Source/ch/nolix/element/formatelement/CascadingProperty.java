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
	private FormatElement<S> parentElement;
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
		final Class<S> stateClass,
		final ValueDetermination valueDetermination,
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
		return getValueWhenHasState(parentElement.getCurrentStateObject());
	}
	
	//method
	public boolean hasParentProperty() {
		return (parentProperty != null);
	}
	
	//method
	public boolean hasValue() {
		return hasValueWhenHasState(parentElement.getCurrentStateObject());
	}
	
	//method
	public void setUndefined() {
		for (final var sp : stateProperties) {
			sp.setUndefined();
		}
	}
	
	//method
	public void setUndefinedForState(final S state) {
		stateProperties[(getStateOf(state).getIndex())].setUndefined();
	}
	
	//method
	public void setValueForState(final S state, final V value) {
		stateProperties[(getStateOf(state).getIndex())].setValue(value);
	}
	
	//method
	void fillUpValuesSpecificationInto(final LinkedList<Node> list) {
		for (final var s : parentElement.getAvailableStates()) {
			
			final var stateProperty = stateProperties[s.getIndex()];
			
			switch (stateProperty.getAssignmentType()) {
				case VALUE:
					
					list.addAtBegin(
						Node.withHeaderAndAttribute(
							s.getPrefix() + getName(),
							specificationCreator.getOutput(stateProperty.getValue())
						)
					);
					
					break;
				case NO_VALUE:
					
						list.addAtBegin(Node.withHeaderAndAttribute(s.getPrefix() + getName(), NONE_HEADER));
						
						break;
				case UNDEFINED:
					break;
				
			}
		}
	}
	
	//method
	void setParentElement(final FormatElement<S> parentElement) {
		
		Validator.assertThat(parentElement).thatIsNamed("parent Element").isNotNull();
		
		this.parentElement = parentElement;
	}
	
	//method
	void setValueFromSpecification(final BaseNode specification) {
		
		for (final var s : parentElement.getAvailableStates()) {
			if (specification.getHeader().startsWith(s.getPrefix())) {
				setValueFromSpecificationToStateProperty(stateProperties[s.getIndex()], specification.getRefOneAttribute());
				break;
			}
		}
		
		throw new InvalidArgumentException(LowerCaseCatalogue.SPECIFICATION, specification, "is not valid");
	}
	
	//method
	private StateProperty<V> getRefBaseStateProperty() {
		return stateProperties[parentElement.getBaseStateObject().getIndex()];
	}
	
	//method
	private State<S> getStateOf(final S state) {
		return parentElement.getStateObjectFor(state);
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
		switch (specification.getHeader()) {
			case NONE_HEADER:
				stateProperty.clear();
				break;
			default:
				stateProperty.setValue(valueCreator.getOutput(specification));
		}
	}	
}
