/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import java.lang.reflect.Field;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.element.multistateconfiguration.MultiStateConfiguration;

/**
 * @author Silvan Wyss
 * @param <C> the type of a {@link AbstractMultiStateConfiguration}.
 * @param <S> the type of the {@link Enum} representation of the {@link State}s
 *            of a {@link AbstractMultiStateConfiguration}.
 */
public abstract class AbstractMultiStateConfiguration<C extends MultiStateConfiguration<C, S>, S extends Enum<S>>
extends AbstractElement
implements MultiStateConfiguration<C, S> {
  private final State<S> baseState;

  private final ExtendedIterable<State<S>> availableStates;

  private ExtendedIterable<AbstractProperty<S>> abstractProperties;

  protected AbstractMultiStateConfiguration(final S baseState) {
    Validator.assertThat(baseState).thatIsNamed("base state").isNotNull();

    availableStates = new StateExtractor<S>().createtStatesFromState(baseState);
    this.baseState = availableStates.getStoredFirst(s -> s.hasEnumValue(baseState));
  }

  private static boolean fieldStoresProperty(final Field field) {
    return AbstractProperty.class.isAssignableFrom(field.getType());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean addedOrChangedAttribute(final Node<?> attribute) {
    for (final var p : getStoredProperties()) {
      if (attribute.getHeader().endsWith(p.getName())) {
        p.setValueFromSpecification(attribute);
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addOrChangeAttribute(final Node<?> attribute) {
    if (!addedOrChangedAttribute(attribute)) {
      throw InvalidArgumentException.forArgumentAndArgumentName(attribute, LowerCaseVariableNameCatalog.ATTRIBUTE);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addOrChangeAttribute(final String attribute) {
    final var attributeNode = ImmutableNode.fromString(attribute);

    addOrChangeAttribute(attributeNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Node<?>> getAttributes() {
    final ILinkedList<Node<?>> attributes = LinkedList.createEmpty();

    for (final var p : getStoredProperties()) {
      p.fillUpValuesSpecificationInto(attributes);
    }

    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final S getBaseState() {
    return baseState.getEnumValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void reset() {
    getStoredProperties().forEach(AbstractProperty::setUndefined);
  }

  public final void setFrom(final C element) {
    @SuppressWarnings("unchecked")
    final var iterator = ((AbstractMultiStateConfiguration<C, S>) element).getStoredProperties().iterator();

    for (final var p : getStoredProperties()) {
      p.setFrom(iterator.next());
    }
  }

  /**
   * @return the current {@link AbstractMultiStateConfiguration} as concrete
   *         {@link AbstractMultiStateConfiguration}.
   */
  @SuppressWarnings("unchecked")
  protected final C asConcrete() {
    return (C) this;
  }

  @Override
  public final void addChild(final MultiStateConfiguration<?, S> child) {
    Validator.assertThat(child).thatIsNamed(LowerCaseVariableNameCatalog.CHILD).isNotNull();

    ((AbstractMultiStateConfiguration<?, S>) child).setParent(this);
  }

  @Override
  public <T extends MultiStateConfiguration<T, S>> void removeChild(final T multiStateConfiguration) {
    @SuppressWarnings("unchecked")
    final var abstractMultiStateConfiguration = (AbstractMultiStateConfiguration<C, S>) multiStateConfiguration;

    abstractMultiStateConfiguration.removeParent();
  }

  protected final void initialize() {
    extractPropertiesIfNotExtracted();
  }

  final ExtendedIterable<State<S>> getAvailableStates() {
    return availableStates;
  }

  final State<S> getBaseStateObject() {
    return baseState;
  }

  final ExtendedIterable<AbstractProperty<S>> getStoredProperties() {
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

      Validator.assertThat(property).isOfType(AbstractProperty.class);

      return property;
    } catch (final IllegalAccessException illegalAccessException) {
      throw WrapperException.forError(illegalAccessException);
    }
  }

  @SuppressWarnings("unchecked")
  private ExtendedIterable<CascadingProperty<S, ?>> getStoredCascadingProperties() {
    return getStoredProperties().getStoredOfType(CascadingProperty.class);
  }

  private boolean propertiesAreExtracted() {
    return (abstractProperties != null);
  }

  private void removeParent() {
    getStoredCascadingProperties().forEach(CascadingProperty::removeParentProperty);
  }

  private void setItselsAsParentToProperties() {
    for (final var p : getStoredProperties()) {
      p.setParent(this);
    }
  }

  private void setParent(final AbstractMultiStateConfiguration<?, S> parentElement) {
    final var parentCascadingProperties = LinkedList.fromIterable(parentElement.getStoredCascadingProperties());

    for (final var p : getStoredCascadingProperties()) {
      p.setParentProperty(parentCascadingProperties.removeAndGetStoredFirst(pp -> pp.hasSameNameAs(p)));
    }

    Validator.assertThat(parentCascadingProperties).thatIsNamed("remaining parent cascading properties").isEmpty();
  }
}
