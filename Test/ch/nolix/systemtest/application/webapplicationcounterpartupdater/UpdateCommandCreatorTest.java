/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.application.webapplicationcounterpartupdater;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webapplication.counterpartupdater.UpdateCommandCreator;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.main.WebGui;

/**
 * @author Silvan Wyss
 */
final class UpdateCommandCreatorTest extends StandardTest {
  @Test
  void testCase_createSetCssCommandFromWebGui() {
    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetCssCommandForWebGui(webGui);

    //verification
    expect(result.toString()).matches("GUI.SetCSS(.*)");
  }

  @Test
  void testCase_createSetTitleCommandForTitle() {
    //setup
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandForTitle("my_title");

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  @Test
  void testCase_createSetTitleCommandFromWebGui() {
    //setup
    final var webGui = new WebGui().setTitle("my_title");
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandForWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiIsEmpty() {
    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandForWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetUserInputFunctions");
  }

  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContainsAButton() {
    //setup
    final var button = new Button();
    final var webGui = new WebGui().pushLayerWithRootControl(button);
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandForWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetUserInputFunctions");
  }

  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContaisATextbox() {
    //setup
    final var textbox = new Textbox();
    final var webGui = new WebGui().pushLayerWithRootControl(textbox);
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandForWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation(
      "GUI.SetUserInputFunctions((" + textbox.getInternalId() + ",return x$Dvalue;))");
  }
}
