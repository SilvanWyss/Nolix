//package declaration
package ch.nolix.systemtest.webguitest.mainvalidatortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.system.webgui.mainvalidator.LayerValidator;

//class
final class LayerValidatorTest extends StandardTest {

  //method
  @Test
  void testCase_assertBelongsToGui_whenTheGivenLayerDoesNotBelongToAGui() {

    //setup
    final var layer = new Layer();
    final var testUnit = new LayerValidator();

    //setup verification
    expectNot(layer.belongsToGui());

    //execution & verification
    expectRunning(() -> testUnit.assertBelongsToGui(layer))
      .throwsException()
      .ofType(ArgumentDoesNotBelongToParentException.class);
  }

  //method
  @Test
  void testCase_assertBelongsToGui_whenTheGivenLayerBelongsToAGui() {

    //setup
    final var layer = new Layer();
    new WebGui().pushLayer(layer);
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui());

    //execution & verification
    expectRunning(() -> testUnit.assertBelongsToGui(layer)).doesNotThrowException();
  }

  //method
  @Test
  void testCase_assertDoesNotBelongsToGui_whenTheGivenLayerDoesNotBelongToAGui() {

    //setup
    final var layer = new Layer();
    final var testUnit = new LayerValidator();

    //setup verification
    expectNot(layer.belongsToGui());

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotBelongToGui(layer)).doesNotThrowException();
  }

  //method
  @Test
  void testCase_assertDoesNotBelongToGui_whenTheGivenLayerBelongsToAGui() {

    //setup
    final var layer = new Layer();
    new WebGui().pushLayer(layer);
    final var testUnit = new LayerValidator();

    //setup verification
    expect(layer.belongsToGui());

    //execution & verification
    expectRunning(() -> testUnit.assertDoesNotBelongToGui(layer))
      .throwsException()
      .ofType(ArgumentBelongsToParentException.class);
  }
}
