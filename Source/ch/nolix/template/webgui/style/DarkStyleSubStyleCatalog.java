/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.style;

import ch.nolix.system.atomiccontrol.button.Button;
import ch.nolix.system.atomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.atomiccontrol.label.Label;
import ch.nolix.system.atomiccontrol.link.Link;
import ch.nolix.system.atomiccontrol.textbox.Textbox;
import ch.nolix.system.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.containercontrol.container.AbstractContainer;
import ch.nolix.system.containercontrol.grid.Grid;
import ch.nolix.system.containercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.containercontrol.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.containercontrol.verticalstack.VerticalStack;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.atomiccontrol.label.LabelRole;
import ch.nolix.systemapi.containercontrol.container.ContainerRole;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class DarkStyleSubStyleCatalog {
  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAttachingAttributes("Background(Color(0x202020))");

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAdditionalSelectorRoles(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes(
      "Background(Color(0x808080E0))",
      "ContentAlignment(CENTER)");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractControl.class)
    .withAttachingAttributes("BaseTextColor(0xC0C0C0)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractContainer.class)
    .withAdditionalSelectorRoles(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes(
      "MinWidth(500)",
      "MinHeight(200)",
      "BaseBackground(Color(0x202020E0))",
      "BasePadding(20)");

  public static final ISelectingStyleWithSelectors FOOTER_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(HorizontalStack.class)
    .withAdditionalSelectorRoles(ContainerRole.FOOTER_CONTAINER)
    .withAttachingAttributes(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(100)",
      "BaseTextSize(15)");

  public static final ISelectingStyleWithSelectors GRID_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Grid.class)
    .withAttachingAttributes(
      "BaseChildControlMargin(10)",
      "BaseGridThickness(0)");

  public static final ISelectingStyleWithSelectors HEADER_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(HorizontalStack.class)
    .withAdditionalSelectorRoles(ContainerRole.HEADER_CONTAINER)
    .withAttachingAttributes(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(50)");

  public static final ISelectingStyleWithSelectors LINEAR_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractLinearContainer.class)
    .withAttachingAttributes("BaseChildControlMargin(10)");

  public static final ISelectingStyleWithSelectors MAIN_CONTENT_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withAdditionalSelectorRoles(ContainerRole.MAIN_CONTENT_CONTAINER)
    .withAttachingAttributes("MinHeight(500)");

  public static final ISelectingStyleWithSelectors OVERALL_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(VerticalStack.class)
    .withAdditionalSelectorRoles(ContainerRole.OVERALL_CONTAINER)
    .withAttachingAttributes(
      "BaseWidth(80%)",
      "MinHeight(80%)",
      "BasePadding(20)",
      "BaseChildControlMargin(20)");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Button.class)
    .withAttachingAttributes(
      "MinWidth(200)",
      "BaseBorderThickness(1)",
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseLeftPadding(10)",
      "BaseRightPadding(10)",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(DropdownMenu.class)
    .withAttachingAttributes(
      "MinWidth(200)",
      "BaseBorderThickness(1)",
      "BaseBorderColor(Grey)",
      "HoverBorderColor(White)",
      "BaseBackground(Color(0x202020))",
      "BaseTextColor(Grey)",
      "HoverTextColor(White)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextSize(30)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Blue)",
      "HoverTextColor(DarkBlue)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Textbox.class)
    .withAttachingAttributes(
      "BaseWidth(200)",
      "BaseBackground(Color(Black))");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextSize(50)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Orange)");

  private DarkStyleSubStyleCatalog() {
  }
}
