/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.englishlanguage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.englishlanguage.EnglishNounTool;

/**
 * @author Silvan Wyss
 */
final class EnglishNounToolTest extends StandardTest {
  @ParameterizedTest
  @CsvSource({
  "bear",
  "crocodile",
  "deer",
  "flamingo",
  "gecko",
  "hawk",
  "jaguar",
  "kangaroo",
  "lion",
  "macaque",
  "nutria",
  "panda",
  "quokka",
  "rhino",
  "tiger",
  "viper",
  "warthog",
  "zebra",
  "Bear",
  "Crocodile",
  "Deer",
  "Flamingo",
  "Gecko",
  "Hawk",
  "Jaguar",
  "Kangaroo",
  "Lion",
  "Macaque",
  "Nutria",
  "Panda",
  "Quokka",
  "Rhino",
  "Tiger",
  "Viper",
  "Warthog",
  "Zebra"
  })
  void testCase_getArticleOfNoun_whenGivenNounHasArtilceA(final String noun) {
    // execute
    final var result = EnglishNounTool.getArticleOfNoun(noun);

    // verify
    expect(result).isEqualTo("a");
  }

  @ParameterizedTest
  @CsvSource({
  "antelope",
  "elephant",
  "ibis",
  "orang-utan",
  "urial",
  "Antelope",
  "Elephant",
  "Ibis",
  "Orang-utan",
  "Urial"
  })
  void testCase_getArticleOfNoun_whenGivenNounHasArtilceAn(final String noun) {
    // execute
    final var result = EnglishNounTool.getArticleOfNoun(noun);

    // verify
    expect(result).isEqualTo("an");
  }

  @ParameterizedTest
  @CsvSource({
  "antelope, antelopes",
  "elephant, elephants",
  "rose, roses",
  "child, children",
  "goose, geese",
  "fireman, firemen",
  "fix, fixes",
  "foot, feet",
  "ray, rays",
  "rose, roses",
  "mouse, mice",
  "rush, rushes",
  "shelf, shelves",
  "tooth, teeth"
  })
  void testCase_getPluralOfNoun(final String noun, final String expectedPlural) {
    // execute
    final var result = EnglishNounTool.getPluralOfNoun(noun);

    // verify
    expect(result).isEqualTo(expectedPlural);
  }
}
