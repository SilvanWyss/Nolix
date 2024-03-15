//package declaration
package ch.nolix.coreapitest.attributeapitest.mandatoryattributeapitest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class TitleHolderTest extends Test {

  //method
  @TestCase
  public void testCase_getTitleInQuotes() {

    //setup
    final var testUnit = MockTitleHolder.withTitle("my_title");

    //execution
    final var result = testUnit.getTitleInQuotes();

    //verification
    expect(result).isEqualTo("'my_title'");
  }
}
