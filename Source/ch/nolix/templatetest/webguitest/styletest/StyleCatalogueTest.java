//package declaration
package ch.nolix.templatetest.webguitest.styletest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.container.SingleContainer;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.template.webgui.style.StyleCatalogue;

//class
public final class StyleCatalogueTest extends Test {

  //method
  @TestCase
  public void testCase_DarkStyle() {

    //setup
    final var webGuiWithVariousContent = createWebGuiWithVariousContent();
    final var testUnit = StyleCatalogue.DARK_STYLE;

    //execution & verification
    expectRunning(() -> testUnit.styleElement(webGuiWithVariousContent)).doesNotThrowException();
  }

  //method
  private WebGui createWebGuiWithVariousContent() {
    return new WebGui()
      .pushLayer(
        new Layer()
          .setRootControl(
            new VerticalStack()
              .addControl(
                new Button(),
                new DropdownMenu(),
                new FloatContainer(),
                new HorizontalStack(),
                new ImageControl(),
                new Label(),
                new Link(),
                new SingleContainer(),
                new Textbox(),
                new ValidationLabel(),
                new VerticalStack())));
  }
}
