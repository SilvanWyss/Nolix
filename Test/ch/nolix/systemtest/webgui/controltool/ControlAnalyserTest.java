package ch.nolix.systemtest.webgui.controltool;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.atomiccontrol.link.Link;
import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.controltool.ControlAnalyser;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;

final class ControlAnalyserTest extends StandardTest {
  @Test
  void testCase_firstControlContainsSecondControl_whenTheGivenFirstControlIsNull() {
    //setup
    final var testUnit = new ControlAnalyser();

    //execution
    final var result = testUnit.firstControlContainsSecondControl(null, new Label());

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_firstControlContainsSecondControl_whenTheGivenSecondControlIsNull() {
    //setup
    final var testUnit = new ControlAnalyser();

    //execution
    final var result = testUnit.firstControlContainsSecondControl(new Label(), null);

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_firstControlContainsSecondControl_whenTheGivenFirstControlContainsTheGivenSecondControlDirectly() {
    //setup
    final var label = new Label();
    final var verticalStack = new VerticalStack().addControl(new Button(), new Link(), label);
    final var testUnit = new ControlAnalyser();

    //execution
    final var result = testUnit.firstControlContainsSecondControl(verticalStack, label);

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_firstControlContainsSecondControl_whenTheGivenFirstControlContainsTheGivenSecondControlRecursively() {
    //setup
    final var label = new Label();
    final var verticalStack = //
    new VerticalStack()
      .addControl(
        new Button(),
        new Link(),
        new FloatContainer()
          .addControl(
            new Textbox(),
            new ImageControl(),
            label));
    final var testUnit = new ControlAnalyser();

    //execution
    final var result = testUnit.firstControlContainsSecondControl(verticalStack, label);

    //verification
    expect(result).isTrue();
  }
}
