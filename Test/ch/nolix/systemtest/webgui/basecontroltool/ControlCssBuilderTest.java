/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webgui.basecontroltool;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 * @param <B> the type of the {@link IControlCssBuilder}s of a
 *            {@link ControlCssBuilderTest}.
 * @param <C> the type of the {@link Control} of the {@link IControlCssBuilder}s
 *            of a {@link ControlCssBuilderTest}.
 */
public abstract class ControlCssBuilderTest<B extends IControlCssBuilder<C, ?>, C extends Control<C, ?>>
extends StandardTest {
  protected abstract C createControl();

  protected abstract B createTestUnit();

  @Test
  final void testCase_createCssRulesForControl_whenGivenControlHasAHoverBackgroundColor() {
    // setup
    final var control = createControl();
    control.editStyle(s -> s.forStateSetBackgroundColor(ControlState.HOVER, Color.fromString("0x102030")));
    final B testUnit = createTestUnit();

    // execute
    final var result = testUnit.createCssRulesForControl(control);

    // verify
    final var controlInternalId = control.getInternalId();
    final var hoverCssRule = result
      .getStoredSingle(r -> r.getSelector().startsWith("#" + controlInternalId + ":hover"));
    expect(hoverCssRule.getProperties()).containsExactlyOneWithStringRepresentation("background: #102030;");
  }

  @Test
  final void testCase_createCssRulesForControl_whenGivenControlIsNew() {
    // setup
    final var control = createControl();
    final var controlInternalId = control.getInternalId();
    final B testUnit = createTestUnit();

    // execute
    final var result = testUnit.createCssRulesForControl(control);

    // verify part 1
    expect(result).hasElementCount(4);
    final var cssRuleForAllStates = result.getStoredFirst(r -> r.getSelector().startsWith("#" + controlInternalId));
    expect(cssRuleForAllStates.getProperties().containsMatching(p -> p.hasName("cursor"))).isTrue();

    // verify part 2
    expect(result.containsMatching(r -> r.getSelector().startsWith("#" + controlInternalId + ":hover"))).isTrue();

    // verify part 3
    expect(result.containsMatching(r -> r.getSelector().startsWith("#" + controlInternalId + ":focus"))).isTrue();
  }
}
