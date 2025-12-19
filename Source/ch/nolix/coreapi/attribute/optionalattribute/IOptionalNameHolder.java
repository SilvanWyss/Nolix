package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalNameHolder} can have a name.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalNameHolder {
  /**
   * @return the name of the current {@link IOptionalNameHolder}.
   * @throws RuntimeException if the current {@link IOptionalNameHolder} does not
   *                          have a name.
   */
  String getName();

  /**
   * @return the name of the current {@link IOptionalNameHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalNameHolder} does not
   *                          have a name.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

  /**
   * @return true if the current {@link IOptionalNameHolder} has a name, false
   *         otherwise.
   */
  boolean hasName();

  /**
   * @param name
   * @return true if the current {@link IOptionalNameHolder} has the given name,
   *         false otherwise.
   */
  default boolean hasName(final String name) {
    return //
    hasName()
    && getName().equals(name);
  }
}
