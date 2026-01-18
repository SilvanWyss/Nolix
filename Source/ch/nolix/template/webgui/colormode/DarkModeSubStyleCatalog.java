/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.colormode;

import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webcontainercontrol.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class DarkModeSubStyleCatalog {
  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withAttachingAttributes("Background(Color(0x202020))");

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withSelectorRole(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("Background(Color(0x808080E0))");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Control.class)
    .withAttachingAttributes("BaseTextColor(0xC0C0C0)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractContainer.class)
    .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes("BaseBackground(Color(0x202020E0))");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Button.class)
    .withAttachingAttributes(
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(DropdownMenu.class)
    .withAttachingAttributes(
      "BaseBackground(Color(0x202020))",
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Blue)",
      "HoverTextColor(DarkBlue)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Textbox.class)
    .withAttachingAttributes("BaseBackground(Color(Black))");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Red)");

  private DarkModeSubStyleCatalog() {
  }
}
