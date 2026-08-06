/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.shapemode;

import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.system.control.grid.Grid;
import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.control.textbox.Textbox;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.control.container.ContainerRole;
import ch.nolix.systemapi.control.label.LabelRole;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * Of the {@link EdgeModeSubStyleCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class EdgeModeSubStyleCatalog {
  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAdditionalSelectorRoles(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("ContentAlignment(CENTER)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractContainer.class)
    .withAdditionalSelectorRoles(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes(
      "MinWidth(500)",
      "MinHeight(200)",
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
    .withAdditionalSelectorRoles(LabelRole.LEVEL1_HEADER)
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
    .withAdditionalSelectorRoles(LabelRole.TITLE)
    .withAttachingAttributes(
      "BaseBottomPadding(50)",
      "BaseTextSize(50)");

  private EdgeModeSubStyleCatalog() {
  }
}
