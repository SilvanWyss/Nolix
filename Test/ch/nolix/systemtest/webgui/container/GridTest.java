package ch.nolix.systemtest.webgui.container;

import org.junit.jupiter.api.Test;

import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.systemapi.webgui.container.IGrid;
import ch.nolix.systemtest.webgui.basecontainer.ContainerTest;

final class GridTest extends ContainerTest<IGrid> {

  @Override
  protected IGrid createTestUnit() {
    return new Grid();
  }

  @Test
  void testCase_clear() {

    //setup
    final var control = new Label();
    final var testUnit = new Grid();
    testUnit.insertControlAtRowAndColumn(2, 3, control);

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

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
