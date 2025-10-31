package ch.nolix.template.webapplication.session;

import ch.nolix.system.graphic.color.X11ColorCatalog;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.webapplication.main.WebClientSession;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.imagecontrol.ImageControl;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.systemapi.graphic.image.IImage;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.ControlState;

public final class UnderConstructionPageSession extends WebClientSession<Object> {
  private static final String CRANE_IMAGE_RESOURCE_PATH = "image/crane.jpg";

  private static final IImage CRANE_IMAGE = Image.fromResource(CRANE_IMAGE_RESOURCE_PATH);

  @Override
  protected void initialize() {
    getStoredGui()
      .pushLayerWithRootControl(
        new VerticalStack()
          .setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
          .addControl(
            new Label()
              .setRole(LabelRole.TITLE)
              .setText(getApplicationName()),
            new ImageControl()
              .setImage(CRANE_IMAGE)
              .editStyle(s -> s.setHeightForState(ControlState.BASE, 500)),
            new Label()
              .setText("This is page is under construction.")
              .editStyle(
                s -> s
                  .setTextSizeForState(ControlState.BASE, 30)
                  .setTextColorForState(ControlState.BASE, X11ColorCatalog.GREY)),
            new Button()
              .setVisibility(hasParentSession())
              .setText("<-- Go back")
              .setLeftMouseButtonPressAction(() -> pop()))
          .editStyle(s -> s.setChildControlMarginForState(ControlState.BASE, 20)));
  }
}
