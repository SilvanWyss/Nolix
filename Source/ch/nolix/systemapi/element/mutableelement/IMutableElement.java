package ch.nolix.systemapi.element.mutableelement;

import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.state.statemutation.Resettable;
import ch.nolix.systemapi.element.base.IElement;

/**
 * A {@link IMutableElement} is a {@link IElement}. The attributes of a
 * {@link IMutableElement} can be mutated separately. The attributes of a
 * {@link IMutableElement} can be reset together.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public interface IMutableElement extends Resettable, IElement {
  /**
   * Adds or changes the given attribute to the current {@link IMutableElement}.
   * 
   * @param attribute
   */
  void addOrChangeAttribute(INode<?> attribute);

  /**
   * Adds or changes the given attributes to the current {@link IMutableElement}.
   * 
   * @param attribute
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid.
   */
  default void addOrChangeAttribute(final INode<?> attribute, final INode<?>... attributes) {
    //Calls other method.
    addOrChangeAttribute(attribute);

    //Iterates the given attributes.
    for (final var a : attributes) {
      //Calls other method.
      addOrChangeAttribute(a);
    }
  }

  /**
   * Adds or changes the given attributes to the current {@link IMutableElement}.
   * 
   * @param attribute
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid.
   */
  void addOrChangeAttribute(String attribute, String... attributes);

  /**
   * Adds or changes the given attributes to the current {@link IMutableElement}.
   * 
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid.
   */
  default void addOrChangeAttributes(final Iterable<? extends INode<?>> attributes) {
    //Iterates the given attributes.
    for (final var a : attributes) {
      addOrChangeAttribute(a);
    }
  }

  /**
   * Resets the current {@link IMutableElement} from the given attributes.
   * 
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid.
   */
  default void resetFromAttributes(final Iterable<? extends INode<?>> attributes) {
    reset();

    addOrChangeAttributes(attributes);
  }

  /**
   * Resets the current {@link IMutableElement} from the given specification.
   * 
   * @param specification
   * @throws RuntimeException if the given specification is not valid.
   */
  default void resetFromSpecification(final INode<?> specification) {
    resetFromAttributes(specification.getStoredChildNodes());
  }
}
