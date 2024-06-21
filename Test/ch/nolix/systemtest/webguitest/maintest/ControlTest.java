//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlTest<C extends IControl<C, ?>> extends StandardTest {

  //method declaration
  protected abstract C createTestUnit();

  //method
  @Test
  final void testCase_editStyle() {

    //setup
    final var testUnit = createTestUnit();

    //execution
    testUnit.editStyle(s -> s.setTextColorForState(ControlState.BASE, Color.DARK_CYAN));

    //verification
    final var actualBaseTextColor = testUnit.getStoredStyle().getTextColorWhenHasState(ControlState.BASE);
    expect(actualBaseTextColor).isEqualTo(Color.DARK_CYAN);
  }

  //method
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

  //method
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

  //method
  @Test
  final void testCase_getStoredChildControls() {

    //setup
    final var testUnit = createTestUnit();

    //execution
    final var result = testUnit.getStoredChildControls();

    //verification
    expect(result).isEmpty();
  }

  //method
  @Test
  final void testCase_linkTo() {

    //setup
    final var voidObject = new VoidObject();
    final var testUnit = createTestUnit();

    //setup verification
    expectNot(testUnit.isLinkedToAnObject());

    //execution
    testUnit.linkTo(voidObject);

    //verification
    expect(testUnit.getStoredLinkedObjects()).containsExactly(voidObject);
  }

  //method
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
    expect(testUnit.isVisible());
    expectNot(testUnit.hasMinWidth());
    expectNot(testUnit.hasMinHeight());
    expectNot(testUnit.hasMaxWidth());
    expectNot(testUnit.hasMaxHeight());
  }

  //method
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
