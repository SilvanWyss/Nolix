//package declaration
package ch.nolix.systemtest.applicationtest.webapplicationcounterpartupdatertest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.application.webapplicationcounterpartupdater.UpdateCommandCreator;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.main.WebGui;

//class
final class UpdateCommandCreatorTest extends StandardTest {

  //method
  @Test
  void testCase_createSetCssCommandFromWebGui() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetCssCommandFromWebGui(webGui);

    //verification
    expect(result.toString()).matches("GUI.SetCSS(.*)");
  }

  //method
  @Test
  void testCase_createSetTitleCommandForTitle() {

    //setup
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandForTitle("my_title");

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  //method
  @Test
  void testCase_createSetTitleCommandFromWebGui() {

    //setup
    final var webGui = new WebGui().setTitle("my_title");
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetTitleCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetTitle(my_title)");
  }

  //method
  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiIsEmpty() {

    //setup
    final var webGui = new WebGui();
    final var testUnit = new UpdateCommandCreator();

    //execution
    final var result = testUnit.createSetUserInputFunctionsCommandFromWebGui(webGui);

    //verification
    expect(result).hasStringRepresentation("GUI.SetUserInputFunctions");
  }

  //method
  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContainsAButton() {

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
  @Test
  void testCase_createSetUserInputFunctionsCommandFromWebGui_whenTheGivenWebGuiContaisATextbox() {

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
