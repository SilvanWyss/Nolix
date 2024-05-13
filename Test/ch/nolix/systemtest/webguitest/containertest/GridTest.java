//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;

//class
final class GridTest extends ContainerTest<IGrid> {

  //method
  @Override
  protected IGrid createTestUnit() {
    return new Grid();
  }

  //method
  @Test
  void testCase_clear() {

    //setup
    final var control = new Label();
    final var testUnit = new Grid();
    testUnit.insertControlAtRowAndColumn(2, 3, control);

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_insertControlAtRowAndColumn_whenIsEmpty() {

    //setup
    final var control = new Label();
    final var testUnit = new Grid();

    //execution
    testUnit.insertControlAtRowAndColumn(2, 3, control);

    //verification
    expect(testUnit.getRowCount()).isEqualTo(2);
    expect(testUnit.getColumnCount()).isEqualTo(3);
    expect(testUnit.getStoredChildControls()).containsExactly(control);
  }
}
