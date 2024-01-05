//package declaration
package ch.nolix.system.element.mutableelement;

//Java imports
import java.lang.reflect.Field;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.base.SpecificationCreator;
import ch.nolix.systemapi.elementapi.baseapi.IElement;
import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;
import ch.nolix.systemapi.elementapi.propertyapi.IProperty;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-29
 */
public abstract class MutableElement implements IMutableElement {

  //constant
  private static final SpecificationCreator SPECIFICATION_CREATOR = new SpecificationCreator();

  //multi-attribute
  private LinkedList<IProperty> properties;

  //method
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

      //Handles the case that the current Property has added or changed the given
      //attribute.
      if (p.addedOrChangedAttribute(attribute)) {
        return;
      }
    }

    //Handles the case that the current Mutable cannot have the given attribute.
    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot not have a " + attribute.getHeader());
  }

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean equals(final Object object) {
    return object != null
    && getClass() == object.getClass()
    && hasSameSpecificationAs((IElement) object);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<INode<?>> getAttributes() {

    final var attributes = new LinkedList<INode<?>>();

    //Iterates the properties of the current MutableElement.
    for (final var p : getStoredProperties()) {

      //Fills up the attributes of the current Property.
      p.fillUpAttributesInto(attributes);
    }

    return attributes;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> getSpecification() {
    return SPECIFICATION_CREATOR.getSpecificationOfElement(this);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final int hashCode() {
    return getSpecification().hashCode();
  }

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    return getSpecification().toString();
  }

  //method
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

  //method
  /**
   * Lets the current {@link MutableElement} extract the {@link Property} from the
   * given field if the given field stores a {@link Property}.
   * 
   * @param field
   */
  private void extractPotentialPropertyFrom(final Field field) {

    //Handles the case that the given field is a Property.
    if (IProperty.class.isAssignableFrom(field.getType())) {
      extractPropertyFrom(field);
    }
  }

  //method
  /**
   * Extracts the properties of the current {@link MutableElement}.
   */
  private void extractProperties() {

    properties = new LinkedList<>();

    //Iterates the classes of the current MutableElement.
    Class<?> lClass = getClass();
    while (lClass != null) {
      extractPropertiesFrom(lClass);
      lClass = lClass.getSuperclass();
    }
  }

  //method
  /**
   * Extracts the {@link Property}s of the {@link MutableElement} that are from
   * the given pClass.
   * 
   * @param pClass
   */
  private void extractPropertiesFrom(final Class<?> pClass) {

    //Iterates the fields of the given pClass.
    for (final var f : pClass.getDeclaredFields()) {
      extractPotentialPropertyFrom(f);
    }
  }

  //method
  /**
   * Extracts the {@link Property}s of the current {@link MutableElement} if they
   * are not extracted yet.
   */
  private void extractPropertiesIfNotExtracted() {
    if (!hasExtractedProperties()) {
      extractProperties();
    }
  }

  //method
  /**
   * Extracts the {@link Property} of the {@link MutableElement} that is from the
   * given field.
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

  //method
  /**
   * @return the {@link Property}s of the current {@link MutableElement}.
   */
  private IContainer<IProperty> getStoredProperties() {

    extractPropertiesIfNotExtracted();

    return properties;
  }

  //method
  /**
   * @return true if the {@link Property}s of the current {@link MutableElement}
   *         are extracted.
   */
  private boolean hasExtractedProperties() {
    return (properties != null);
  }

  //method
  /**
   * @param element
   * @return true if the current {@link MutableElement} has the same specification
   *         as the given element.
   */
  private boolean hasSameSpecificationAs(final IElement element) {
    return getSpecification().equals(element.getSpecification());
  }
}
