package ch.nolix.systemtest.webgui.basecontroltool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class ControlCssBuilderTest<B extends IControlCssBuilder<C, ?>, C extends IControl<C, ?>>
extends StandardTest {

  protected abstract C createControl();

  protected abstract B createTestUnit();

  @Test
  final void testCase_createCssRulesForControl_whenGivenControlHasAHoverBackgroundColor() {

    //setup
    final var control = createControl();
    control.editStyle(s -> s.setBackgroundColorForState(ControlState.HOVER, Color.fromString("0x102030")));
    final B testUnit = createTestUnit();

    //execution
    final var result = testUnit.createCssRulesForControl(control);

    //verification
    final var controlInternalId = control.getInternalId();
    final var hoverCssRule = result.getStoredOne(r -> r.getSelector().startsWith("#" + controlInternalId + ":hover"));
    expect(hoverCssRule.getProperties()).containsExactlyOneWithStringRepresentation("background: #102030;");
  }

  @Test
  final void testCase_createCssRulesForControl_whenGivenControlIsNew() {

    //setup
    final var control = createControl();
    final var controlInternalId = control.getInternalId();
    final B testUnit = createTestUnit();

    //execution
    final var result = testUnit.createCssRulesForControl(control);

    //verification part 1
    expect(result).hasElementCount(4);
    final var cssRuleForAllStates = result.getStoredFirst(r -> r.getSelector().startsWith("#" + controlInternalId));
    expect(cssRuleForAllStates.getProperties().containsAny(p -> p.hasName("cursor"))).isTrue();

    //verification part 2
    expect(result.containsAny(r -> r.getSelector().startsWith("#" + controlInternalId + ":hover"))).isTrue();

    //verification part 3
    expect(result.containsAny(r -> r.getSelector().startsWith("#" + controlInternalId + ":focus"))).isTrue();
  }
}
