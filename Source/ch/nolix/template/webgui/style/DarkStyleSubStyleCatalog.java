package ch.nolix.template.webgui.style;

import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webgui.atomiccontrol.button.Button;
import ch.nolix.system.webgui.atomiccontrol.label.Label;
import ch.nolix.system.webgui.atomiccontrol.link.Link;
import ch.nolix.system.webgui.atomiccontrol.textbox.Textbox;
import ch.nolix.system.webgui.atomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webgui.basecontainer.AbstractContainer;
import ch.nolix.system.webgui.container.grid.Grid;
import ch.nolix.system.webgui.itemmenu.dropdownmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.AbstractLinearContainer;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webgui.atomiccontrol.labelapi.LabelRole;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.LayerRole;

public final class DarkStyleSubStyleCatalog {
  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withAttachingAttribute("Background(Color(0x202020))");

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withSelectorRole(LayerRole.DIALOG_LAYER)
    .withAttachingAttribute(
      "Background(Color(0x808080E0))",
      "ContentAlignment(CENTER)");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Control.class)
    .withAttachingAttribute("BaseTextColor(0xC0C0C0)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractContainer.class)
    .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttribute(
      "MinWidth(500)",
      "MinHeight(200)",
      "BaseBackground(Color(0x202020E0))",
      "BasePadding(20)");

  public static final ISelectingStyleWithSelectors FOOTER_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(HorizontalStack.class)
    .withSelectorRole(ContainerRole.FOOTER_CONTAINER)
    .withAttachingAttribute(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(100)",
      "BaseTextSize(15)");

  public static final ISelectingStyleWithSelectors GRID_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Grid.class)
    .withAttachingAttribute(
      "BaseChildControlMargin(10)",
      "BaseGridThickness(0)");

  public static final ISelectingStyleWithSelectors HEADER_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(HorizontalStack.class)
    .withSelectorRole(ContainerRole.HEADER_CONTAINER)
    .withAttachingAttribute(
      "ContentAlignment(BOTTOM)",
      "BaseChildControlMargin(50)");

  public static final ISelectingStyleWithSelectors LINEAR_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractLinearContainer.class)
    .withAttachingAttribute("BaseChildControlMargin(10)");

  public static final ISelectingStyleWithSelectors MAIN_CONTENT_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
    .withAttachingAttribute("MinHeight(500)");

  public static final ISelectingStyleWithSelectors OVERALL_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(VerticalStack.class)
    .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
    .withAttachingAttribute(
      "BaseWidth(80%)",
      "MinHeight(80%)",
      "BasePadding(20)",
      "BaseChildControlMargin(20)");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Button.class)
    .withAttachingAttribute(
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
    .withAttachingAttribute(
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
    .withAttachingAttribute("BaseTextSize(30)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Link.class)
    .withAttachingAttribute(
      "BaseTextColor(Blue)",
      "HoverTextColor(DarkBlue)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Textbox.class)
    .withAttachingAttribute(
      "BaseWidth(200)",
      "BaseBackground(Color(Black))");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttribute("BaseTextSize(50)", "BaseTextColor(White)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttribute("BaseTextColor(Orange)");

  private DarkStyleSubStyleCatalog() {
  }
}
