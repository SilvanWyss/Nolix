package ch.nolix.system.element.multistateconfiguration;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.element.base.Element;
import ch.nolix.systemapi.elementapi.multistateconfigurationapi.IMultiStateConfiguration;

public abstract class MultiStateConfiguration<C extends IMultiStateConfiguration<C, S>, S extends Enum<S>>
extends Element
implements IMultiStateConfiguration<C, S> {

  private final State<S> baseState;

  private final IContainer<State<S>> availableStates;

  private IContainer<AbstractProperty<S>> abstractProperties;

  protected MultiStateConfiguration(final S baseState) {

    GlobalValidator.assertThat(baseState).thatIsNamed("base state").isNotNull();

    availableStates = new StateExtractor<S>().createtStatesFromState(baseState);
    this.baseState = availableStates.getStoredFirst(s -> s.hasEnumValue(baseState));
  }

  private static boolean fieldStoresProperty(final Field field) {
    return AbstractProperty.class.isAssignableFrom(field.getType());
  }

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

  @Override
  public final void addOrChangeAttribute(final String attribtue, final String... attributes) {

    //Calls other method.
    addOrChangeAttribute(Node.fromString(attribtue));

    //Iterates the given attributes.
    for (final var a : attributes) {

      //Calls other method.
      addOrChangeAttribute(Node.fromString(a));
    }
  }

  @Override
  public final void addOrChangeAttribute(final INode<?> attribute) {
    if (!addedOrChangedAttribute(attribute)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.ATTRIBUTE, attribute);
    }
  }

  @Override
  public final IContainer<INode<?>> getAttributes() {

    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();

    for (final var p : getStoredProperties()) {
      p.fillUpValuesSpecificationInto(attributes);
    }

    return attributes;
  }

  @Override
  public final S getBaseState() {
    return baseState.getEnumValue();
  }

  @Override
  public final void reset() {
    getStoredProperties().forEach(AbstractProperty::setUndefined);
  }

  public final void setFrom(final C element) {

    @SuppressWarnings("unchecked")
    final var iterator = ((MultiStateConfiguration<C, S>) element).getStoredProperties().iterator();

    for (final var p : getStoredProperties()) {
      p.setFrom(iterator.next());
    }
  }

  /**
   * @return the current {@link MultiStateConfiguration} as concrete
   *         {@link MultiStateConfiguration}.
   */
  @SuppressWarnings("unchecked")
  protected final C asConcrete() {
    return (C) this;
  }

  @SuppressWarnings("unchecked")
  protected final void internalAddChild(final C child) {

    GlobalValidator.assertThat(child).thatIsNamed(LowerCaseVariableCatalogue.CHILD).isNotNull();

    ((MultiStateConfiguration<?, S>) child).setParent(this);
  }

  protected final void initialize() {
    extractPropertiesIfNotExtracted();
  }

  final IContainer<State<S>> getAvailableStates() {
    return availableStates;
  }

  final State<S> getBaseStateObject() {
    return baseState;
  }

  final IContainer<AbstractProperty<S>> getStoredProperties() {

    extractPropertiesIfNotExtracted();

    return abstractProperties;
  }

  final State<S> getStateObjectFor(final S state) {

    for (final var s : availableStates) {
      if (s.hasEnumValue(state)) {
        return s;
      }
    }

    throw InvalidArgumentException.forArgument(state);
  }

  private void extractPropertiesIfNotExtracted() {
    if (!propertiesAreExtracted()) {
      extractPropertiesWhenNotExtracted();
    }
  }

  private void extractPropertiesWhenNotExtracted() {

    final ILinkedList<AbstractProperty<S>> lProperties = LinkedList.createEmpty();
    fillUpPropertiesIntoList(lProperties);

    abstractProperties = lProperties;

    setItselsAsParentToProperties();
  }

  private void fillUpPotentialPropertyFromFieldIntoList(
    final Field field,
    final ILinkedList<AbstractProperty<S>> list) {
    if (fieldStoresProperty(field)) {
      list.addAtEnd(getPropertyFromField(field));
    }
  }

  private void fillUpPropertiesIntoList(final ILinkedList<AbstractProperty<S>> list) {
    Class<?> lClass = getClass();
    while (lClass != null) {
      fillUpPropertiesFromClassIntoList(lClass, list);
      lClass = lClass.getSuperclass();
    }
  }

  private void fillUpPropertiesFromClassIntoList(final Class<?> pClass, final ILinkedList<AbstractProperty<S>> list) {
    for (final var f : pClass.getDeclaredFields()) {
      fillUpPotentialPropertyFromFieldIntoList(f, list);
    }
  }

  private AbstractProperty<S> getPropertyFromField(final Field field) {
    try {

      field.setAccessible(true);

      @SuppressWarnings("unchecked")
      final var property = (AbstractProperty<S>) (field.get(this));

      GlobalValidator.assertThat(property).isOfType(AbstractProperty.class);

      return property;
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  @SuppressWarnings("unchecked")
  private IContainer<CascadingProperty<S, ?>> getStoredCascadingProperties() {
    return getStoredProperties().getStoredOfType(CascadingProperty.class);
  }

  private boolean propertiesAreExtracted() {
    return (abstractProperties != null);
  }

  private void setItselsAsParentToProperties() {
    for (final var p : getStoredProperties()) {
      p.setParent(this);
    }
  }

  private void setParent(final MultiStateConfiguration<?, S> parentElement) {

    final var parentCascadingProperties = LinkedList.fromIterable(parentElement.getStoredCascadingProperties());

    for (final var cp : getStoredCascadingProperties()) {
      cp.setParentProperty(parentCascadingProperties.removeAndGetStoredFirst(pp -> pp.hasSameNameAs(cp)));
    }

    GlobalValidator.assertThat(parentCascadingProperties).thatIsNamed("remaining parent cascading properties")
      .isEmpty();
  }
}
