//package declaration
package ch.nolix.system.client.baseguiclient;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
final class BaseBackGUIClientCanvasGUICounterpartUpdater implements IBackGUIClientCounterpartUpdater {
	
	//static attribute
	private static final BaseBackGUIClientUpdateCanvasGUICommandCreator updateCanvasGUICommandCreator =
	new BaseBackGUIClientUpdateCanvasGUICommandCreator();
	
	//attribute
	private final BaseBackGUIClient<?> parentBackGUIClient;
	
	//multi-attribute
	private LinkedList<ChainedNode> latestUpdateCommands;
	
	//constructor
	public BaseBackGUIClientCanvasGUICounterpartUpdater(final BaseBackGUIClient<?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	public void updateCounterpart() {
		
		final var updateCommands =
		updateCanvasGUICommandCreator.createUpdateCommandsFor(parentBackGUIClient.getRefGUI());
		
		if (!latestUpdateCommandsEqual(updateCommands)) {
			
			parentBackGUIClient.runOnCounterpart(updateCommands);
			
			latestUpdateCommands = updateCommands;
		}
	}
	
	//method
	private boolean latestUpdateCommandsEqual(final IContainer<ChainedNode> updateCommands) {
		
		if (
			(latestUpdateCommands == null && updateCommands != null)
			|| !latestUpdateCommands.containsAsManyAs(updateCommands)
		) {
			return false;
		}
		
		final var iterator = updateCommands.iterator();
		for (final var uc : latestUpdateCommands) {
			if (!uc.equals(iterator.next())) {
				return false;
			}
		}
		
		return true;
	}
}
