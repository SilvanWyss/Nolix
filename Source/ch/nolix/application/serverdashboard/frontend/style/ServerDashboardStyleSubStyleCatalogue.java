package ch.nolix.application.serverdashboard.frontend.style;

import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

public final class ServerDashboardStyleSubStyleCatalogue {

  public static final ISelectingStyleWithSelectors LAYER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Layer.class)
    .withAttachingAttribute("ContentAlignment(TOP)");

  public static final ISelectingStyleWithSelectors MAIN_CONTENT_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(FloatContainer.class)
    .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
    .withAttachingAttribute("BaseChildControlMargin(20)")
    .withSubStyle(
      new DeepSelectingStyle()
        .withSelectorType(VerticalStack.class)
        .withAttachingAttribute("BaseChildControlMargin(10)"));

  public static final ISelectingStyleWithSelectors OVERALL_CONTAINER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(VerticalStack.class)
    .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
    .withAttachingAttribute(
      "ContentAlignment(CENTER)",
      "BaseChildControlMargin(50)",
      "BaseTextColor(0x202020)");

  public static final ISelectingStyleWithSelectors IMAGE_CONTROL_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(ImageControl.class)
    .withAttachingAttribute(
      "BaseWidth(250)",
      "BaseHeight(200)",
      "CursorIcon(Hand)",
      "BaseOpacity(50%)",
      "HoverOpacity(90%)");

  public static final ISelectingStyleWithSelectors LEVEL_1_HEADER_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.LEVEL1_HEADER)
    .withAttachingAttribute("BaseTextSize(18)");

  public static final ISelectingStyleWithSelectors TITLE_STYLE = //
  new DeepSelectingStyle()
    .withSelectorType(Label.class)
    .withSelectorRole(LabelRole.TITLE)
    .withAttachingAttribute("BaseTextSize(50)");

  private ServerDashboardStyleSubStyleCatalogue() {
  }
}
