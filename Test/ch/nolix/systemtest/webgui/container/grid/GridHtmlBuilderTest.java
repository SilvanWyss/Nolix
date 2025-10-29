package ch.nolix.systemtest.webgui.container.grid;

import ch.nolix.system.webgui.container.grid.Grid;
import ch.nolix.system.webgui.container.grid.GridHtmlBuilder;
import ch.nolix.systemapi.webgui.container.grid.IGrid;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class GridHtmlBuilderTest extends ControlHtmlBuilderTest<GridHtmlBuilder, IGrid> {
  @Override
  protected IGrid createControl() {
    return new Grid();
  }

  @Override
  protected GridHtmlBuilder createTestUnit() {
    return new GridHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div><table><tbody /></table></div>";
  }
}
