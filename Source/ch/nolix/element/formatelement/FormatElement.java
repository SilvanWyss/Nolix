//package declaration
package ch.nolix.element.formatelement;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.layerelement.LayerProperty;

//class
public abstract class FormatElement<FE extends FormatElement<FE, S>, S extends Enum<S>>
implements IMutableElement<FE> {
	
	//static method
	private static boolean fieldStoresProperty(final Field field) {
		return Property.class.isAssignableFrom(field.getType());
	}
	
	//attributes
	private final State<S> baseState;
	private State<S> currentState;
	
	//multi-attributes
	private final IContainer<State<S>> availableStates;
	private IContainer<Property<S, ?>> properties;
	
	//constructor
	public FormatElement(final S baseState) {
		
		Validator.assertThat(baseState).thatIsNamed("base state").isNotNull();
		
		availableStates = new StateExtractor<S>().createtStatesFromState(baseState);
		this.baseState = availableStates.getRefFirst(s -> s.hasEnumValue(baseState));
		internalSwitchToState(baseState);
	}
	
	//method
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		
		for (final var p : getRefProperties()) {
			if (attribute.getHeader().endsWith(p.getName())) {
				p.setValueFromSpecification(attribute);
				return;
			}
		}
		
		throw new InvalidArgumentException(LowerCaseCatalogue.ATTRIBUTE, attribute, "is not valid");
	}
	
	//method
	@Override
	public final void fillUpAttributesInto(final LinkedList<Node> list) {
		for (final var p : getRefProperties()) {
			p.fillUpValuesSpecificationInto(list);
		}
	}
	
	//method
	public final S getBaseState() {
		return baseState.getEnumValue();
	}
	
	//method
	public final S getCurrentState() {
		return currentState.getEnumValue();
	}
	
	//method
	@Override
	public final void reset() {
		getRefProperties().forEach(Property::setUndefined);
	}
	
	//method
	final IContainer<State<S>> getAvailableStates() {
		return availableStates;
	}
	
	//method
	final State<S> getBaseStateObject() {
		return baseState;
	}
	
	//method
	final State<S> getCurrentStateObject() {
		return currentState;
	}
	
	//method
	final IContainer<Property<S, ?>> getRefProperties() {
		
		extractPropertiesIfNotExtracted();
		
		return properties;
	}
	
	//method
	final State<S> getStateObjectFor(final S state) {
		
		for (final var s : availableStates) {
			if (s.hasEnumValue(state)) {
				return s;
			}
		}
		
		throw new InvalidArgumentException(state);
	}
	
	//method
	final void setParent(final FormatElement<?, S> parentElement) {
		
		final var parentProperties = LinkedList.fromIterable(parentElement.getRefProperties());
		
		for (final var p: getRefProperties()) {
			p.setParentProperty(parentProperties.removeAndGetRefFirst(pp -> pp.hasSameNameAs(p)));
		}
		
		Validator.assertThat(parentProperties).thatIsNamed("remaining parent properties").isEmpty();
	}

	//method
	protected final <FE2 extends FE> void internalAddChild(final FE2 child) {
		
		Validator.assertThat(child).thatIsNamed(LowerCaseCatalogue.CHILD).isNotNull();
		
		child.setParent(this);
	}
	
	//method
	protected final void internalSwitchToState(final S state) {
		
		Validator.assertThat(state).thatIsNamed(LowerCaseCatalogue.STATE).isNotNull();
		
		currentState = availableStates.getRefFirst(s -> s.hasEnumValue(state));
	}
	
	//method
	private void extractPropertiesIfNotExtracted() {
		if (!propertiesAreExtracted()) {
			extractPropertiesWhenNotExtracted();
		}
	}
	
	//method
	private void extractPropertiesWhenNotExtracted() {
		
		final var lProperties = new LinkedList<Property<S, ?>>();
		fillUpPropertiesIntoList(lProperties);
		
		properties = lProperties;
		
		setItselsAsParentToProperties();
	}
	
	//method
	private void fillUpPotentialPropertyFromFieldIntoList(final Field field, final LinkedList<Property<S, ?>> list) {
		if (fieldStoresProperty(field)) {
			list.addAtEnd(getPropertyFromField(field));
		}
	}
	
	//method
	private void fillUpPropertiesIntoList(final LinkedList<Property<S, ?>> list) {
		Class<?> lClass = getClass();
		while (lClass != null) {
			fillUpPropertiesFromClassIntoList(lClass, list);
			lClass = lClass.getSuperclass();
		}
	}
	
	//method
	private void fillUpPropertiesFromClassIntoList(final Class<?> pClass, final LinkedList<Property<S, ?>> list) {
		for (final var f : pClass.getDeclaredFields()) {
			fillUpPotentialPropertyFromFieldIntoList(f, list);
		}
	}
	
	//method
	private Property<S, ?> getPropertyFromField(final Field field) {
		try {
			field.setAccessible(true);
			
			@SuppressWarnings("unchecked")
			final var property = (Property<S, ?>)(field.get(this));
			
			Validator.assertThat(property).isOfType(LayerProperty.class);
			
			return property;
		} catch (final IllegalAccessException illegalAccessException) {
			throw new WrapperException(illegalAccessException);
		}
	}
	
	//method
	private boolean propertiesAreExtracted() {
		return (properties != null);
	}
	
	//method
	private void setItselsAsParentToProperties() {
		for (final var p : getRefProperties()) {
			p.setParent(this);
		}
	}
}
