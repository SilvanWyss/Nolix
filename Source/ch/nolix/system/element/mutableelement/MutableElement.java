package ch.nolix.system.element.mutableelement;

import java.lang.reflect.Field;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.base.SpecificationCreator;
import ch.nolix.system.element.multistateconfiguration.AbstractProperty;
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

/**
 * @author Silvan Wyss
 * @version 2017-10-29
 */
public abstract class MutableElement implements IMutableElement {

  private static final SpecificationCreator SPECIFICATION_CREATOR = new SpecificationCreator();

  private LinkedList<IProperty> properties;

  /**
   * Adds or changes the given attribute to the current {@link IMutableElement}.
   * 
   * @param attribute
   * @throws InvalidArgumentException if the given attribute is not valid.
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
  public final void addOrChangeAttribute(final String attribte, final String... attributes) {

    //Calls other method.
    addOrChangeAttribute(Node.fromString(attribte));

    //Iterates the given attributes.
    for (final var a : attributes) {

      //Calls other method.
      addOrChangeAttribute(Node.fromString(a));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((IElement) object);
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
      p.fillUpAttributesInto(attributes);
    }

    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return SPECIFICATION_CREATOR.getSpecificationOfElement(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return getSpecification().hashCode();
  }

  /**
   * Resets the current {@link MutableElement} from the file with the given
   * filePath.
   * 
   * @param filePath
   * @throws InvalidArgumentException if the given filePath is not valid.
   */
  public final void resetFromFileWithFilePath(final String filePath) {
    resetFromSpecification(Node.fromFile(filePath));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    return getSpecification().toString();
  }

  /**
   * @param illegalAccessException
   * @return a new {@link IllegalAccessError} for the given
   *         illegalAccessException.
   */
  private IllegalAccessError createIllegalAccessErrorFor(final IllegalAccessException illegalAccessException) {

    final var message = illegalAccessException.getMessage();

    if (message == null || message.isBlank()) {
      throw new IllegalAccessError();
    }

    throw new IllegalAccessError(message);
  }

  /**
   * Lets the current {@link MutableElement} extract the {@link AbstractProperty}
   * from the given field if the given field stores a {@link AbstractProperty}.
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
   * Extracts the properties of the current {@link MutableElement}.
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
   * Extracts the {@link AbstractProperty}s of the {@link MutableElement} that are
   * from the given pClass.
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
   * Extracts the {@link AbstractProperty}s of the current {@link MutableElement}
   * if they are not extracted yet.
   */
  private void extractPropertiesIfNotExtracted() {
    if (!hasExtractedProperties()) {
      extractProperties();
    }
  }

  /**
   * Extracts the {@link AbstractProperty} of the {@link MutableElement} that is
   * from the given field.
   * 
   * @param field
   * @throws IllegalAccessError if the given field is not accessible.
   */
  private void extractPropertyFrom(final Field field) {
    try {

      field.setAccessible(true);

      final var property = (IProperty) (field.get(this));

      //Asserts that the corresponding Property is not null.
      GlobalValidator.assertThat(property).isOfType(IProperty.class);

      properties.addAtEnd(property);
    } catch (final IllegalAccessException illegalAccessException) {
      throw createIllegalAccessErrorFor(illegalAccessException);
    }
  }

  /**
   * @return the {@link AbstractProperty}s of the current {@link MutableElement}.
   */
  private IContainer<IProperty> getStoredProperties() {

    extractPropertiesIfNotExtracted();

    return properties;
  }

  /**
   * @return true if the {@link AbstractProperty}s of the current
   *         {@link MutableElement} are extracted.
   */
  private boolean hasExtractedProperties() {
    return (properties != null);
  }

  /**
   * @param element
   * @return true if the current {@link MutableElement} has the same specification
   *         as the given element.
   */
  private boolean hasSameSpecificationAs(final IElement element) {
    return getSpecification().equals(element.getSpecification());
  }
}
