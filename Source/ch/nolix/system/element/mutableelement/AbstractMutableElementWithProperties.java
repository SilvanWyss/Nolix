/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.mutableelement;

import java.lang.reflect.Field;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.errorcontrol.errormapping.IllegalAccessErrorMapper;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.systemapi.element.base.SpecificationRepresentable;
import ch.nolix.systemapi.element.baseproperty.Property;
import ch.nolix.systemapi.element.mutableelement.MutableElement;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractMutableElementWithProperties implements MutableElement {
  private ArrayList<Property> properties;

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addOrChangeAttribute(final Node<?> attribute) {
    // Iterates the properties of the current AbstractPropertiesElement.
    for (final var p : getStoredProperties()) {
      // Handles the case that the current property has added or changed the given attribute.
      if (p.addedOrChangedAttribute(attribute)) {
        return;
      }
    }

    // Handles the case that the current AbstractPropertiesElement cannot have the given attribute.
    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot have a " + attribute.getHeader());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addOrChangeAttribute(final String attribute) {
    // Maps the given attribute to a Node.
    final var attributeNode = ImmutableNode.fromString(attribute);

    // Calls other method.
    addOrChangeAttribute(attributeNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object instanceof SpecificationRepresentable element && hasEqualSpecificationAsElement(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Node<?>> getAttributes() {
    // Creates attributes list.
    final LinkedList<Node<?>> attributes = LinkedList.createEmpty();

    // Iterates the properties of the current AbstractPropertiesElement.
    for (final var p : getStoredProperties()) {
      // Fills up the attributes of the current property into the attributes list.
      p.fillUpAttributesIntoList(attributes);
    }

    // Returns the attributes list.
    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Node<?> getSpecification() {
    return ImmutableNode.withHeaderAndChildNodes(getSpecificationHeader(), getAttributes());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return getSpecification().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    return getSpecification().toString();
  }

  /**
   * @return the header of the specification of the current
   *         {@link AbstractMutableElementWithProperties}
   */
  private String getSpecificationHeader() {
    final var localClass = getClass();

    if (!localClass.isAnonymousClass()) {
      return localClass.getSimpleName();
    }

    return PascalCaseVariableNameCatalog.ELEMENT;
  }

  /**
   * Adds the {@link Property}s from the current
   * {@link AbstractMutableElementWithProperties} to the current
   * {@link AbstractMutableElementWithProperties} if the current
   * {@link AbstractMutableElementWithProperties} has not added its
   * {@link Property}s.
   */
  private void addPropertiesIfNotAdded() {
    if (!hasAddedProperties()) {
      addProperties();
    }
  }

  /**
   * @return true if the current {@link AbstractMutableElementWithProperties}s has
   *         added its {@link Property}s, false otherwise
   */
  private boolean hasAddedProperties() {
    return properties != null;
  }

  /**
   * Adds the {@link Property}s from the current
   * {@link AbstractMutableElementWithProperties} to the current
   * {@link AbstractMutableElementWithProperties}.
   */
  private void addProperties() {
    properties = ArrayList.createEmpty();
    Class<?> lClass = getClass();

    while (lClass != null) {
      addPropertiesFromClass(lClass);
      lClass = lClass.getSuperclass();
    }
  }

  /**
   * Adds the {@link Property}s from the given paramClass to the current
   * {@link AbstractMutableElementWithProperties}.
   * 
   * @param paramClass
   * @throws RuntimeException if the given paramClass is null
   */
  private void addPropertiesFromClass(final Class<?> paramClass) {
    for (final var f : paramClass.getDeclaredFields()) {
      addPotentialPropertyFromField(f);
    }
  }

  /**
   * Adds the {@link Property} from the given field to the current
   * {@link AbstractMutableElementWithProperties} if the given field contains a
   * {@link Property}.
   * 
   * @param field
   * @throws RuntimeException if the given field is null
   */
  private void addPotentialPropertyFromField(final Field field) {
    if (Property.class.isAssignableFrom(field.getType())) {
      addPropertyFromField(field);
    }
  }

  /**
   * Adds the {@link Property} from the given field to the current
   * {@link AbstractMutableElementWithProperties}.
   * 
   * @param field
   * @throws RuntimeException if the given field is null, not accessible or does
   *                          not have a value.
   */
  private void addPropertyFromField(final Field field) {
    try {
      field.setAccessible(true);

      final var property = (Property) (field.get(this));

      Validator.assertThat(property).thatIsNamed(LowerCaseVariableNameCatalog.PROPERTY).isNotNull();
      properties.addAtEnd(property);
    } catch (final IllegalAccessException illegalAccessException) {
      throw IllegalAccessErrorMapper.mapIllegalAccessExceptionToIllegalAccessError(illegalAccessException);
    }
  }

  /**
   * @return the {@link Property}s of the current
   *         {@link AbstractMutableElementWithProperties}.
   */
  private ExtendedIterable<Property> getStoredProperties() {
    addPropertiesIfNotAdded();

    return properties;
  }
}
