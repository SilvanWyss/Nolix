//package declaration
package ch.nolix.systemapi.elementapi.mutableelementapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.methodapi.mutationapi.Resettable;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.elementapi.specificationapi.Specified;

//interface
/**
 * A {@link IMutableElement} is a {@link Specified}. The attributes of a
 * {@link IMutableElement} can be mutated separately. The attributes of a
 * {@link IMutableElement} can be reset together.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IMutableElement extends Resettable, Specified {

  //method declaration
  /**
   * Adds or changes the given attribute to the current {@link IMutableElement}.
   * 
   * @param attribute
   */
  void addOrChangeAttribute(INode<?> attribute);

  //method
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

  //method
  /**
   * Adds or changes the given attributes to the current {@link IMutableElement}.
   * 
   * @param attribute
   * @param attributes
   * @throws RuntimeException if one of the given attributes is not valid.
   */
  void addOrChangeAttribute(String attribute, String... attributes);

  //method
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

  //method
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

  //method
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
