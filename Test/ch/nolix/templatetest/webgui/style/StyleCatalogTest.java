/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.templatetest.webgui.style;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.system.control.floatcontainer.FloatContainer;
import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.imagecontrol.ImageControl;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.link.Link;
import ch.nolix.system.control.singlecontainer.SingleContainer;
import ch.nolix.system.control.textbox.Textbox;
import ch.nolix.system.control.validationlabel.ValidationLabel;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.system.webgui.main.WebGui;
import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.template.webgui.style.StyleCatalog;

/**
 * @author Silvan Wyss
 */
final class StyleCatalogTest extends StandardTest {
  @MethodSource
  private static ExtendedIterable<Arguments> getStyles() {
    return //
    ImmutableList.withElements(
      Arguments.of(StyleCatalog.DARK_EDGE_STYLE),
      Arguments.of(StyleCatalog.PARCHMENT_EDGE_STYLE));
  }

  @ParameterizedTest
  @MethodSource("getStyles")
  void testCase_styles(final IStyle testUnit) {
    // setup
    final var webGuiWithVariousContent = createWebGuiWithVariousContent();

    // execution & verification
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
