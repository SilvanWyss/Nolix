package ch.nolix.systemtest.webguitest.containertest;

import ch.nolix.system.webgui.container.Grid;
import ch.nolix.system.webgui.container.GridHtmlBuilder;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

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
