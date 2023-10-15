//package declaration
package ch.nolix.templatetest.webguitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.webguitest.styletest.StyleTestPool;

//class
public final class WebGuiTestPool extends TestPool {

  // constructor
  public WebGuiTestPool() {
    super(new StyleTestPool());
  }
}
