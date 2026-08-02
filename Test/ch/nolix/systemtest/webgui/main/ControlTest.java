/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.webgui.main;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.control.floatcontainer.FloatContainer;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.main.ControlState;

/**
 * @author Silvan Wyss
 * @param <C> the type of the {@link Control}s of a {@link ControlTest}.
 */
public abstract class ControlTest<C extends Control<C, ?>> extends StandardTest {
  protected abstract C createTestUnit();

  @Test
  final void testCase_belongsToGui_whenDoesNotBelongToGui() {
    // setup
    final var testUnit = createTestUnit();

    // execute
    final var result = testUnit.belongsToGui();

    // verify
    expect(result).isFalse();
  }

  @Test
  final void testCase_belongsToGui_whenBelongsDirectlyToGui() {
    // setup
    final var webGui = new WebGui();
    final var testUnit = createTestUnit();
    webGui.pushLayerWithRootControl(testUnit);

    // execute
    final var result = testUnit.belongsToGui();

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_belongsToGui_whenBelongsToControlThatBelongsToGui() {
    // setup
    final var webGui = new WebGui();
    final var floatContainer = new FloatContainer();
    webGui.pushLayerWithRootControl(floatContainer);
    final var testUnit = createTestUnit();
    floatContainer.addControl(testUnit);

    // execute
    final var result = testUnit.belongsToGui();

    // verify
    expect(result).isTrue();
  }

  @Test
  final void testCase_editStyle() {
    // setup
    final var testUnit = createTestUnit();

    // execute
    testUnit.editStyle(s -> s.forStateSetTextColor(ControlState.BASE, X11ColorCatalog.DARK_CYAN));

    // verify
    final var actualBaseTextColor = testUnit.getStoredStyle().getTextColorWhenHasState(ControlState.BASE);
    expect(actualBaseTextColor).isEqualTo(X11ColorCatalog.DARK_CYAN);
  }

  @Test
  final void testCase_getAttributes_whenIsCollapsed() {
    // setup
    final var testUnit = createTestUnit();
    testUnit.setCollapsed();

    // execute
    final var result = testUnit.getAttributes();

    // verify
    expect(result).containsEqualing(ImmutableNode.fromString("Presence(COLLAPSED)"));
  }

  @Test
  final void testCase_getInternalId() {
    // setup
    final var testUnit = createTestUnit();

    // execute
    final var result = testUnit.getInternalId();

    // verify
    expect(result).startsWith("i");
    expect(result).hasLength(11);
  }

  @Test
  final void testCase_getInternalId_whenMethodIsCalledSeveralTimes() {
    // setup
    final var testUnit = createTestUnit();
    final var internalId = testUnit.getInternalId();

    for (var i = 1; i <= 10_000; i++) {
      // execute
      final var result = testUnit.getInternalId();

      // verify
      expect(result).isEqualTo(internalId);
    }
  }

  @Test
  final void testCase_getStoredChildControls() {
    // setup
    final var testUnit = createTestUnit();

    // execute
    final var result = testUnit.getStoredChildControls();

    // verify
    expect(result).isEmpty();
  }

  @Test
  final void testCase_reset() {
    // setup
    final var testUnit = createTestUnit();
    testUnit.setInvisible();
    testUnit.setMinWidth(1000);
    testUnit.setMinHeight(500);
    testUnit.setMaxWidth(1200);
    testUnit.setMaxHeight(600);
    testUnit.setCursorIcon(CursorIcon.HAND);

    // execute
    testUnit.reset();

    // verify
    expect(testUnit.isVisible()).isTrue();
    expect(testUnit.hasMinWidth()).isFalse();
    expect(testUnit.hasMinHeight()).isFalse();
    expect(testUnit.hasMaxWidth()).isFalse();
    expect(testUnit.hasMaxHeight()).isFalse();
  }

  @Test
  final void testCase_setCursorIcon() {
    // setup
    final var testUnit = createTestUnit();

    // setup verification
    expect(testUnit.getCursorIcon()).isNot(CursorIcon.MOVE);

    // execute
    final var result = testUnit.setCursorIcon(CursorIcon.MOVE);

    // verify
    expect(result).is(testUnit);
    expect(testUnit.getCursorIcon()).is(CursorIcon.MOVE);
  }
}
