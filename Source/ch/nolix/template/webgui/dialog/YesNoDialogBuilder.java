/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.template.webgui.dialog;

import ch.nolix.base.util.FunctionService;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.programcontrol.builder.Builder;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.control.horizontalstack.HorizontalStack;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.control.verticalstack.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.button.IButton;
import ch.nolix.systemapi.control.container.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;

/**
 * @author Silvan Wyss
 */
public final class YesNoDialogBuilder implements Builder<ILayer> {
  private static final String DEFAULT_YES_NO_QUESTION = "Do you want to run the action?";

  private static final Runnable DEFAULT_CONFIRM_ACTION = FunctionService::doNothing;

  private String yesNoQuestion = DEFAULT_YES_NO_QUESTION;

  private Runnable confirmAction = DEFAULT_CONFIRM_ACTION;

  @Override
  public ILayer build() {
    return new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControls(
            new Label()
              .setText(getYesNoQuestion()),
            new HorizontalStack()
              .addControls(
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
