//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.LabelHtmlBuilder;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class LabelHtmlBuilderTest extends ControlHtmlBuilderTest<LabelHtmlBuilder, ILabel> {

  //method
  @Override
  protected ILabel createControl() {
    return new Label();
  }

  //method
  @Override
  protected LabelHtmlBuilder createTestUnit() {
    return new LabelHtmlBuilder();
  }

  //method
  @Override
  protected void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(final IHtmlElement htmlElement) {
    expect(htmlElement.getType()).isEqualTo(HtmlElementTypeCatalogue.DIV);
    expect(htmlElement.getInnerText()).isEqualTo(StringCatalogue.MINUS);
  }
}
