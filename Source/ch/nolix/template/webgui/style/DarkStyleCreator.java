//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.stylebuilder.DeepSelectingStyleBuilder;
import ch.nolix.system.element.stylebuilder.StyleBuilder;
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
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
final class DarkStyleCreator {

  //method
  public Style createDarkStyle() {
    return new StyleBuilder()
      .addSubStyle(
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
        createDialogContainerStyle())
      .build();
  }

  //method
  private DeepSelectingStyle createLayerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Layer.class)
      .addAttachingAttribute("Background(Color(0x202020))")
      .build();
  }

  private DeepSelectingStyle createControlStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Control.class)
      .addAttachingAttribute("BaseTextColor(0xC0C0C0)")
      .build();
  }

  //method
  private DeepSelectingStyle createLinearContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(LinearContainer.class)
      .addAttachingAttribute("BaseChildControlMargin(10)")
      .build();
  }

  //method
  private DeepSelectingStyle createGridStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Grid.class)
      .addAttachingAttribute(
        "BaseChildControlMargin(10)",
        "BaseGridThickness(0)")
      .build();
  }

  //method
  private DeepSelectingStyle createLinkStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Link.class)
      .addAttachingAttribute(
        "BaseTextColor(Blue)",
        "HoverTextColor(DarkBlue)")
      .build();
  }

  //method
  private DeepSelectingStyle createButtonStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Button.class)
      .addAttachingAttribute(
        "MinWidth(200)",
        "BaseBorderThickness(1)",
        "BaseBorderColor(Grey)",
        "HoverBorderColor(White)",
        "BaseLeftPadding(10)",
        "BaseRightPadding(10)",
        "BaseTextColor(Grey)",
        "HoverTextColor(White)")
      .build();
  }

  //method
  private DeepSelectingStyle createTextboxStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Textbox.class)
      .addAttachingAttribute(
        "BaseWidth(200)",
        "BaseBackground(Color(Black))")
      .build();
  }

  //method
  private DeepSelectingStyle createDropdownMenuStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(DropdownMenu.class)
      .addAttachingAttribute(
        "MinWidth(200)",
        "BaseBorderThickness(1)",
        "BaseBorderColor(Grey)",
        "HoverBorderColor(White)",
        "BaseBackground(Color(0x202020))",
        "BaseTextColor(Grey)",
        "HoverTextColor(White)")
      .build();
  }

  //method
  private DeepSelectingStyle createValidationLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(ValidationLabel.class)
      .addAttachingAttribute("BaseTextColor(Orange)")
      .build();
  }

  //method
  private DeepSelectingStyle createOverallVerticalStackStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(VerticalStack.class)
      .addSelectorRole(ContainerRole.OVERALL_CONTAINER)
      .addAttachingAttribute(
        "BaseWidth(80%)",
        "MinHeight(80%)",
        "BasePadding(20)",
        "BaseChildControlMargin(20)")
      .build();
  }

  //method
  private DeepSelectingStyle createHeaderHorizontalStackStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(HorizontalStack.class)
      .addSelectorRole(ContainerRole.HEADER_CONTAINER)
      .addAttachingAttribute(
        "ContentAlignment(BOTTOM)",
        "BaseChildControlMargin(50)")
      .build();
  }

  //method
  private DeepSelectingStyle createMainContentContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .addAttachingAttribute("MinHeight(500)")
      .build();
  }

  //method
  private DeepSelectingStyle createFooterHorizontalStackStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(HorizontalStack.class)
      .addSelectorRole(ContainerRole.FOOTER_CONTAINER)
      .addAttachingAttribute(
        "ContentAlignment(BOTTOM)",
        "BaseChildControlMargin(100)",
        "BaseTextSize(15)")
      .build();
  }

  //method
  private DeepSelectingStyle createTitleLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Label.class)
      .addSelectorRole(LabelRole.TITLE)
      .addAttachingAttribute("BaseTextSize(50)", "BaseTextColor(White)")
      .build();
  }

  //method
  private DeepSelectingStyle createLevel1HeaderLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Label.class)
      .addSelectorRole(LabelRole.LEVEL1_HEADER)
      .addAttachingAttribute("BaseTextSize(30)", "BaseTextColor(White)")
      .build();
  }

  //method
  private DeepSelectingStyle createDialogLayerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Layer.class)
      .addSelectorRole(LayerRole.DIALOG_LAYER)
      .addAttachingAttribute(
        "Background(Color(0x808080E0))",
        "ContentAlignment(CENTER)")
      .build();
  }

  //method
  private DeepSelectingStyle createDialogContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Container.class)
      .addSelectorRole(ContainerRole.DIALOG_CONTAINER)
      .addAttachingAttribute(
        "MinWidth(500)",
        "MinHeight(200)",
        "BaseBackground(Color(0x202020E0))",
        "BasePadding(20)")
      .build();
  }
}
