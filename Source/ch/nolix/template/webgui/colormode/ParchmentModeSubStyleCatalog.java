package ch.nolix.template.webgui.colormode;

import ch.nolix.core.document.node.Node;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.gui.box.CornerShadow;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.dropdownmenu.DropdownMenu;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webatomiccontrol.link.Link;
import ch.nolix.system.webatomiccontrol.textbox.Textbox;
import ch.nolix.system.webatomiccontrol.validationlabel.ValidationLabel;
import ch.nolix.system.webcontainercontrol.container.AbstractContainer;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.gui.background.ImageApplication;
import ch.nolix.systemapi.gui.box.Corner;
import ch.nolix.systemapi.gui.location.Location;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webcontainercontrol.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class ParchmentModeSubStyleCatalog {
  private static final Image PARCHMENT_IMAGE = Image.fromResource("image/parchment_paper.jpg");

  private static final Background PARCHMENT_BACKGROUND = //
  Background.withImageAndImageApplication(PARCHMENT_IMAGE, ImageApplication.SCALE_TO_FRAME);

  private static final Node PARCHMENT_BACKGROUND_SPECIFICATION = Node.fromNode(PARCHMENT_BACKGROUND.getSpecification());

  private static final String PARCHMENT_BACKGROUND_SPECIFICATION_STRING = PARCHMENT_BACKGROUND_SPECIFICATION.toString();

  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withAttachingAttributes(PARCHMENT_BACKGROUND_SPECIFICATION_STRING);

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withSelectorRole(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("Background(Color(0x80808080))");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Control.class)
    .withAttachingAttributes("BaseTextColor(Brown)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(AbstractContainer.class)
    .withSelectorRole(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes("BaseBackground(Color(0x202020E0))");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Button.class)
    .withAttachingAttributes(
      Node.withHeaderAndChildNode("BaseCornerShadows",
        CornerShadow.withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(Corner.BOTTOM_RIGHT,
          Location.OUTSIDE, 5, 5, 5, X11ColorCatalog.BROWN).getSpecification())
        .toString(),
      Node.withHeaderAndChildNode("HoverCornerShadows",
        CornerShadow.withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(Corner.BOTTOM_RIGHT,
          Location.OUTSIDE, 5, 5, 5, X11ColorCatalog.BLACK).getSpecification())
        .toString(),
      "BaseBorderColor(Brown)",
      "HoverBorderColor(Black)",
      "BaseBackground(Color(0xFFFFFF80))",
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(DropdownMenu.class)
    .withAttachingAttributes(
      "BaseBackground(Color(0xFFFFFF80))",
      "BaseBorderColor(Brown)",
      "HoverBorderColor(Black)",
      "FocusBorderColor(Black)",
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)",
      "FocusTextColor(Black)");

  public static final ISelectingStyleWithSelectors IMAGE_CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ImageControl.class)
    .withAttachingAttributes(
      "BaseBorderThickness(1)",
      "BaseBorderColor(Brown)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextColor(Black)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Textbox.class)
    .withAttachingAttributes(
      "BaseBorderThickness(1)",
      "BaseBorderColor(Brown)",
      "HoverBorderColor(Black)",
      "FocusBorderColor(Black)",
      "BaseBackground(Color(0xFFFFFF80))",
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)",
      "FocusTextColor(Black)");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextColor(Black)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Red)");

  private ParchmentModeSubStyleCatalog() {
  }
}
