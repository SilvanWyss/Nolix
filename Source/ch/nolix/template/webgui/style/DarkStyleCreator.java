//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.basecontainer.Container;
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.LinearContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
final class DarkStyleCreator {

  //method
  public IStyle createDarkStyle() {
    return //
    new Style()
      .withSubStyle(
        createLayerStyle(),
        createControlStyle(),
        createLinearContainerStyle(),
        createGridStyle(),
        createLinkStyle(),
        createButtonStyle(),
        createTextboxStyle(),
        createDropdownMenuStyle(),
        createValidationLabelStyle(),
        createOverallVerticalStackStyle(),
        createHeaderHorizontalStackStyle(),
        createMainContentContainerStyle(),
        createFooterHorizontalStackStyle(),
        createTitleLabelStyle(),
        createLevel1HeaderLabelStyle(),
        createDialogLayerStyle(),
        createDialogContainerStyle());
  }

  //method
  private ISelectingStyleWithSelectors createLayerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Layer.class)
      .withAttachingAttribute("Background(Color(0x202020))");
  }

  private ISelectingStyleWithSelectors createControlStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Control.class)
      .withAttachingAttribute("BaseTextColor(0xC0C0C0)");
  }

  //method
  private ISelectingStyleWithSelectors createLinearContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(LinearContainer.class)
      .withAttachingAttribute("BaseChildControlMargin(10)");
  }

  //method
  private ISelectingStyleWithSelectors createGridStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Grid.class)
      .withAttachingAttribute(
        "BaseChildControlMargin(10)",
        "BaseGridThickness(0)");
  }

  //method
  private ISelectingStyleWithSelectors createLinkStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Link.class)
      .withAttachingAttribute(
        "BaseTextColor(Blue)",
        "HoverTextColor(DarkBlue)");
  }

  //method
  private ISelectingStyleWithSelectors createButtonStyle() {
    return new DeepSelectingStyle()
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
  }

  //method
  private ISelectingStyleWithSelectors createTextboxStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Textbox.class)
      .withAttachingAttribute(
        "BaseWidth(200)",
        "BaseBackground(Color(Black))");
  }

  //method
  private ISelectingStyleWithSelectors createDropdownMenuStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(DropdownMenu.class)
      .withAttachingAttribute(
        "MinWidth(200)",
        "BaseBorderThickness(1)",
        "BaseBorderColor(Grey)",
        "HoverBorderColor(White)",
        "BaseBackground(Color(0x202020))",
        "BaseTextColor(Grey)",
        "HoverTextColor(White)");
  }

  //method
  private ISelectingStyleWithSelectors createValidationLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(ValidationLabel.class)
      .withAttachingAttribute("BaseTextColor(Orange)");
  }

  //method
  private ISelectingStyleWithSelectors createOverallVerticalStackStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(VerticalStack.class)
      .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
      .withAttachingAttribute(
        "BaseWidth(80%)",
        "MinHeight(80%)",
        "BasePadding(20)",
        "BaseChildControlMargin(20)");
  }

  //method
  private ISelectingStyleWithSelectors createHeaderHorizontalStackStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(HorizontalStack.class)
      .withSelectorRole(ContainerRole.HEADER_CONTAINER)
      .withAttachingAttribute(
        "ContentAlignment(BOTTOM)",
        "BaseChildControlMargin(50)");
  }

  //method
  private ISelectingStyleWithSelectors createMainContentContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .withAttachingAttribute("MinHeight(500)");
  }

  //method
  private ISelectingStyleWithSelectors createFooterHorizontalStackStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(HorizontalStack.class)
      .withSelectorRole(ContainerRole.FOOTER_CONTAINER)
      .withAttachingAttribute(
        "ContentAlignment(BOTTOM)",
        "BaseChildControlMargin(100)",
        "BaseTextSize(15)");
  }

  //method
  private ISelectingStyleWithSelectors createTitleLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Label.class)
      .withSelectorRole(LabelRole.TITLE)
      .withAttachingAttribute("BaseTextSize(50)", "BaseTextColor(White)");
  }

  //method
  private ISelectingStyleWithSelectors createLevel1HeaderLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Label.class)
      .withSelectorRole(LabelRole.LEVEL1_HEADER)
      .withAttachingAttribute("BaseTextSize(30)", "BaseTextColor(White)");
  }

  //method
  private ISelectingStyleWithSelectors createDialogLayerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Layer.class)
      .withSelectorRole(LayerRole.DIALOG_LAYER)
      .withAttachingAttribute(
        "Background(Color(0x808080E0))",
        "ContentAlignment(CENTER)");
  }

  //method
  private ISelectingStyleWithSelectors createDialogContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Container.class)
      .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
      .withAttachingAttribute(
        "MinWidth(500)",
        "MinHeight(200)",
        "BaseBackground(Color(0x202020E0))",
        "BasePadding(20)");
  }
}
