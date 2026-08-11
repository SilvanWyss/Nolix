/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.colormode;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.control.dropdownmenu.DropdownMenu;
import ch.nolix.system.control.imagecontrol.ImageControl;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.link.Link;
import ch.nolix.system.control.textbox.Textbox;
import ch.nolix.system.control.validationlabel.ValidationLabel;
import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.ImmutableImage;
import ch.nolix.system.gui.background.Background;
import ch.nolix.system.gui.box.CornerShadow;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.control.container.ContainerRole;
import ch.nolix.systemapi.control.label.LabelRole;
import ch.nolix.systemapi.gui.guiproperty.Corner;
import ch.nolix.systemapi.gui.guiproperty.ImageApplication;
import ch.nolix.systemapi.gui.guiproperty.Location;
import ch.nolix.systemapi.style.model.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class ParchmentModeSubStyleCatalog {
  private static final ImmutableImage PARCHMENT_IMAGE = ImmutableImage.fromResource("image/parchment_paper.jpg");

  private static final Background PARCHMENT_BACKGROUND = //
  Background.withImageAndImageApplication(PARCHMENT_IMAGE, ImageApplication.SCALE_TO_FRAME);

  private static final ImmutableNode PARCHMENT_BACKGROUND_SPECIFICATION = //
  ImmutableNode.fromNode(PARCHMENT_BACKGROUND.getSpecification());

  private static final String PARCHMENT_BACKGROUND_SPECIFICATION_STRING = PARCHMENT_BACKGROUND_SPECIFICATION.toString();

  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAttachingAttributes(PARCHMENT_BACKGROUND_SPECIFICATION_STRING);

  public static final ISelectingStyleWithSelectors DIALOG_LAYER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Layer.class)
    .withAdditionalSelectorRoles(LayerRole.DIALOG_LAYER)
    .withAttachingAttributes("Background(Color(0x80808080))");

  public static final ISelectingStyleWithSelectors CONTROL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractControl.class)
    .withAttachingAttributes("BaseTextColor(Brown)");

  public static final ISelectingStyleWithSelectors DIALOG_CONTAINER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(AbstractContainer.class)
    .withAdditionalSelectorRoles(ContainerRole.DIALOG_CONTAINER)
    .withAttachingAttributes("BaseBackground(Color(0x202020E0))");

  public static final ISelectingStyleWithSelectors BUTTON_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Button.class)
    .withAttachingAttributes(
      ImmutableNode.withHeaderAndChildNode("BaseCornerShadows",
        CornerShadow.withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(Corner.BOTTOM_RIGHT,
          Location.OUTSIDE, 5, 5, 5, X11ColorCatalog.BROWN).getSpecification())
        .toString(),
      ImmutableNode.withHeaderAndChildNode("HoverCornerShadows",
        CornerShadow.withCornerAndLocationAndSide1ThicknessAnsSide2ThicknessAndBlurRadiusAndColor(Corner.BOTTOM_RIGHT,
          Location.OUTSIDE, 5, 5, 5, X11ColorCatalog.BLACK).getSpecification())
        .toString(),
      "BaseBorderColor(Brown)",
      "HoverBorderColor(Black)",
      "BaseBackground(Color(0xFFFFFF80))",
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)");

  public static final ISelectingStyleWithSelectors DROPDOWN_MENU_STYLE = //
  DeepSelectingStyle.EMPTY
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
  DeepSelectingStyle.EMPTY
    .withSelectorType(ImageControl.class)
    .withAttachingAttributes(
      "BaseBorderThickness(1)",
      "BaseBorderColor(Brown)");

  public static final ISelectingStyleWithSelectors LEVEL1_HEADER_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.LEVEL1_HEADER)
    .withAttachingAttributes("BaseTextColor(Black)");

  public static final ISelectingStyleWithSelectors LINK_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(Link.class)
    .withAttachingAttributes(
      "BaseTextColor(Brown)",
      "HoverTextColor(Black)");

  public static final ISelectingStyleWithSelectors TEXT_BOX_STYLE = //
  DeepSelectingStyle.EMPTY
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
  DeepSelectingStyle.EMPTY
    .withSelectorType(Label.class)
    .withAdditionalSelectorRoles(LabelRole.TITLE)
    .withAttachingAttributes("BaseTextColor(Black)");

  public static final ISelectingStyleWithSelectors VALIDATION_LABEL_STYLE = //
  DeepSelectingStyle.EMPTY
    .withSelectorType(ValidationLabel.class)
    .withAttachingAttributes("BaseTextColor(Red)");

  private ParchmentModeSubStyleCatalog() {
  }
}
