/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.grid;

import ch.nolix.system.control.grid.Grid;
import ch.nolix.system.control.grid.GridCssBuilder;
import ch.nolix.systemapi.control.grid.IGrid;
import ch.nolix.systemtest.webgui.basecontroltool.ControlCssBuilderTest;

/**
 * @author Silvan Wyss
 */
final class GridCssBuilderTest extends ControlCssBuilderTest<GridCssBuilder, IGrid> {
  /**
   * {@inheritDoc}
   */
  @Override
  protected IGrid createControl() {
    return new Grid();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GridCssBuilder createTestUnit() {
    return new GridCssBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getExpectedCssRuleCountForNewControl() {
    return 10;
  }
}
