//package declaration
package ch.nolix.core.language;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class GlobalEnglishNounHelper {

  private static final EnglishPostfixAnalyser ENGLISH_POSTFIX_ANALYSER = new EnglishPostfixAnalyser();

  //constructor
  private GlobalEnglishNounHelper() {
  }

  //static method
  public static String getPlural(final String noun) {

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

  //static method
  private static String getPluralOfNounDependingOnEnding(final String noun) {

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
