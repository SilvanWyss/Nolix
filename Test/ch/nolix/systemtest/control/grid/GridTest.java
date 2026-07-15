/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.grid;

import org.junit.jupiter.api.Test;

import ch.nolix.system.control.grid.Grid;
import ch.nolix.system.control.label.Label;
import ch.nolix.systemapi.control.grid.IGrid;
import ch.nolix.systemtest.control.container.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class GridTest extends ContainerTest<IGrid> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IGrid createTestUnit() {
    return new Grid();
  }

  @Test
  void testCase_clear() {
    // setup
    final var control = new Label();
    final var testUnit = new Grid();
    testUnit.insertControlAtRowAndColumn(2, 3, control);

    // execution
    testUnit.clear();

    // verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_insertControlAtRowAndColumn_whenIsEmpty() {
    // setup
    final var control = new Label();
    final var testUnit = new Grid();

    // execution
    testUnit.insertControlAtRowAndColumn(2, 3, control);

    // verification
    expect(testUnit.getRowCount()).isEqualTo(2);
    expect(testUnit.getColumnCount()).isEqualTo(3);
    expect(testUnit.getStoredChildControls()).containsExactly(control);
  }
}
