//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

//own imports
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.elementapi.styleapi.ISelectingStyleWithSelectors;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;

//class
final class ServerDashboardStyleCreator {

  //method
  public IStyle createServerDashboardStyle() {
    return //
    new Style()
      .withSubStyle(
        createLayerStyle(),
        createImageControlStyle(),
        createOverallVerticalStackContainerStyle(),
        createMainContentFloatContainerStyle(),
        createTitleLabelStyle(),
        createLevel1HeaderLabelStyle());
  }

  //method
  private ISelectingStyleWithSelectors createLayerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Layer.class)
      .withAttachingAttribute("ContentAlignment(TOP)");
  }

  //method
  private ISelectingStyleWithSelectors createImageControlStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(ImageControl.class)
      .withAttachingAttribute(
        "BaseWidth(250)",
        "BaseHeight(200)",
        "CursorIcon(Hand)",
        "BaseOpacity(50%)",
        "HoverOpacity(90%)");
  }

  //method
  private ISelectingStyleWithSelectors createOverallVerticalStackContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(VerticalStack.class)
      .withSelectorRole(ContainerRole.OVERALL_CONTAINER)
      .withAttachingAttribute(
        "ContentAlignment(CENTER)",
        "BaseChildControlMargin(50)",
        "BaseTextColor(0x202020)");
  }

  //method
  private ISelectingStyleWithSelectors createMainContentFloatContainerStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(FloatContainer.class)
      .withSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .withAttachingAttribute("BaseChildControlMargin(20)")
      .withSubStyle(
        new DeepSelectingStyle()
          .withSelectorType(VerticalStack.class)
          .withAttachingAttribute("BaseChildControlMargin(10)"));
  }

  //method
  private ISelectingStyleWithSelectors createTitleLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Label.class)
      .withSelectorRole(LabelRole.TITLE)
      .withAttachingAttribute("BaseTextSize(50)");
  }

  //method
  private ISelectingStyleWithSelectors createLevel1HeaderLabelStyle() {
    return new DeepSelectingStyle()
      .withSelectorType(Label.class)
      .withSelectorRole(LabelRole.LEVEL1_HEADER)
      .withAttachingAttribute("BaseTextSize(18)");
  }
}
