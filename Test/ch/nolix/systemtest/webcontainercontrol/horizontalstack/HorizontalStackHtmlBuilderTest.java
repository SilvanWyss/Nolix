package ch.nolix.systemtest.webcontainercontrol.horizontalstack;

import org.junit.jupiter.api.Test;

import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStackHtmlBuilder;
import ch.nolix.systemapi.webcontainercontrol.horizontalstack.IHorizontalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class HorizontalStackHtmlBuilderTest
extends ControlHtmlBuilderTest<HorizontalStackHtmlBuilder, IHorizontalStack> {
  @Test
  void testCase_createHtmlElement_whenContainsChildControls() {
    //setup control
    final var control = createControl().addControls(new Label(), new Label(), new Label());

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
  protected IHorizontalStack createControl() {
    return new HorizontalStack();
  }

  @Override
  protected HorizontalStackHtmlBuilder createTestUnit() {
    return new HorizontalStackHtmlBuilder();
  }

  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
