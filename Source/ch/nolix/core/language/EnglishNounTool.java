//package declaration
package ch.nolix.core.language;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class EnglishNounTool {

  //constant
  private static final String ARTICLE_A = "a";

  //constant
  private static final String ARTICLE_AN = "an";

  //constant
  private static final EnglishPostfixAnalyser ENGLISH_POSTFIX_ANALYSER = new EnglishPostfixAnalyser();

  //method
  public String getArticleOfNoun(final String noun) {

    final var firstLetter = noun.charAt(0);

    return getArticleOfNounWithFirstLetter(firstLetter);
  }

  //method
  public String getPlural(final String noun) {

    GlobalValidator.assertThat(noun).thatIsNamed(LowerCaseVariableCatalogue.NOUN).isNotBlank();

    return switch (noun) {
      case "child" ->
        "children";
      case "foot" ->
        "feet";
      case "goose" ->
        "geese";
      case "mouse" ->
        "mice";
      case "tooth" ->
        "teeth";
      default ->
        getPluralOfNounDependingOnEnding(noun);
    };
  }

  //method
  private String getArticleOfNounWithFirstLetter(final char firstLetter) {

    //Asserts that the given letter is valid.
    if (firstLetter < 65
    || (firstLetter > 90 && firstLetter < 97)
    || firstLetter > 122) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.LETTER, firstLetter);
    }

    //Enumerates the given letter.
    return switch (firstLetter) {
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

  //method
  private String getPluralOfNounDependingOnEnding(final String noun) {

    if (noun.endsWith("man")) {
      return (noun.substring(0, noun.length() - 3) + "men");
    }

    if (noun.endsWith("ef")) {
      return (noun.substring(0, noun.length() - 1) + "ves");
    }

    if (noun.endsWith("ss") || noun.endsWith("x")) {
      return (noun + "es");
    }

    if (ENGLISH_POSTFIX_ANALYSER.endsWithVocalAndY(noun) || noun.endsWith("ff")) {
      return (noun + "s");
    }

    if (noun.endsWith("y")) {
      return (noun.substring(0, noun.length() - 1) + "ies");
    }

    if (noun.endsWith("f")) {
      return (noun.substring(0, noun.length() - 1) + "ves");
    }

    if (noun.endsWith("s")) {
      return (noun + "ses");
    }

    return (noun + "s");
  }
}