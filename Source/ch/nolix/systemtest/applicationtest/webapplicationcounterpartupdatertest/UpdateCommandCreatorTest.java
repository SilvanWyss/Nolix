//package declaration
package ch.nolix.systemtest.applicationtest.webapplicationcounterpartupdatertest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.application.webapplicationcounterpartupdater.UpdateCommandCreator;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class UpdateCommandCreatorTest extends Test {

  //method
  @TestCase
  public void testCase_createSetCssCommandFromWebGui() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetCssCommandFromWebGui(webGui);

    //verification
    expect(result.toString()).matches("GUI.SetCSS(.*)");
  }

  //method
  @TestCase
  public void testCase_createSetTitleCommandForTitle() {

    //setup
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandForTitle("my_title");

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  //method
  @TestCase
  public void testCase_createSetTitleCommandFromWebGui() {

    //setup
    final var webGui = new WebGui().setTitle("my_title");
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  //method
  @TestCase
  public void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiIsEmpty() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetUserInputFunctions");
  }

  //method
  @TestCase
  public void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContainsAButton() {

    //setup
    final var button = new Button();
    final var webGui = new WebGui().pushLayerWithRootControl(button);
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetUserInputFunctions");
  }

  //method
  @TestCase
  public void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContaisATextbox() {

    //setup
    final var textbox = new Textbox();
    final var webGui = new WebGui().pushLayerWithRootControl(textbox);
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation(
      "GUI.SetUserInputFunctions((" + textbox.getInternalId() + ",return x$Dvalue;))");
  }
}
