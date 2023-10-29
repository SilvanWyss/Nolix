//package declaration
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-01
 * @param <V> is the type of the value of a {@link Value}.
 */
public final class Value<V> extends SingleValue<V> {

  //constructor
  /**
   * Creates a new {@link Value} with the given name, setterMethod, valueCreator
   * and specificationCreator.
   * 
   * @param name
   * @param setterMethod
   * @param valueCreator
   * @param specificationCreator
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given setterMethod is blank.
   * @throws ArgumentIsNullException  if the given valueCreator is null.
   * @throws ArgumentIsNullException  if the given specificationCreator is null.
   */
  public Value(
    final String name,
    final Consumer<V> setterMethod,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {

    //Calls constructor of the base class.
    super(name, setterMethod, valueCreator, specificationCreator);
  }

  //static method
  /**
   * @param name
   * @param setterMethod
   * @return a new {@link Value} that will store a {@link Boolean} and have the
   *         given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static Value<Boolean> forBoolean(final String name, final Consumer<Boolean> setterMethod) {
    return new Value<>(name, setterMethod, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  //static method
  /**
   * @param name
   * @param setterMethod
   * @return a new {@link Value} that will store a {@link Integer} and have the
   *         given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static Value<Integer> forInt(final String name, final Consumer<Integer> setterMethod) {
    return new Value<>(name, setterMethod, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  //static method
  /**
   * @param name
   * @param setterMethod
   * @return a new {@link Value} that will store a {@link String} and have the
   *         given name and setterMethod.
   * @throws ArgumentIsNullException  if the given name is null.
   * @throws InvalidArgumentException if the given name is blank.
   * @throws ArgumentIsNullException  if the given setterMethod is null.
   */
  public static Value<String> forString(final String name, final Consumer<String> setterMethod) {
    return new Value<>(
      name,
      setterMethod,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      Value::getStringValueSpecificationForAValueFromString);
  }

  //static method
  /**
   * @param string
   * @return the specification of a {@link String} value of a {@link Value} from
   *         the given string.
   * @throws InvalidArgumentException if the given string does not represent a
   *                                  {@link String} value for a {@link Value}.
   */
  private static Node getStringValueSpecificationForAValueFromString(final String string) {

    if (string.isEmpty()) {
      return Node.EMPTY_NODE;
    }

    return Node.withChildNode(string);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return false;
  }
}
