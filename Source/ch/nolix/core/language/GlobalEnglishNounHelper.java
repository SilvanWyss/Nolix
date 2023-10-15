//package declaration
package ch.nolix.core.language;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class GlobalEnglishNounHelper {

  // static method
  public static String getPlural(final String noun) {

    GlobalValidator.assertThat(noun).thatIsNamed(LowerCaseCatalogue.NOUN).isNotBlank();

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

  // static method
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

    if (noun.endsWith("ay")
        || noun.endsWith("ey")
        || noun.endsWith("iy")
        || noun.endsWith("oy")
        || noun.endsWith("uy")
        || noun.endsWith("ff")) {
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

  // constructor
  private GlobalEnglishNounHelper() {
  }
}
