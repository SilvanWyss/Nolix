//package declaration
package ch.nolix.coretest.webtest.htmltest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class HtmlTestPool extends TestPool {

  //constructor
  public HtmlTestPool() {
    super(HtmlElementStringRepresentatorTest.class, HtmlElementTest.class);
  }
}
