//package declaration
package ch.nolix.systemtest.webguitest.containertest;

//own imports
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.system.webgui.container.GridHtmlBuilder;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class GridHtmlBuilderTest extends ControlHtmlBuilderTest<GridHtmlBuilder, IGrid> {

  //method
  @Override
  protected IGrid createControl() {
    return new Grid();
  }

  //method
  @Override
  protected GridHtmlBuilder createTestUnit() {
    return new GridHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div><table><tbody /></table></div>";
  }
}
