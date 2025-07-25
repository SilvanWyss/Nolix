package ch.nolix.systemtest.webgui.linearcontainer;

import org.junit.jupiter.api.Test;

import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStackHtmlBuilder;
import ch.nolix.systemapi.webgui.linearcontainer.IVerticalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

final class VerticalStackHtmlBuilderTest extends ControlHtmlBuilderTest<VerticalStackHtmlBuilder, IVerticalStack> {

  @Test
  void testCase_createHtmlElement_whenContainsChildControls() {

    //setup control
    final var control = createControl().addControl(new Label(), new Label(), new Label());

    //setup testUnit
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.createHtmlElementForControl(control);

    //verification
    expect(result.getInnerText().isEmpty()).isTrue();
    final var childElements = result.getChildElements();
    expect(childElements).hasElementCount(3);
    expect(childElements.getStoredAtOneBasedIndex(1).getType()).isEqualTo(HtmlElementTypeCatalog.DIV);
    expect(childElements.getStoredAtOneBasedIndex(2).getType()).isEqualTo(HtmlElementTypeCatalog.DIV);
    expect(childElements.getStoredAtOneBasedIndex(3).getType()).isEqualTo(HtmlElementTypeCatalog.DIV);
  }

  @Override
  protected IVerticalStack createControl() {
    return new VerticalStack();
  }

  @Override
  protected VerticalStackHtmlBuilder createTestUnit() {
    return new VerticalStackHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
