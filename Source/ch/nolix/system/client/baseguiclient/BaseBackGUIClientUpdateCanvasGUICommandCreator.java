//package declaration
package ch.nolix.system.client.baseguiclient;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.IWidgetGUI;
import ch.nolix.element.gui.image.Image;

//class
final class BaseBackGUIClientUpdateCanvasGUICommandCreator {
	
	//optional attributes
	private String latestTitle;
	private Image latestIcon;
	private CursorIcon latestCursorIcon;
	
	//multi attribute
	private IContainer<ChainedNode> latestPaintCommands = new LinkedList<>();
	
	//method
	public LinkedList<ChainedNode> createUpdateCommandsFor(final IWidgetGUI<?> widgetGUI) {
		
		final var updateCommands = new LinkedList<ChainedNode>();
		fillUpUpdateCommandsInto(updateCommands, widgetGUI);		
		
		return updateCommands;
	}
	
	//method
	private ChainedNode createUpdateCursorIconCommand(final CursorIcon cursorIcon) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SET_CURSOR_ICON, cursorIcon.getSpecification())
		);
	}
	
	private ChainedNode createUpdateIconCommand(Image icon) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SET_ICON, icon.getSpecification())
		);
	}
	
	//method
	private ChainedNode createUpdatePaintCommands(final IContainer<ChainedNode> paintCommands) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributes(CommandProtocol.SET_PAINT_COMMANDS,	paintCommands)
		);
	}
	
	//method
	private ChainedNode createUpdateTitleCommand(final String title) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SET_TITLE, Node.withHeader(title))
		);
	}
	
	//method
	private void fillUpPotentialUpdateCursorIconCommandInto(LinkedList<ChainedNode> list, IWidgetGUI<?> widgetGUI) {
		
		final var cursorIcon = widgetGUI.getCursorIcon();
		
		if (cursorIcon != latestCursorIcon) {
			list.addAtEnd(createUpdateCursorIconCommand(cursorIcon));
			latestCursorIcon = cursorIcon;
		}
	}
	
	//method
	private void fillUpPotentialUpdateIconCommandInto(LinkedList<ChainedNode> list, IWidgetGUI<?> widgetGUI) {
		
		final var icon = widgetGUI.getIcon();
		
		if (icon != latestIcon) {
			list.addAtEnd(createUpdateIconCommand(icon));
			latestIcon = icon;
		}
	}
	
	private void fillUpPotentialUpdatePaintCommandsInto(
		final LinkedList<ChainedNode> list,
		final IWidgetGUI<?> widgetGUI
	) {
		
		final var paintCommands = widgetGUI.getPaintCommands();
		
		if (!nodesEqual(paintCommands, latestPaintCommands)) {
			list.addAtEnd(createUpdatePaintCommands(paintCommands));
			latestPaintCommands = paintCommands;
		}
	}
	
	//method
	private void fillUpPotentialUpdateTitleCommandInto(final LinkedList<ChainedNode> list,final IWidgetGUI<?> widgetGUI) {
		
		final var title = widgetGUI.getTitle();
		
		if (!title.equals(latestTitle)) {
			list.addAtEnd(createUpdateTitleCommand(title));
			latestTitle = title;
		}
	}
	
	//method
	private void fillUpUpdateCommandsInto(final LinkedList<ChainedNode> list, final IWidgetGUI<?> widgetGUI) {
		fillUpPotentialUpdateTitleCommandInto(list, widgetGUI);
		fillUpPotentialUpdateIconCommandInto(list, widgetGUI);
		fillUpPotentialUpdateCursorIconCommandInto(list, widgetGUI);
		fillUpPotentialUpdatePaintCommandsInto(list, widgetGUI);
	}
	
	//method
	private boolean nodesEqual(final IContainer<ChainedNode> chainedNodes1, final IContainer<ChainedNode> chainedNodes2) {
		
		if (!chainedNodes1.containsAsManyAs(chainedNodes2)) {
			return false;
		}
		
		final var iterator = chainedNodes2.iterator();
		for (final var cn : chainedNodes1) {
			if (!cn.equals(iterator.next())) {
				return false;
			}
		}
		
		return true;
	}
}
