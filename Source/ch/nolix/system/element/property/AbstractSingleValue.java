package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2018-03-04
 * @param <V> is the type of the value of a {@link AbstractSingleValue}.
 */
abstract class AbstractSingleValue<V> extends AbstractValue<V> {

  private final Consumer<V> setterMethod;

  private V value;

  /**
   * Creates a new {@link AbstractSingleValue} with the given name, setterMethod,
   * valueCreator and specificationCreator.
   * 
   * @param name
   * @param setterMethod
   * @param valueCreator
   * @param specificationCreator
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   * @throws ArgumentIsNullException  if the given specificationCreator is null.
   */
  protected AbstractSingleValue(
    final String name,
    final Consumer<V> setterMethod,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    //Calls constructor of the base class.
    super(name, valueCreator, specificationCreator);

    //Asserts that the given setterMethod is not null.
    Validator.assertThat(setterMethod).thatIsNamed("setter method").isNotNull();

    //Sets the setterMethod of the current SingleProperty.
    this.setterMethod = setterMethod;
  }

  /**
   * @return a new specification of the current {@link AbstractSingleValue}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractSingleValue} does not
   *                                               have a value
   */
  public final Node getSpecification() {
    return Node.withHeaderAndChildNodes(getName(), specificationCreator.apply(getValue()).getStoredChildNodes());
  }

  /**
   * @return the value of the current {@link AbstractSingleValue}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbstractSingleValue} does not
   *                                               have a value.
   */
  public final V getValue() {

    //Asserts that the current SingleProperty has a value.
    if (value == null) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndArgumentNameAndAttributeName(
        this,
        getName(),
        LowerCaseVariableCatalog.VALUE);
    }

    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return (value == null);
  }

  /**
   * Sets the value of the current {@link AbstractSingleValue}.
   * 
   * @param value
   * @throws ArgumentIsNullException  if the given value is null.
   * @throws InvalidArgumentException if the current {@link AbstractSingleValue} is not
   *                                  mutable and has already a value.
   */
  public final void setValue(final V value) {

    //Asserts that the given value is not null.
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    //Asserts that the current SingleProperty is mutable or does not have already a
    //value.
    if (!isMutable() && containsAny()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "is not mutable and has already a value");
    }

    //Sets the value of the current SingleProperty.
    this.value = value;
  }

  @Override
  protected final void addOrChangeValue(final V value) {
    setterMethod.accept(value);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public final void fillUpAttributesInto(final ILinkedList<INode<?>> list) {

    //Handles the case that the current SingleValue has a value.
    if (containsAny()) {

      //Adds the specification of the current SingleValue to the given list.
      list.addAtEnd(getSpecification());
    }
  }

  /**
   * Removes the value of the current {@link AbstractSingleValue}.
   */
  protected final void internalClear() {
    value = null;
  }
}
