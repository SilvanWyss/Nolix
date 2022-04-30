//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
final class BaseBackendGUIClientCounterpartUpdater {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackGUIClient;
	
	//attribute
	private final BaseBackendGUIClientGUIUpdateCommandCreator updateCanvasGUICommandCreator;
	
	//constructor
	public BaseBackendGUIClientCounterpartUpdater(final BaseBackendGUIClient<?, ?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
		updateCanvasGUICommandCreator = new BaseBackendGUIClientGUIUpdateCommandCreator(this::registerImage);
	}
	
	//method
	public void updateCounterpart() {
		
		final var updateCommands = updateCanvasGUICommandCreator.createUpdateCommandsFor(parentBackGUIClient.getRefGUI());
		
		if (updateCommands.containsAny()) {
			parentBackGUIClient.internalRunOnCounterpart(updateCommands);
		}
	}
	
	//method
	private void registerImage(final String imageId, final IImage<?> image) {
		parentBackGUIClient.internalRunOnCounterpart(
			ChainedNode.withHeaderAndNextNode(
				ObjectProtocol.GUI,
				ChainedNode.withHeaderAndAttributesFromNodes(
					CommandProtocol.REGISTER_IMAGE,
					Node.withHeader(imageId),
					image.getSpecification()
				)
			)
		);
	}
}
