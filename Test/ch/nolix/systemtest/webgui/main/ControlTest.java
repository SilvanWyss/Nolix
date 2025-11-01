package ch.nolix.systemtest.webgui.main;

import org.junit.jupiter.api.Test;

import ch.nolix.core.misc.dataobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.webcontainercontrol.floatcontainer.FloatContainer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;

public abstract class ControlTest<C extends IControl<C, ?>> extends StandardTest {
  protected abstract C createTestUnit();

  @Test
  final void testCase_belongsToGui_whenDoesNotBelongToGui() {
    //setup
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.belongsToGui();

    //verification
    expect(result).isFalse();
  }

  @Test
  final void testCase_belongsToGui_whenBelongsDirectlyToGui() {
    //setup
    final var webGui = new WebGui();
    final var testUnit = createTestUnit();
    webGui.pushLayerWithRootControl(testUnit);

    //execution
    final var result = testUnit.belongsToGui();

    //verification
    expect(result).isTrue();
  }

  @Test
  final void testCase_belongsToGui_whenBelongsToControlThatBelongsToGui() {
    //setup
    final var webGui = new WebGui();
    final var floatContainer = new FloatContainer();
    webGui.pushLayerWithRootControl(floatContainer);
    final var testUnit = createTestUnit();
    floatContainer.addControl(testUnit);

    //execution
    final var result = testUnit.belongsToGui();

    //verification
    expect(result).isTrue();
  }

  @Test
  final void testCase_editStyle() {
    //setup
    final var testUnit = createTestUnit();

    //execution
    testUnit.editStyle(s -> s.setTextColorForState(ControlState.BASE, X11ColorCatalog.DARK_CYAN));

    //verification
    final var actualBaseTextColor = testUnit.getStoredStyle().getTextColorWhenHasState(ControlState.BASE);
    expect(actualBaseTextColor).isEqualTo(X11ColorCatalog.DARK_CYAN);
  }

  @Test
  final void testCase_getInternalId() {
    //setup
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.getInternalId();

    //verification
    expect(result).startsWith("i");
    expect(result).hasLength(11);
  }

  @Test
  final void testCase_getInternalId_whenMethodIsCalledSeveralTimes() {
    //setup
    final var testUnit = createTestUnit();
    final var internalId = testUnit.getInternalId();

    for (var i = 1; i <= 10_000; i++) {
      //execution
      final var result = testUnit.getInternalId();

      //verification
      expect(result).isEqualTo(internalId);
    }
  }

  @Test
  final void testCase_getStoredChildControls() {
    //setup
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.getStoredChildControls();

    //verification
    expect(result).isEmpty();
  }

  @Test
  final void testCase_linkTo() {
    //setup
    final var voidObject = new VoidObject();
    final var testUnit = createTestUnit();

    //setup verification
    expect(testUnit.isLinkedToAnObject()).isFalse();

    //execution
    testUnit.linkTo(voidObject);

    //verification
    expect(testUnit.getStoredLinkedObjects()).containsExactly(voidObject);
  }

  @Test
  final void testCase_reset() {
    //setup
    final var testUnit = createTestUnit();
    testUnit.setInvisible();
    testUnit.setMinWidth(1000);
    testUnit.setMinHeight(500);
    testUnit.setMaxWidth(1200);
    testUnit.setMaxHeight(600);
    testUnit.setCursorIcon(CursorIcon.HAND);

    //execution
    testUnit.reset();

    //verification
    expect(testUnit.isVisible()).isTrue();
    expect(testUnit.hasMinWidth()).isFalse();
    expect(testUnit.hasMinHeight()).isFalse();
    expect(testUnit.hasMaxWidth()).isFalse();
    expect(testUnit.hasMaxHeight()).isFalse();
  }

  @Test
  final void testCase_setCursorIcon() {
    //setup
    final var testUnit = createTestUnit();

    //setup verification
    expect(testUnit.getCursorIcon()).isNot(CursorIcon.MOVE);

    //execution
    final var result = testUnit.setCursorIcon(CursorIcon.MOVE);

    //verification
    expect(result).is(testUnit);
    expect(testUnit.getCursorIcon()).is(CursorIcon.MOVE);
  }
}
