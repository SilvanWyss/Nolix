package ch.nolix.templatetest.webgui.style;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.container.base.IContainer;
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
import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.template.webgui.style.StyleCatalog;

/**
 * @author Silvan Wyss
 */
final class StyleCatalogTest extends StandardTest {
  @MethodSource
  private static IContainer<Arguments> getStyles() {
    return //
    ImmutableList.withElements(
      Arguments.of(StyleCatalog.DARK_EDGE_STYLE),
      Arguments.of(StyleCatalog.PARCHMENT_EDGE_STYLE));
  }

  @ParameterizedTest
  @MethodSource("getStyles")
  void testCase_styles(final IStyle testUnit) {
    //setup
    final var webGuiWithVariousContent = createWebGuiWithVariousContent();

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
