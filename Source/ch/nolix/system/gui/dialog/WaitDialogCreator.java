//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.gui.base.LayerRole;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.widgetguiapi.ILayer;

//class
public final class WaitDialogCreator {
	
	//static attribute
	public static final WaitDialogCreator INSTANCE = new WaitDialogCreator();
	
	//constructor
	private WaitDialogCreator() {}
	
	//method
	public ILayer<?> createWaitDialogForJobAndTerminalAction(final IAction job, final IAction terminalAction) {
		
		final var waitDialog =
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(new Label().setText("Please wait..."));
		
		GlobalSequencer.runInBackgroundAndOrder(job, waitDialog::removeSelfFromGUI, terminalAction);
		
		return waitDialog;
	}
}
