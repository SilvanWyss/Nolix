/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webcontainercontrol.verticalstack;

import org.junit.jupiter.api.Test;

import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStackHtmlBuilder;
import ch.nolix.systemapi.webcontainercontrol.verticalstack.IVerticalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class VerticalStackHtmlBuilderTest extends ControlHtmlBuilderTest<VerticalStackHtmlBuilder, IVerticalStack> {
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

  /**
   * {@inheritDoc}
   */
  @Override
  protected IVerticalStack createControl() {
    return new VerticalStack();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected VerticalStackHtmlBuilder createTestUnit() {
    return new VerticalStackHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
