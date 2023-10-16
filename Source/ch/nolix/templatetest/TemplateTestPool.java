//package declaration
package ch.nolix.templatetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.graphictest.GraphicTestPool;
import ch.nolix.templatetest.mathtest.MathTestPool;
import ch.nolix.templatetest.webguitest.WebGuiTestPool;

//class
public final class TemplateTestPool extends TestPool {

  //constructor
  public TemplateTestPool() {
    super(new GraphicTestPool(), new MathTestPool(), new WebGuiTestPool());
  }
}
