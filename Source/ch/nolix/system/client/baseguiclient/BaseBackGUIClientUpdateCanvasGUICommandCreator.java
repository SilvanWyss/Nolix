//package declaration
package ch.nolix.system.client.baseguiclient;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.gui.base.IWidgetGUI;

//visibility-reduced class
final class BaseBackGUIClientUpdateCanvasGUICommandCreator {
	
	//method
	public LinkedList<ChainedNode> createUpdateCommandsFor(final IWidgetGUI<?> widgetGUI) {
		
		final var updateCommands = new LinkedList<ChainedNode>();
		fillUpUpdateCommandsForWidgetGUIIntoList(widgetGUI, updateCommands);		
				
		return updateCommands;
	}
	
	//method
	private void fillUpUpdateCommandsForWidgetGUIIntoList(
		final IWidgetGUI<?> widgetGUI,
		final LinkedList<ChainedNode> list
	) {
		
		list.addAtEnd(
			ChainedNode.withHeaderAndNextNode(
				ObjectProtocol.GUI,
				ChainedNode.withHeaderAndAttributesFromNodes(
						CommandProtocol.SET_TITLE,
						Node.withHeader(widgetGUI.getTitle())
				)
			),
			ChainedNode.withHeaderAndNextNode(
				ObjectProtocol.GUI,
				ChainedNode.withHeaderAndAttributesFromNodes(
					CommandProtocol.SET_CURSOR_ICON,
					widgetGUI.getCursorIcon().getSpecification()
				)
			)
		);
		
		final var paintCommands = widgetGUI.getPaintCommands();
		if (paintCommands.containsAny()) {
			list.addAtEnd(
				ChainedNode.withHeaderAndNextNode(
					ObjectProtocol.GUI,
					ChainedNode.withHeaderAndAttributes(
						CommandProtocol.SET_PAINT_COMMANDS,
						paintCommands
					)
				)
			);
		}
	}
}
