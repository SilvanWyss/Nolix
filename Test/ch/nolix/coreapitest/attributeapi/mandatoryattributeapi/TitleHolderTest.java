package ch.nolix.coreapitest.attributeapi.mandatoryattributeapi;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class TitleHolderTest extends StandardTest {
  @Test
  void testCase_getTitleInQuotes() {
    //setup
    final var testUnit = MockTitleHolder.withTitle("my_title");

    //execution
    final var result = testUnit.getTitleInQuotes();

    //verification
    expect(result).isEqualTo("'my_title'");
  }
}
