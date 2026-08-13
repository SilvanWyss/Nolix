/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.englishlanguage;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.EnglishArticleCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 */
public final class EnglishNounTool {
  private static final EnglishWordEndExaminer ENGLISH_WORD_END_EXAMINER = new EnglishWordEndExaminer();

  private EnglishNounTool() {
  }

  public static String getArticleOfNoun(final String noun) {
    final var firstLetter = noun.charAt(0);

    return getArticleOfNounWithFirstLetter(firstLetter);
  }

  public static String getPluralOfNoun(final String noun) {
    return //
    switch (noun) {
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

  private static String getArticleOfNounWithFirstLetter(final char firstLetter) {
    // Asserts that the given letter is valid.
    if (firstLetter < 65
    || (firstLetter > 90 && firstLetter < 97)
    || firstLetter > 122) {
      throw InvalidArgumentException.forArgumentAndArgumentName(firstLetter, LowerCaseVariableNameCatalog.LETTER);
    }

    // Enumerates the given letter.
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
        EnglishArticleCatalog.AN;
      default ->
        EnglishArticleCatalog.A;
    };
  }

  private static String getPluralOfNounDependingOnEnding(final String noun) {
    Validator.assertThat(noun).thatIsNamed(LowerCaseVariableNameCatalog.NOUN).isNotBlank();

    if (noun.endsWith("man")) {
      return (noun.substring(0, noun.length() - 3) + "men");
    }

    if (noun.endsWith("ef")) {
      return (noun.substring(0, noun.length() - 1) + "ves");
    }

    if (pluralOfNounEndsWithEs(noun)) {
      return (noun + "es");
    }

    if (ENGLISH_WORD_END_EXAMINER.endsWithVocalAndY(noun) || noun.endsWith("ff")) {
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

  private static boolean pluralOfNounEndsWithEs(final String noun) {
    return //
    noun.endsWith("sh")
    || noun.endsWith("ss")
    || noun.endsWith("x");
  }
}
