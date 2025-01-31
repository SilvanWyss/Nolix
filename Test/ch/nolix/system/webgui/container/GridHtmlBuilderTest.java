package ch.nolix.system.webgui.container;

import ch.nolix.system.webgui.basecontroltool.ControlHtmlBuilderTest;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;

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
