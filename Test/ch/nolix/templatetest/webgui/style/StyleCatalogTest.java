package ch.nolix.templatetest.webgui.style;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webcontainercontrol.floatcontainer.FloatContainer;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webcontainercontrol.singlecontainer.SingleContainer;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.template.webgui.style.StyleCatalog;

final class StyleCatalogTest extends StandardTest {
  @Test
  void testCase_DarkStyle() {
    //setup
    final var webGuiWithVariousContent = createWebGuiWithVariousContent();
    final var testUnit = StyleCatalog.DARK_STYLE;

    //execution & verification
    expectRunning(() -> testUnit.applyToElement(webGuiWithVariousContent)).doesNotThrowException();
  }

  private WebGui createWebGuiWithVariousContent() {
    return new WebGui()
      .pushLayer(
        new Layer()
          .setRootControl(
            new VerticalStack()
              .addControls(
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
