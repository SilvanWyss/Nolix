package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalQualifiedNameHolder} can have a qualified name.
 * 
 * @author Silvan Wyss
 * @version 2024-02-11
 */
public interface IOptionalQualifiedNameHolder extends IOptionalNameHolder {
  /**
   * @return the qualified name of the current
   *         {@link IOptionalQualifiedNameHolder}.
   * @throws RuntimeException if the current {@link IOptionalQualifiedNameHolder}
   *                          does not have a qualified name.
   */
  default String getQualifiedName() {
    return (getQualifyingPrefix() + getName());
  }

  /**
   * @return the qualifiedName of the current {@link IOptionalQualifiedNameHolder}
   *         in quotes.
   * @throws RuntimeException if the current {@link IOptionalQualifiedNameHolder}
   *                          does not have a qualifiedName.
   */
  default String getQualifiedNameInQuotes() {
    return ("'" + getQualifiedName() + "'");
  }

  /**
   * @return the qualifying prefix of the current
   *         {@link IOptionalQualifiedNameHolder}.
   */
  String getQualifyingPrefix();

  /**
   * @return true if the current {@link IOptionalQualifiedNameHolder} has a
   *         qualifiedName.
   */
  default boolean hasQualifiedName() {
    return hasName();
  }
}
