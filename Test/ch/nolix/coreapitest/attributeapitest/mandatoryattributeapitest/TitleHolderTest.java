//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class TitleHolderTest extends StandardTest {

  //method
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
