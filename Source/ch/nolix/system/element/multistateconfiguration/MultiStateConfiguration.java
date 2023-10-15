//package declaration
package ch.nolix.system.element.multistateconfiguration;

//Java imports
import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;

//class
public abstract class MultiStateConfiguration<MSC extends IMultiStateConfiguration<MSC, S>, S extends Enum<S>>
    extends Element
    implements IMultiStateConfiguration<MSC, S> {

  // static method
  private static boolean fieldStoresProperty(final Field field) {
    return Property.class.isAssignableFrom(field.getType());
  }

  // attribute
  private final State<S> baseState;

  // multi-attribute
  private final IContainer<State<S>> availableStates;

  // multi-attribute
  private IContainer<Property<S>> properties;

  // constructor
  protected MultiStateConfiguration(final S baseState) {

    GlobalValidator.assertThat(baseState).thatIsNamed("base state").isNotNull();

    availableStates = new StateExtractor<S>().createtStatesFromState(baseState);
    this.baseState = availableStates.getStoredFirst(s -> s.hasEnumValue(baseState));
  }

  // method
  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {

    for (final var p : getStoredProperties()) {
      if (attribute.getHeader().endsWith(p.getName())) {
        p.setValueFromSpecification(attribute);
        return true;
      }
    }

    return false;
  }

  // method
  @Override
  public final void addOrChangeAttribute(final String attribtue, final String... attributes) {

    // Calls other method.
    addOrChangeAttribute(Node.fromString(attribtue));

    // Iterates the given attributes.
    for (final var a : attributes) {

      // Calls other method.
      addOrChangeAttribute(Node.fromString(a));
    }
  }

  // method
  @Override
  public final void addOrChangeAttribute(final INode<?> attribute) {
    if (!addedOrChangedAttribute(attribute)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ATTRIBUTE, attribute);
    }
  }

  // method
  @Override
  public final IContainer<INode<?>> getAttributes() {

    final var attributes = new LinkedList<INode<?>>();

    for (final var p : getStoredProperties()) {
      p.fillUpValuesSpecificationInto(attributes);
    }

    return attributes;
  }

  // method
  @Override
  public final S getBaseState() {
    return baseState.getEnumValue();
  }

  // method
  @Override
  public final void reset() {
    getStoredProperties().forEach(Property::setUndefined);
  }

  // method
  public final void setFrom(final MSC element) {

    @SuppressWarnings("unchecked")
    final var iterator = ((MultiStateConfiguration<MSC, S>) element).getStoredProperties().iterator();

    for (final var p : getStoredProperties()) {
      p.setFrom(iterator.next());
    }
  }

  // method
  /**
   * @return the current {@link MultiStateConfiguration} as concrete
   *         {@link MultiStateConfiguration}.
   */
  @SuppressWarnings("unchecked")
  protected final MSC asConcrete() {
    return (MSC) this;
  }

  // method
  @SuppressWarnings("unchecked")
  protected final void internalAddChild(final MSC child) {

    GlobalValidator.assertThat(child).thatIsNamed(LowerCaseCatalogue.CHILD).isNotNull();

    ((MultiStateConfiguration<?, S>) child).setParent(this);
  }

  // method
  protected final void initialize() {
    extractPropertiesIfNotExtracted();
  }

  // method
  final IContainer<State<S>> getAvailableStates() {
    return availableStates;
  }

  // method
  final State<S> getBaseStateObject() {
    return baseState;
  }

  // method
  final IContainer<Property<S>> getStoredProperties() {

    extractPropertiesIfNotExtracted();

    return properties;
  }

  // method
  final State<S> getStateObjectFor(final S state) {

    for (final var s : availableStates) {
      if (s.hasEnumValue(state)) {
        return s;
      }
    }

    throw InvalidArgumentException.forArgument(state);
  }

  // method
  private void extractPropertiesIfNotExtracted() {
    if (!propertiesAreExtracted()) {
      extractPropertiesWhenNotExtracted();
    }
  }

  // method
  private void extractPropertiesWhenNotExtracted() {

    final var lProperties = new LinkedList<Property<S>>();
    fillUpPropertiesIntoList(lProperties);

    properties = lProperties;

    setItselsAsParentToProperties();
  }

  // method
  private void fillUpPotentialPropertyFromFieldIntoList(final Field field, final LinkedList<Property<S>> list) {
    if (fieldStoresProperty(field)) {
      list.addAtEnd(getPropertyFromField(field));
    }
  }

  // method
  private void fillUpPropertiesIntoList(final LinkedList<Property<S>> list) {
    Class<?> lClass = getClass();
    while (lClass != null) {
      fillUpPropertiesFromClassIntoList(lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  // method
  private void fillUpPropertiesFromClassIntoList(final Class<?> pClass, final LinkedList<Property<S>> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialPropertyFromFieldIntoList(f, list);
    }
  }

  // method
  private Property<S> getPropertyFromField(final Field field) {
    try {

      field.setAccessible(true);

      @SuppressWarnings("unchecked")
      final var property = (Property<S>) (field.get(this));

      GlobalValidator.assertThat(property).isOfType(Property.class);

      return property;
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  // method
  @SuppressWarnings("unchecked")
  private IContainer<CascadingProperty<S, ?>> getStoredCascadingProperties() {
    return getStoredProperties().getStoredOfType(CascadingProperty.class);
  }

  // method
  private boolean propertiesAreExtracted() {
    return (properties != null);
  }

  // method
  private void setItselsAsParentToProperties() {
    for (final var p : getStoredProperties()) {
      p.setParent(this);
    }
  }

  // method
  private void setParent(final MultiStateConfiguration<?, S> parentElement) {

    final var parentCascadingProperties = LinkedList.fromIterable(parentElement.getStoredCascadingProperties());

    for (final var cp : getStoredCascadingProperties()) {
      cp.setParentProperty(parentCascadingProperties.removeAndGetRefFirst(pp -> pp.hasSameNameAs(cp)));
    }

    GlobalValidator.assertThat(parentCascadingProperties).thatIsNamed("remaining parent cascading properties")
        .isEmpty();
  }
}
