//package declaration
package ch.nolix.system.element.multistateelement;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.elementapi.multistateelementapi.IMultiStateElement;

//class
public abstract class MultiStateElement<
	MSE extends IMultiStateElement<MSE, S>,
	S extends Enum<S>
>
extends Element
implements IMultiStateElement<MSE, S> {
	
	//static method
	private static boolean fieldStoresProperty(final Field field) {
		return Property.class.isAssignableFrom(field.getType());
	}
	
	//attribute
	private final State<S> baseState;
	
	//attribute
	private State<S> currentState;
	
	//multi-attribute
	private final IContainer<State<S>> availableStates;
	
	//multi-attribute
	private IContainer<Property<S>> properties;
	
	//constructor
	protected MultiStateElement(final S baseState) {
		
		GlobalValidator.assertThat(baseState).thatIsNamed("base state").isNotNull();
		
		availableStates = new StateExtractor<S>().createtStatesFromState(baseState);
		this.baseState = availableStates.getRefFirst(s -> s.hasEnumValue(baseState));
		internalSwitchToState(baseState);
	}
	
	//method
	@Override
	public final boolean addedOrChangedAttribute(final INode<?> attribute) {
		
		for (final var p : getRefProperties()) {
			if (attribute.getHeader().endsWith(p.getName())) {
				p.setValueFromSpecification(attribute);
				return true;
			}
		}
		
		return false;
	}
	
	//method
	@Override
	public final void addOrChangeAttribute(final String... attributes) {
		
		//Iterates the given attributes.
		for (final var a : attributes) {
			addOrChangeAttribute(Node.fromString(a));
		}
	}
	
	//method
	@Override
	public final void addOrChangeAttribute(final INode<?> attribute) {
		if (!addedOrChangedAttribute(attribute)) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ATTRIBUTE, attribute);
		}
	}
	
	//method
	@Override
	public final IContainer<INode<?>> getAttributes() {
		
		final var attributes = new LinkedList<INode<?>>();
		
		for (final var p : getRefProperties()) {
			p.fillUpValuesSpecificationInto(attributes);
		}
		
		return attributes;
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
	public final void setFrom(final MSE element) {
		
		@SuppressWarnings("unchecked")
		final var iterator = ((MultiStateElement<MSE, S>)element).getRefProperties().iterator();
		
		for (final var p : getRefProperties()) {
			p.setFrom(iterator.next());
		}
	}
	
	//method
	/**
	 * @return the current {@link MultiStateElement} as concrete {@link MultiStateElement}.
	 */
	@SuppressWarnings("unchecked")
	protected final MSE asConcrete() {
		return (MSE)this;
	}
	
	//method
	protected final <FE2 extends MultiStateElement<FE2, S>> void internalAddChild(final FE2 child) {
		
		GlobalValidator.assertThat(child).thatIsNamed(LowerCaseCatalogue.CHILD).isNotNull();
		
		child.setParent(this);
	}
	
	//method
	protected final void initialize() {
		extractPropertiesIfNotExtracted();
	}
	
	//method
	protected final void internalSwitchToState(final S state) {
		
		GlobalValidator.assertThat(state).thatIsNamed(LowerCaseCatalogue.STATE).isNotNull();
		
		currentState = availableStates.getRefFirst(s -> s.hasEnumValue(state));
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
	final IContainer<Property<S>> getRefProperties() {
		
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
		
		throw InvalidArgumentException.forArgument(state);
	}
	
	//method
	final void setParent(final MultiStateElement<?, S> parentElement) {
		
		final var parentCascadingProperties = LinkedList.fromIterable(parentElement.getRefCascadingProperties());
		
		for (final var cp: getRefCascadingProperties()) {
			cp.setParentProperty(parentCascadingProperties.removeAndGetRefFirst(pp -> pp.hasSameNameAs(cp)));
		}
		
		GlobalValidator.assertThat(parentCascadingProperties).thatIsNamed("remaining parent cascading properties").isEmpty();
	}
	
	//method
	private void extractPropertiesIfNotExtracted() {
		if (!propertiesAreExtracted()) {
			extractPropertiesWhenNotExtracted();
		}
	}
	
	//method
	private void extractPropertiesWhenNotExtracted() {
		
		final var lProperties = new LinkedList<Property<S>>();
		fillUpPropertiesIntoList(lProperties);
		
		properties = lProperties;
		
		setItselsAsParentToProperties();
	}
	
	//method
	private void fillUpPotentialPropertyFromFieldIntoList(final Field field, final LinkedList<Property<S>> list) {
		if (fieldStoresProperty(field)) {
			list.addAtEnd(getPropertyFromField(field));
		}
	}
	
	//method
	private void fillUpPropertiesIntoList(final LinkedList<Property<S>> list) {
		Class<?> lClass = getClass();
		while (lClass != null) {
			fillUpPropertiesFromClassIntoList(lClass, list);
			lClass = lClass.getSuperclass();
		}
	}
	
	//method
	private void fillUpPropertiesFromClassIntoList(final Class<?> pClass, final LinkedList<Property<S>> list) {
		for (final var f : pClass.getDeclaredFields()) {
			fillUpPotentialPropertyFromFieldIntoList(f, list);
		}
	}
	
	//method
	private Property<S> getPropertyFromField(final Field field) {
		try {
			
			field.setAccessible(true);
			
			@SuppressWarnings("unchecked")
			final var property = (Property<S>)(field.get(this));
			
			GlobalValidator.assertThat(property).isOfType(Property.class);
			
			return property;
		} catch (final IllegalAccessException illegalAccessException) {
			throw WrapperException.forError(illegalAccessException);
		}
	}
	
	//method
	@SuppressWarnings("unchecked")
	private IContainer<CascadingProperty<S, ?>> getRefCascadingProperties() {
		return getRefProperties().getRefOfType(CascadingProperty.class);
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
