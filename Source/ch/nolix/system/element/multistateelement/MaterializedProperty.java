//package declaration
package ch.nolix.system.element.multistateelement;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.systemapi.elementapi.multistateelementapi.ValueStoringState;

//class
public abstract class MaterializedProperty<S extends Enum<S>, V> extends Property<S> {
	
	//constant
	private static final String NONE_HEADER = "None";
	
	//attributes
	private final IElementTakerElementGetter<INode<?>, V> valueCreator;
	private final IElementTakerElementGetter<V, INode<?>> specificationCreator;
	
	//optional attribute
	private final I2ElementTaker<S, V> setterMethod;
	
	//multi-attribute
	protected final StateProperty<V>[] stateProperties;
	
	//constructor
	@SuppressWarnings("unchecked")
	protected MaterializedProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator
	) {
		
		super(name);
		
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		
		stateProperties = new StateProperty[stateClass.getEnumConstants().length];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		setterMethod = null;
		
		extractStateProperties();
	}
	
	//constructor
	@SuppressWarnings("unchecked")
	protected MaterializedProperty(
		final String name,
		final Class<S> stateClass,
		final IElementTakerElementGetter<INode<?>, V> valueCreator,
		final IElementTakerElementGetter<V, INode<?>> specificationCreator,
		final I2ElementTaker<S, V> setterMethod
	) {
		
		super(name);
		
		GlobalValidator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
		GlobalValidator.assertThat(specificationCreator).thatIsNamed("specification creator").isNotNull();
		GlobalValidator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();
		
		stateProperties = new StateProperty[stateClass.getEnumConstants().length];
		this.valueCreator = valueCreator;
		this.specificationCreator = specificationCreator;
		this.setterMethod = setterMethod;
		
		extractStateProperties();
	}
	
	//method
	public final ValueStoringState getAssignmentTypeForState(final S state) {
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
		return stateProperties[getStateOf(state).getIndex()].hasValueOrDefinesEmpty();
	}
	
	//method
	public void setUndefined() {
		for (final var sp : stateProperties) {
			sp.setForwarding();
		}
	}
	
	//method
	public void setUndefinedForState(final S state) {
		stateProperties[getStateOf(state).getIndex()].setForwarding();
	}
	
	//method
	public final void setValueForState(final S state, final V value) {
		stateProperties[getStateOf(state).getIndex()].setValue(value);
	}
	
	//method
	@Override
	protected final void fillUpValuesSpecificationInto(final IMutableList<INode<?>> list) {
		for (final var s : parent.getAvailableStates()) {
			
			final var stateProperty = stateProperties[s.getIndex()];
			
			switch (stateProperty.getAssignmentType()) {
				case STORING_VALUE:
					
					final var valueSpecification =
					Node.withHeaderAndChildNode(
						s.getPrefix() + getName(),
						specificationCreator.getOutput(stateProperty.getValue()).getRefSingleChildNode()
					);
					
					list.addAtEnd(valueSpecification);
					
					break;
				case DEFINING_EMPTY:
					
					list.addAtEnd(Node.withHeaderAndChildNode(s.getPrefix() + getName(), NONE_HEADER));
						
					break;
				case FORWARDING:
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
	protected final void setValueFromSpecification(final INode<?> specification) {
		
		for (final var s : parent.getAvailableStates()) {
			if (GlobalStringHelper.startsWithIgnoringCase(specification.getHeader(), s.getPrefix())) {
				setValueFromSpecificationToState(s, specification);
				return;
			}
		}
		
		throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.SPECIFICATION, specification);
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
				case STORING_VALUE:
					stateProperties[i].setValue((V)materializedProperty.stateProperties[i].getValue());
					break;
				case DEFINING_EMPTY:
					stateProperties[i].setEmpty();
					break;
				case FORWARDING:
					stateProperties[i].setForwarding();
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
	private void setValueFromSpecificationToState(final State<S> state, final INode<?> specification) {
		if (specification.getSingleChildNodeHeader().equals(NONE_HEADER)) {
			stateProperties[state.getIndex()].setEmpty();
		} else {
			setValueForStateUsingSetterMethod(state.getEnumValue(), valueCreator.getOutput(specification));
		}
	}
}
