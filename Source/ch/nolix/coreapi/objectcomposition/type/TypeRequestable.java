package ch.nolix.coreapi.objectcomposition.type;

/**
 * A {@link TypeRequestable} is of a certain type.
 * 
 * @author Silvan Wyss
 */
public interface TypeRequestable {
  /**
   * @return the type of the current {@link TypeRequestable}.
   */
  default String getType() {
    return getClass().getSimpleName();
  }

  /**
   * @param concreteType
   * @return true if the current {@link TypeRequestable} is of the given concrete
   *         type, false otherwise.
   */
  default boolean isOfConcreteType(final Class<?> concreteType) {
    return (getClass() == concreteType);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param concreteType
   * @return true if the current {@link TypeRequestable} is of the given concrete
   *         type, false otherwise.
   */
  default boolean isOfConcreteType(final String concreteType) {
    return getClass().getSimpleName().equals(concreteType);
  }

  /**
   * @param type
   * @return true if the current {@link TypeRequestable} is of the given type,
   *         false otherwise.
   */
  default boolean isOfType(final Class<?> type) {
    return type.isAssignableFrom(getClass());
  }

  /**
   * @param type
   * @return true if the current {@link TypeRequestable} is of the given type,
   *         false otherwise.
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
