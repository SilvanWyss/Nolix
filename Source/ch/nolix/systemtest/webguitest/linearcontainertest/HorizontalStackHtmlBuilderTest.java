//package declaration
package ch.nolix.systemtest.webguitest.linearcontainertest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.HorizontalStackHtmlBuilder;
import ch.nolix.systemapi.webguiapi.linearcontainerapi.IHorizontalStack;
import ch.nolix.systemtest.webguitest.basecontrolservicetest.ControlHtmlBuilderTest;

//class
public final class HorizontalStackHtmlBuilderTest
extends ControlHtmlBuilderTest<HorizontalStackHtmlBuilder, IHorizontalStack> {

  //method
  @TestCase
  public void testCase_createHtmlElement_whenContainsChildControls() {

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
  protected IHorizontalStack createControl() {
    return new HorizontalStack();
  }

  //method
  @Override
  protected HorizontalStackHtmlBuilder createTestUnit() {
    return new HorizontalStackHtmlBuilder();
  }

  //method
  @Override
  protected void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(final IHtmlElement htmlElement) {
    expect(htmlElement.getType()).isEqualTo(HtmlElementTypeCatalogue.DIV);
    expect(htmlElement.getInnerText()).isEmpty();
    expectNot(htmlElement.containsChildElements());
  }
}
