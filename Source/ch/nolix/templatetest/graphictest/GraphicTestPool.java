//package declaration
package ch.nolix.templatetest.graphictest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.graphictest.texturetest.TextureTestPool;

//class
public final class GraphicTestPool extends TestPool {

  // constructor
  public GraphicTestPool() {
    super(new TextureTestPool());
  }
}
