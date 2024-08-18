//package declaration
package ch.nolix.systemtest.webguitest.linearcontainertest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStackHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IVerticalStack;
import ch.nolix.systemtest.webguitest.basecontroltooltest.ControlHtmlBuilderTest;

//class
final class VerticalStackHtmlBuilderTest extends ControlHtmlBuilderTest<VerticalStackHtmlBuilder, IVerticalStack> {

  //method
  @Test
  void testCase_createHtmlElement_whenContainsChildControls() {

    //setup control
    final var control = createControl().addControl(new Label(), new Label(), new Label());

    //setup testUnit
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.createHtmlElementForControl(control);

    //verification
    expect(result.getInnerText().isEmpty());
    final var childElements = result.getChildElements();
    expect(childElements).hasElementCount(3);
    expect(childElements.getStoredAt1BasedIndex(1).getType()).isEqualTo(HtmlElementTypeCatalogue.DIV);
    expect(childElements.getStoredAt1BasedIndex(2).getType()).isEqualTo(HtmlElementTypeCatalogue.DIV);
    expect(childElements.getStoredAt1BasedIndex(3).getType()).isEqualTo(HtmlElementTypeCatalogue.DIV);
  }

  //method
  @Override
  protected IVerticalStack createControl() {
    return new VerticalStack();
  }

  //method
  @Override
  protected VerticalStackHtmlBuilder createTestUnit() {
    return new VerticalStackHtmlBuilder();
  }

  //method
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
