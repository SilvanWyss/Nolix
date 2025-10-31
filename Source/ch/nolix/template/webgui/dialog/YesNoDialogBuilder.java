package ch.nolix.template.webgui.dialog;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.coreapi.objectcreation.builder.IBuilder;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webcontainercontrol.horizontalstack.HorizontalStack;
import ch.nolix.system.webcontainercontrol.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webatomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webatomiccontrol.button.IButton;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;

public final class YesNoDialogBuilder implements IBuilder<ILayer<?>> {
  private static final String DEFAULT_YES_NO_QUESTION = "Do you want to run the action?";

  private static final Runnable DEFAULT_CONFIRM_ACTION = FunctionService::doNothing;

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
    Validator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();

    this.confirmAction = confirmAction;

    return this;
  }

  public YesNoDialogBuilder setYesNoQuestion(final String yesNoQuestion) {
    Validator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotNull();

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
