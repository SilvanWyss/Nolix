//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

//own imports
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.stylebuilder.DeepSelectingStyleBuilder;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
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
  private DeepSelectingStyle createLayerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Layer.class)
      .addAttachingAttribute("ContentAlignment(TOP)")
      .build();
  }

  //method
  private DeepSelectingStyle createImageControlStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(ImageControl.class)
      .addAttachingAttribute(
        "BaseWidth(250)",
        "BaseHeight(200)",
        "CursorIcon(Hand)",
        "BaseOpacity(50%)",
        "HoverOpacity(90%)")
      .build();
  }

  //method
  private DeepSelectingStyle createOverallVerticalStackContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(VerticalStack.class)
      .addSelectorRole(ContainerRole.OVERALL_CONTAINER)
      .addAttachingAttribute(
        "ContentAlignment(CENTER)",
        "BaseChildControlMargin(50)",
        "BaseTextColor(0x202020)")
      .build();
  }

  //method
  private DeepSelectingStyle createMainContentFloatContainerStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(FloatContainer.class)
      .addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
      .addAttachingAttribute("BaseChildControlMargin(20)")
      .addSubStyle(
        new DeepSelectingStyleBuilder()
          .setSelectorType(VerticalStack.class)
          .addAttachingAttribute("BaseChildControlMargin(10)")
          .build())
      .build();
  }

  //method
  private DeepSelectingStyle createTitleLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Label.class)
      .addSelectorRole(LabelRole.TITLE)
      .addAttachingAttribute("BaseTextSize(50)")
      .build();
  }

  //method
  private DeepSelectingStyle createLevel1HeaderLabelStyle() {
    return new DeepSelectingStyleBuilder()
      .setSelectorType(Label.class)
      .addSelectorRole(LabelRole.LEVEL1_HEADER)
      .addAttachingAttribute("BaseTextSize(18)")
      .build();
  }
}
