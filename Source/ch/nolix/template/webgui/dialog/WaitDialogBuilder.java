//package declaration
package ch.nolix.template.webgui.dialog;

//own imports
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.skillapi.IBuilder;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class WaitDialogBuilder implements IBuilder<ILayer<?>> {

  // constant
  private static final IAction DEFAULT_JOB = FunctionCatalogue::doNothing;

  // constant
  private static final IAction DEFAULT_TERMINAL_ACTION = FunctionCatalogue::doNothing;

  // attribute
  private IAction job = DEFAULT_JOB;

  // attribute
  private IAction terminalAction = DEFAULT_TERMINAL_ACTION;

  // method
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

    GlobalSequencer.runInBackgroundAndOrder(getJob(), waitDialog::removeSelfFromGui, getTerminalAction());

    return waitDialog;
  }

  // method
  public WaitDialogBuilder setJob(final IAction job) {

    this.job = job;

    return this;
  }

  // method
  public WaitDialogBuilder setTerminalAction(final IAction terminalAction) {

    this.terminalAction = terminalAction;

    return this;
  }

  // method
  private IAction getJob() {
    return job;
  }

  // method
  private IAction getTerminalAction() {
    return terminalAction;
  }
}
