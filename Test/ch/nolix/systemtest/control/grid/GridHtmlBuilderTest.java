/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.grid;

import ch.nolix.system.control.grid.Grid;
import ch.nolix.system.control.grid.GridHtmlBuilder;
import ch.nolix.systemapi.control.grid.IGrid;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class GridHtmlBuilderTest extends ControlHtmlBuilderTest<GridHtmlBuilder, IGrid> {
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
  protected GridHtmlBuilder createTestUnit() {
    return new GridHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div><table><tbody /></table></div>";
  }
}
