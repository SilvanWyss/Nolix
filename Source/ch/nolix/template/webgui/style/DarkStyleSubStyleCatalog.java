package ch.nolix.template.webgui.style;

import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webcontainercontrol.grid.Grid;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webcontainercontrol.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webcontainercontrol.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class DarkStyleSubStyleCatalog {
  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withAttachingAttributes("Background(Color(0x202020))");

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withSelectorRole(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes(
      "Background(Color(0x808080E0))",
      "ContentAlignment(CENTER)");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Control.class)
    .withAttachingAttributes("BaseTextColor(0xC0C0C0)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractContainer.class)
    .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes(
      "MinWidth(500)",
      "MinHeight(200)",
      "BaseBackground(Color(0x202020E0))",
      "BasePadding(20)");

  public static final ISelectingStyleWithSelectors FOOTER_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(HorizontalStack.class)
    .withSelectorRole(ContainerRole.FOOTER_CONTAINER)
    .withAttachingAttributes(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(100)",
      "BaseTextSize(15)");

  public static final ISelectingStyleWithSelectors GRID_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Grid.class)
    .withAttachingAttributes(
      "BaseChildControlMargin(10)",
      "BaseGridThickness(0)");

  public static final ISelectingStyleWithSelectors HEADER_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(HorizontalStack.class)
    .withSelectorRole(ContainerRole.HEADER_CONTAINER)
    .withAttachingAttributes(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(50)");

  public static final ISelectingStyleWithSelectors LINEAR_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractLinearContainer.class)
    .withAttachingAttributes("BaseChildControlMargin(10)");

  public static final ISelectingStyleWithSelectors MAIN_CONTENT_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
    .withAttachingAttributes("MinHeight(500)");

  public static final ISelectingStyleWithSelectors OVERALL_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(VerticalStack.class)
    .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
    .withAttachingAttributes(
      "BaseWidth(80%)",
      "MinHeight(80%)",
      "BasePadding(20)",
      "BaseChildControlMargin(20)");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  new DeepSelectingStyle()
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
  new DeepSelectingStyle()
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
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextSize(30)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Blue)",
      "HoverTextColor(DarkBlue)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Textbox.class)
    .withAttachingAttributes(
      "BaseWidth(200)",
      "BaseBackground(Color(Black))");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextSize(50)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Orange)");

  private DarkStyleSubStyleCatalog() {
  }
}
