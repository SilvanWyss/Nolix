//package declaration
package ch.nolix.systemtest.graphictest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.graphictest.colortest.ColorTestPool;
import ch.nolix.systemtest.graphictest.imagetest.ImageTestPool;

//class
public final class GraphicTestPool extends TestPool {

  //constructor
  public GraphicTestPool() {
    super(new ColorTestPool(), new ImageTestPool());
  }
}
