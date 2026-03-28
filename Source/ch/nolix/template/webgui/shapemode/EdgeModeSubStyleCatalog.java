/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.shapemode;

import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webcontainercontrol.grid.Grid;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webcontainercontrol.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class EdgeModeSubStyleCatalog {
  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withSelectorRole(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("ContentAlignment(CENTER)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractContainer.class)
    .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes(
      "MinWidth(500)",
      "MinHeight(200)",
      "BasePadding(20)");

  public static final ISelectingStyleWithSelectors FOOTER_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(HorizontalStack.class)
    .withSelectorRole(ContainerRole.FOOTER_CONTAINER)
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
    .withSelectorRole(ContainerRole.HEADER_CONTAINER)
    .withAttachingAttributes(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(50)");

  public static final ISelectingStyleWithSelectors LINEAR_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractLinearContainer.class)
    .withAttachingAttributes("BaseChildControlMargin(10)");

  public static final ISelectingStyleWithSelectors MAIN_CONTENT_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
    .withAttachingAttributes("MinHeight(500)");

  public static final ISelectingStyleWithSelectors OVERALL_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(VerticalStack.class)
    .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
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
      "BaseLeftPadding(10)",
      "BaseRightPadding(10)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(DropdownMenu.class)
    .withAttachingAttributes(
      "MinWidth(200)",
      "BaseBorderThickness(1)",
      "BasePadding(2)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextSize(30)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Textbox.class)
    .withAttachingAttributes(
      "BaseWidth(200)",
      "BasePadding(2)");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttributes(
      "BaseBottomPadding(50)",
      "BaseTextSize(50)");

  private EdgeModeSubStyleCatalog() {
  }
}
