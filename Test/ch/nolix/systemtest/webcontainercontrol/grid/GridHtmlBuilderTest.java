package ch.nolix.systemtest.webcontainercontrol.grid;

import ch.nolix.system.webcontainercontrol.grid.Grid;
import ch.nolix.system.webcontainercontrol.grid.GridHtmlBuilder;
import ch.nolix.systemapi.webcontainercontrol.grid.IGrid;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
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
