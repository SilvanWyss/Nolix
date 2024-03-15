//package declaration
package ch.nolix.coretest.webtest.csstest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.core.web.css.CssProperty;
import ch.nolix.core.web.css.CssRule;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class CssRuleTest extends Test {

  //method
  @TestCase
  public void testCase_withPrefixedSelector() {

    //setup
    final var property1 = CssProperty.withNameAndValue("n1", "v1");
    final var property2 = CssProperty.withNameAndValue("n2", "v2");
    final var testUnit = CssRule.withSelectorAndProperty("div", property1, property2);

    //execution
    final var result = testUnit.withPrefixedSelector("#my_id ");

    //Verifies the selector of the result.
    expect(result.getSelector()).isEqualTo("#my_id div");

    //Verifies the properties of the result.
    final var proeprties = result.getProperties();
    expect(proeprties).contains(property1);
    expect(proeprties).contains(property2);
  }
}
