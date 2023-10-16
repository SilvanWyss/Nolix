//package declaration
package ch.nolix.coretest.webtest.csstest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class CssTestPool extends TestPool {

  //constructor
  public CssTestPool() {
    super(CssPropertyTest.class, CssRuleTest.class);
  }
}
