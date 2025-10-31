package ch.nolix.template.webgui.dialog;

import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.coreapi.objectcreation.builder.IBuilder;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;
import ch.nolix.systemapi.webgui.basecontainer.ContainerRole;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.LayerRole;

public final class WaitDialogBuilder implements IBuilder<ILayer<?>> {
  private static final Runnable DEFAULT_JOB = FunctionService::doNothing;

  private static final Runnable DEFAULT_TERMINAL_ACTION = FunctionService::doNothing;

  private Runnable job = DEFAULT_JOB;

  private Runnable terminalAction = DEFAULT_TERMINAL_ACTION;

  @Override
  public ILayer<?> build() {
    final var waitDialog = new Layer()
      .setRole(LayerRole.DIALOG_LAYER)
      .setRootControl(
        new VerticalStack()
          .setRole(ContainerRole.DIALOG_CONTAINER)
          .addControl(
            new Label()
              .setRole(LabelRole.MAIN_LABEL)
              .setText("Please wait...")));

    FlowController.runInBackgroundAndOrder(getJob(), waitDialog::removeSelfFromGui, getTerminalAction());

    return waitDialog;
  }

  public WaitDialogBuilder setJob(final Runnable job) {
    this.job = job;

    return this;
  }

  public WaitDialogBuilder setTerminalAction(final Runnable terminalAction) {
    this.terminalAction = terminalAction;

    return this;
  }

  private Runnable getJob() {
    return job;
  }

  private Runnable getTerminalAction() {
    return terminalAction;
  }
}
