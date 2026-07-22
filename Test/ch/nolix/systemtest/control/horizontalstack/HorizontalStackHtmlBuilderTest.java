/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.control.horizontalstack;

import org.junit.jupiter.api.Test;

import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.horizontalstack.HorizontalStackHtmlBuilder;
import ch.nolix.system.control.label.Label;
import ch.nolix.systemapi.control.horizontalstack.IHorizontalStack;
import ch.nolix.systemtest.webgui.basecontroltool.ControlHtmlBuilderTest;

/**
 * @author Silvan Wyss
 */
final class HorizontalStackHtmlBuilderTest
extends ControlHtmlBuilderTest<HorizontalStackHtmlBuilder, IHorizontalStack> {
  @Test
  void testCase_createHtmlElement_whenContainsChildControls() {
    // setup control
    final var control = createControl().addControls(new Label(), new Label(), new Label());

    // setup testUnit
    final var testUnit = createTestUnit();

   // execute
    final var result = testUnit.createHtmlElementForControl(control);

   // verify
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
  protected IHorizontalStack createControl() {
    return new HorizontalStack();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HorizontalStackHtmlBuilder createTestUnit() {
    return new HorizontalStackHtmlBuilder();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl() {
    return "<div />";
  }
}
