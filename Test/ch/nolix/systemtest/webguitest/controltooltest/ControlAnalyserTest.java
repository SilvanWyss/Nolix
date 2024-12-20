package ch.nolix.systemtest.webguitest.controltooltest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
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
    expectNot(result);
  }

  @Test
  void testCase_firstControlContainsSecondControl_whenTheGivenSecondControlIsNull() {

    //setup
    final var testUnit = new ControlAnalyser();

    //execution
    final var result = testUnit.firstControlContainsSecondControl(new Label(), null);

    //verification
    expectNot(result);
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
    expect(result);
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
    expect(result);
  }
}
