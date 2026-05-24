/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.propertieselement;

import java.lang.reflect.Field;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.errorcontrol.errormapping.IllegalAccessErrorMapper;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.system.element.multistateconfiguration.AbstractProperty;
import ch.nolix.systemapi.element.mutableelement.IMutableElement;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractPropertiesElement extends AbstractElement implements IMutableElement {
  private LinkedList<IProperty> properties;

  /**
   * Adds or changes the given attribute to the current {@link IMutableElement}.
   * 
   * @param attribute
   * @throws RuntimeException if the given attribute is not valid.
   */
  @Override
  public final void addOrChangeAttribute(final INode<?> attribute) {
    //Iterates the properties of the current MutableElement.
    for (final var p : getStoredProperties()) {
      //Handles the case that the current Property has added or changed the given attribute.
      if (p.addedOrChangedAttribute(attribute)) {
        return;
      }
    }

    //Handles the case that the current Mutable cannot have the given attribute.
    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot not have a " + attribute.getHeader());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void addOrChangeAttribute(final String attribute) {
    //Maps the given attribute to a Node.
    final var attributeNode = Node.fromString(attribute);

    //Calls other method.
    addOrChangeAttribute(attributeNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<INode<?>> getAttributes() {
    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();

    //Iterates the properties of the current MutableElement.
    for (final var p : getStoredProperties()) {
      //Fills up the attributes of the current Property.
      p.fillUpAttributesIntoList(attributes);
    }

    return attributes;
  }

  /**
   * Resets the current {@link AbstractPropertiesElement} from the file with the
   * given filePath.
   * 
   * @param filePath
   * @throws RuntimeException if the given filePath is not valid.
   */
  public final void resetFromFileWithFilePath(final String filePath) {
    resetFromSpecification(Node.fromFile(filePath));
  }

  /**
   * Lets the current {@link AbstractPropertiesElement} extract the
   * {@link AbstractProperty} from the given field if the given field stores a
   * {@link AbstractProperty}.
   * 
   * @param field
   */
  private void extractPotentialPropertyFrom(final Field field) {
    //Handles the case that the given field is a Property.
    if (IProperty.class.isAssignableFrom(field.getType())) {
      extractPropertyFrom(field);
    }
  }

  /**
   * Extracts the properties of the current {@link AbstractPropertiesElement}.
   */
  private void extractProperties() {
    properties = LinkedList.createEmpty();

    //Iterates the classes of the current MutableElement.
    Class<?> lClass = getClass();
    while (lClass != null) {
      extractPropertiesFrom(lClass);
      lClass = lClass.getSuperclass();
    }
  }

  /**
   * Extracts the {@link AbstractProperty}s of the
   * {@link AbstractPropertiesElement} that are from the given pClass.
   * 
   * @param pClass
   */
  private void extractPropertiesFrom(final Class<?> pClass) {
    //Iterates the fields of the given pClass.
    for (final var f : pClass.getDeclaredFields()) {
      extractPotentialPropertyFrom(f);
    }
  }

  /**
   * Extracts the {@link AbstractProperty}s of the current
   * {@link AbstractPropertiesElement} if they are not extracted yet.
   */
  private void extractPropertiesIfNotExtracted() {
    if (!hasExtractedProperties()) {
      extractProperties();
    }
  }

  /**
   * Extracts the {@link AbstractProperty} of the
   * {@link AbstractPropertiesElement} that is from the given field.
   * 
   * @param field
   * @throws IllegalAccessError if the given field is not accessible.
   */
  private void extractPropertyFrom(final Field field) {
    try {
      field.setAccessible(true);

      final var property = (IProperty) (field.get(this));

      //Asserts that the corresponding Property is not null.
      Validator.assertThat(property).isOfType(IProperty.class);

      properties.addAtEnd(property);
    } catch (final IllegalAccessException illegalAccessException) {
      throw IllegalAccessErrorMapper.mapIllegalAccessExceptionToIllegalAccessError(illegalAccessException);
    }
  }

  /**
   * @return the {@link AbstractProperty}s of the current
   *         {@link AbstractPropertiesElement}.
   */
  private IContainer<IProperty> getStoredProperties() {
    extractPropertiesIfNotExtracted();

    return properties;
  }

  /**
   * @return true if the {@link AbstractProperty}s of the current
   *         {@link AbstractPropertiesElement} are extracted, false otherwise.
   */
  private boolean hasExtractedProperties() {
    return (properties != null);
  }
}
