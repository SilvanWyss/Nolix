package ch.nolix.template.webgui.dialog;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.coreapi.structurecontrolapi.builderapi.IBuilder;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

public final class YesNoDialogBuilder implements IBuilder<ILayer<?>> {

  private static final String DEFAULT_YES_NO_QUESTION = "Do you want to run the action?";

  private static final Runnable DEFAULT_CONFIRM_ACTION = GlobalFunctionService::doNothing;

  private String yesNoQuestion = DEFAULT_YES_NO_QUESTION;

  private Runnable confirmAction = DEFAULT_CONFIRM_ACTION;

  @Override
  public ILayer<?> build() {
    return new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControl(
            new Label()
              .setText(getYesNoQuestion()),
            new HorizontalStack()
              .addControl(
                new Button()
                  .setRole(ButtonRole.CANCEL_BUTTON)
                  .setText("No")
                  .setLeftMouseButtonPressAction(b -> b.getStoredParentLayer().removeSelfFromGui()),
                new Button()
                  .setRole(ButtonRole.CONFIRM_BUTTON)
                  .setText("Yes")
                  .setLeftMouseButtonPressAction(
                    (final IButton b) -> {
                      b.getStoredParentLayer().removeSelfFromGui();
                      getConfirmAction().run();
                    }))));
  }

  public YesNoDialogBuilder setConfirmAction(final Runnable confirmAction) {

    GlobalValidator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();

    this.confirmAction = confirmAction;

    return this;
  }

  public YesNoDialogBuilder setYesNoQuestion(final String yesNoQuestion) {

    GlobalValidator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotNull();

    this.yesNoQuestion = yesNoQuestion;

    return this;
  }

  private Runnable getConfirmAction() {
    return confirmAction;
  }

  private String getYesNoQuestion() {
    return yesNoQuestion;
  }
}
