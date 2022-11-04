//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class WaitDialogFactory {
	
	//static attribute
	public static final WaitDialogFactory INSTANCE = new WaitDialogFactory();
	
	//constructor
	private WaitDialogFactory() {}
	
	//method
	public Layer createWaitDialogForJobAndTerminalAction(final IAction job, final IAction terminalAction) {
		
		final var waitDialog =
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Text().setText("Please wait...")
			)
		);
		
		GlobalSequencer.runInBackgroundAndOrder(job, waitDialog::removeSelfFromGUI, terminalAction);
		
		return waitDialog;
	}	
}
