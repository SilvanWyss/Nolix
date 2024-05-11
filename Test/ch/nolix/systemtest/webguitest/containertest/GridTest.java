//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;

//class
final class GridTest extends ContainerTest<IGrid> {

  //method
  @Override
  protected IGrid createTestUnit() {
    return new Grid();
  }
}
