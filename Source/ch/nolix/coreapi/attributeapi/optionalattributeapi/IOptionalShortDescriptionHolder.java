//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalShortDescriptionHolder} can have a short description.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface IOptionalShortDescriptionHolder {

  //method declaration
  /**
   * @return the short description of the current {@link IOptionalShortDescriptionHolder}.
   * @throws RuntimeException if the current {@link IOptionalShortDescriptionHolder} does
   *                          not have short description.
   */
  String getShortDescription();

  //method declaration
  /**
   * @return the short description of the current {@link IOptionalShortDescriptionHolder}
   *         in quotes.
   * @throws RuntimeException if the current {@link IOptionalShortDescriptionHolder} does
   *                          not have short description.
   */
  String getShortDescriptionInQuotes();

  //method declaration
  /**
   * @return true if the current {@link IOptionalShortDescriptionHolder} has a short
   *         description, false otherwise.
   */
  boolean hasShortDescription();
}
