//package declaration
package ch.nolix.coreapi.functionapi.requestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link TypeRequestable} is of a certain type.
 * 
 * @author Silvan Wyss
 * @date 2018-11-25
 */
@AllowDefaultMethodsAsDesignPattern
public interface TypeRequestable {

  //method
  /**
   * @return the type of the current {@link TypeRequestable}.
   */
  default String getType() {
    return getClass().getSimpleName();
  }

  //method
  /**
   * @param concreteType
   * @return true if the current {@link TypeRequestable} is of the given concrete
   *         type.
   */
  default boolean isOfConcreteType(final Class<?> concreteType) {
    return (getClass() == concreteType);
  }

  //method
  /**
   * @param concreteType
   * @return true if the current {@link TypeRequestable} is of the given concrete
   *         type.
   */
  default boolean isOfConcreteType(final String concreteType) {

    //For a better performance, this implementation does not use all comfortable
    //methods.
    return getClass().getSimpleName().equals(concreteType);
  }

  //method
  /**
   * @param type
   * @return true if the current {@link TypeRequestable} is of the given type.
   */
  default boolean isOfType(final Class<?> type) {
    return type.isAssignableFrom(getClass());
  }

  //method
  /**
   * @param type
   * @return true if the current {@link TypeRequestable} is of the given type.
   */
  default boolean isOfType(final String type) {

    //Iterates the classes of this type requestable object.
    Class<?> c = getClass();
    while (c.getSuperclass() != null) {

      //Handles the case that the current class is the given type or super type.
      if (c.getSimpleName().equals(type)) {
        return true;
      }

      c = c.getSuperclass();
    }

    return false;
  }
}
