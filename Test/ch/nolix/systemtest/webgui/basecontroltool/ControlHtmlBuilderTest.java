/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webgui.basecontroltool;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <B> the type of the {@link IControlHtmlBuilder}s of a
 *            {@link ControlHtmlBuilderTest}.
 * @param <C> the type of the {@link Control} of the
 *            {@link IControlHtmlBuilder}s of a {@link ControlHtmlBuilderTest}.
 */
public abstract class ControlHtmlBuilderTest<B extends IControlHtmlBuilder<C>, C extends Control<C, ?>>
extends StandardTest {
  @Test
  final void testCase_createHtmlElementForNewControl() {
    // setup
    final B testUnit = createTestUnit();

    // execution
    final var result = testUnit.createHtmlElementForControl(createControl());

    // verification
    expect(result).hasStringRepresentation(getExpectedStringRepresentationOfCreatedHtmlElementForNewControl());
  }

  protected abstract C createControl();

  protected abstract B createTestUnit();

  protected abstract String getExpectedStringRepresentationOfCreatedHtmlElementForNewControl();
}
