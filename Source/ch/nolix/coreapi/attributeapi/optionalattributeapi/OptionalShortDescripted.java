//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalShortDescripted} can have a short description.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalShortDescripted {

  //method declaration
  /**
   * @return the short description of the current {@link OptionalShortDescripted}.
   * @throws RuntimeException if the current {@link OptionalShortDescripted} does
   *                          not have short description.
   */
  String getShortDescription();

  //method declaration
  /**
   * @return the short description of the current {@link OptionalShortDescripted}
   *         in quotes.
   * @throws RuntimeException if the current {@link OptionalShortDescripted} does
   *                          not have short description.
   */
  String getShortDescriptionInQuotes();

  //method declaration
  /**
   * @return true if the current {@link OptionalShortDescripted} has a short
   *         description, false otherwise.
   */
  boolean hasShortDescription();
}
