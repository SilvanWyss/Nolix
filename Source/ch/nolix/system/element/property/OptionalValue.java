package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 * @version 2018-03-01
 * @param <V> is the type of the value of a {@link OptionalValue}.
 */
public final class OptionalValue<V> extends AbstractSingleValue<V> {

  /**
   * Creates a new {@link OptionalValue} with the given name, setterMethod,
   * valueCreator and specificationCreator.
   * 
   * @param name
   * @param setterMethod
   * @param valueCreator
   * @param specificationCreator
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   * @throws ArgumentIsNullException  if the given specificationCreator is null.
   */
  public OptionalValue(
    final String name,
    final Consumer<V> setterMethod,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    //Calls constructor of the base class.
    super(name, setterMethod, valueCreator, specificationCreator);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link OptionalValue} that will store a {@link Boolean} and
   *         have the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static OptionalValue<Boolean> forBoolean(final String name, final Consumer<Boolean> setterMethod) {
    return new OptionalValue<>(name, setterMethod, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link OptionalValue} that will store a {@link Double} and have
   *         the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static OptionalValue<Double> forDouble(final String name, final Consumer<Double> setterMethod) {
    return new OptionalValue<>(name, setterMethod, INode::getSingleChildNodeAsDouble, Node::withChildNode);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link OptionalValue} that will store a {@link Integer} and
   *         have the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static OptionalValue<Integer> forInt(final String name, final Consumer<Integer> setterMethod) {
    return new OptionalValue<>(name, setterMethod, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link OptionalValue} that will store a {@link String} and have
   *         the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static OptionalValue<String> forString(final String name, final Consumer<String> setterMethod) {
    return new OptionalValue<>(
      name,
      setterMethod,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      (final String s) -> {

        if (s.isEmpty()) {
          return Node.EMPTY_NODE;
        }

        return Node.withChildNode(s);
      });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return false;
  }
}
