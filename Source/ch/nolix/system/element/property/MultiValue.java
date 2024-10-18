package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

/**
 * @author Silvan Wyss
 * @version 2018-03-04
 * @param <V> is the type of the values of a {@link MultiValue}.
 */
public final class MultiValue<V> extends BaseValue<V> implements Clearable {

  private final Consumer<V> adderMethod;

  private final LinkedList<V> values = LinkedList.createEmpty();

  /**
   * Creates a new {@link MultiValue} with the given name, valueCreator,
   * adderMethod and specificationCreator.
   * 
   * @param name
   * @param valueCreator
   * @param adderMethod
   * @param specificationCreator
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given adderMethod is null.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   * @throws ArgumentIsNullException  if the given specificationCreator is null.
   */
  public MultiValue(
    final String name,
    final Consumer<V> adderMethod,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    //Calls constructor of the base class
    super(name, valueCreator, specificationCreator);

    //Asserts that the given adderMethod is not null.
    GlobalValidator.assertThat(adderMethod).thatIsNamed("adder method").isNotNull();

    //Sets the adderMethod of the current MultiProperty.
    this.adderMethod = adderMethod;
  }

  /**
   * @param name
   * @param adderMethod
   * @return a new {@link MultiValue} that will store {@link Integer}s and have
   *         the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given adderMethod is null.
   */
  public static MultiValue<Integer> forInts(final String name, final Consumer<Integer> adderMethod) {
    return new MultiValue<>(name, adderMethod, INode::toInt, Node::withHeader);
  }

  /**
   * @param name
   * @param adderMethod
   * @return a new {@link MultiValue} that will store {@link String}s and have the
   *         given name and adderMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given adderMethod is null.
   */
  public static MultiValue<String> forStrings(final String name, final Consumer<String> adderMethod) {
    return new MultiValue<>(name, adderMethod, INode::getHeader, Node::withHeader);
  }

  /**
   * Adds the given value to the current {@link MultiValue}.
   * 
   * @param value
   * @throws ArgumentIsNullException if the given value is null.
   */
  public void add(final V value) {
    values.addAtEnd(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    values.clear();
  }

  /**
   * @return the values of the current {@link MultiValue}.
   */
  public IContainer<V> getStoredValues() {
    return values;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return values.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return true;
  }

  /**
   * Removes the given value of the current {@link MultiValue}.
   * 
   * @param value
   * @throws ArgumentDoesNotContainElementException if the current
   *                                                {@link MultiValue} does not
   *                                                contain the given value.
   */
  public void remove(final V value) {
    values.removeStrictlyFirstOccurrenceOf(value);
  }

  /**
   * Removes and returns the last value of the current {@link MultiValue}.
   * 
   * @return the last element of the current {@link MultiValue}.
   * @throws EmptyArgumentException if the current {@link MultiValue} is empty.
   */
  public V removeAndGetRefLast() {
    return values.removeAndGetStoredLast();
  }

  /**
   * Removes the last value of the current {@link MultiValue}.
   * 
   * @return the current {@link MultiValue}.
   * @throws EmptyArgumentException if the current {@link MultiValue} is empty.
   */
  public MultiValue<V> removeLast() {

    values.removeLast();

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addOrChangeValue(final V value) {
    adderMethod.accept(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {

    //Iterates the values of the current MultiProperty.
    for (final var v : getStoredValues()) {

      //Creates a specification from the current value.
      final var specification = Node.withHeaderAndChildNodes(getName(),
        specificationCreator.apply(v).getStoredChildNodes());

      //Adds the specification to the given list.
      list.addAtEnd(specification);
    }
  }
}
