//package declaration
package ch.nolix.core.commontypetool;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * The {@link GlobalCharacterTool} provides methods to handle characters. Of
 * the {@link GlobalCharacterTool} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class GlobalCharacterTool {

  //constant
  private static final String ARTICLE_A = "a";

  //constant
  private static final String ARTICLE_AN = "an";

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalCharacterTool} can be
   * created.
   */
  private GlobalCharacterTool() {
  }

  //static method
  /**
   * @param letter
   * @return the article of a word that starts with the given letter.
   * @throws InvalidArgumentException if the given letter is not valid.
   */
  public static String getArticle(final char letter) {

    //Asserts that the given letter is valid.
    if (letter < 65
    || (letter > 90 && letter < 97)
    || letter > 122) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.LETTER, letter);
    }

    //Enumerates the given letter.
    return switch (letter) {
      case
      'A',
      'a',
      'E',
      'e',
      'I',
      'i',
      'O',
      'o',
      'U',
      'u' ->
        ARTICLE_AN;
      default ->
        ARTICLE_A;
    };
  }

  //static method
  /**
   * @param character
   * @return true if the given character is a digit.
   */
  public static boolean isDigit(final char character) {
    return (character >= 48 && character <= 57);
  }

  //static method
  /**
   * @param character
   * @return true if the given character is a hexadecimal digit.
   */
  public static boolean isHexadecimalDigit(final char character) {
    return (isDigit(character) || (character >= 65 && character <= 70));
  }
}
