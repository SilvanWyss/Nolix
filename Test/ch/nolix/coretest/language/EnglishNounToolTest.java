package ch.nolix.coretest.language;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.englishlanguage.EnglishNounTool;
import ch.nolix.core.testing.standardtest.StandardTest;

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

    //setup
    final var testUnit = new EnglishNounTool();

    //execution
    final var result = testUnit.getArticleOfNoun(noun);

    //verification
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

    //setup
    final var testUnit = new EnglishNounTool();

    //execution
    final var result = testUnit.getArticleOfNoun(noun);

    //verification
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

    //setup
    final var testUnit = new EnglishNounTool();

    //execution
    final var result = testUnit.getPluralOfNoun(noun);

    //verification
    expect(result).isEqualTo(expectedPlural);
  }
}
