/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.colormode;

import ch.nolix.system.atomiccontrol.button.Button;
import ch.nolix.system.atomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.atomiccontrol.label.Label;
import ch.nolix.system.atomiccontrol.link.Link;
import ch.nolix.system.atomiccontrol.textbox.Textbox;
import ch.nolix.system.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.containercontrol.container.AbstractContainer;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.atomiccontrol.label.LabelRole;
import ch.nolix.systemapi.containercontrol.container.ContainerRole;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class DarkModeSubStyleCatalog {
  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAttachingAttributes("Background(Color(0x202020))");

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAdditionalSelectorRoles(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("Background(Color(0x808080E0))");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Control.class)
    .withAttachingAttributes("BaseTextColor(0xC0C0C0)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractContainer.class)
    .withAdditionalSelectorRoles(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes("BaseBackground(Color(0x202020E0))");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Button.class)
    .withAttachingAttributes(
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(DropdownMenu.class)
    .withAttachingAttributes(
      "BaseBackground(Color(0x202020))",
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Blue)",
      "HoverTextColor(DarkBlue)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Textbox.class)
    .withAttachingAttributes("BaseBackground(Color(Black))");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Red)");

  private DarkModeSubStyleCatalog() {
  }
}
