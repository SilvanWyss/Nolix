package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.baseapi.IElement;

/**
 * @author Silvan Wyss
 * @version 2017-11-01
 * @param <V> is the type of the value of a {@link MutableOptionalValue}.
 */
public final class MutableOptionalValue<V> extends SingleValue<V> {

  /**
   * Creates a new {@link MutableOptionalValue} with the given name, setterMethod,
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
  public MutableOptionalValue(
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
   * @return a new {@link MutableOptionalValue} that will store a {@link Boolean}
   *         and have the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static MutableOptionalValue<Boolean> forBoolean(final String name, final Consumer<Boolean> setterMethod) {
    return new MutableOptionalValue<>(name, setterMethod, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  /**
   * @param name
   * @param setterMethod
   * @param valueCreator
   * @param <E>          is the type of the value of the created
   *                     {@link MutableOptionalValue}.
   * @return a new {@link MutableOptionalValue} that will store a {@link IElement}
   *         and have the given name, setterMethod and valueCreator.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   */
  public static <E extends IElement> MutableOptionalValue<E> forElement(
    final String name,
    final Consumer<E> setterMethod,
    final Function<INode<?>, E> valueCreator) {
    return new MutableOptionalValue<>(name, setterMethod, valueCreator, IElement::getSpecification);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link MutableOptionalValue} that will store a {@link Integer}
   *         and have the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static MutableOptionalValue<Integer> forInt(final String name, final Consumer<Integer> setterMethod) {
    return new MutableOptionalValue<>(name, setterMethod, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  /**
   * @param name
   * @param setterMethod
   * @return a new {@link MutableOptionalValue} that will store a {@link String}
   *         and have the given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static MutableOptionalValue<String> forString(final String name, final Consumer<String> setterMethod) {
    return new MutableOptionalValue<>(
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
   * Removes the value of the current {@link SingleValue}.
   */
  public void clear() {
    internalClear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return true;
  }
}
