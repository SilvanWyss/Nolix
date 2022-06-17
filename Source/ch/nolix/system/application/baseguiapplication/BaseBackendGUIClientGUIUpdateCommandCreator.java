//package declaration
package ch.nolix.system.application.baseguiapplication;

import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.guiapi.baseapi.CursorIcon;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetGUI;

//class
final class BaseBackendGUIClientGUIUpdateCommandCreator {
	
	//optional attribute
	private String latestTitle;
	
	//optional attribute
	private IImage latestIcon;
	
	//optional attribute
	private CursorIcon latestCursorIcon;
	
	//multi attribute
	private IContainer<ChainedNode> latestImageRegistrationCommandsAndPaintCommands = new LinkedList<>();
	
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
	
	private ChainedNode createUpdateIconCommand(IImage icon) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributesFromNodes(CommandProtocol.SET_ICON, icon.getSpecification())
		);
	}
	
	//method
	private ChainedNode createGUISetPaintCommandsCommand(final IContainer<ChainedNode> paintCommands) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributes(
				CommandProtocol.SET_PAINT_COMMANDS,
				paintCommands
			)
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
	
	private void fillUpUpdatedImageRegistrationCommandsAndPaintCommandsInto(
		final LinkedList<ChainedNode> list,
		final IWidgetGUI<?> widgetGUI
	) {
		
		final var imageRegistrationCommands = new LinkedList<ChainedNode>();
		final var paintCommands =
		widgetGUI.getPaintCommands(
			(id, im) -> imageRegistrationCommands.addAtEnd(createRegisterImageCommand(id, im))
		);
		final var imageRegistrationAndPaintCommands = new LinkedList<ChainedNode>();
		imageRegistrationAndPaintCommands.addAtEnd(imageRegistrationCommands);
		imageRegistrationAndPaintCommands.addAtEnd(createGUISetPaintCommandsCommand(paintCommands));
		
		if (!nodesEqual(imageRegistrationAndPaintCommands, latestImageRegistrationCommandsAndPaintCommands)) {
			list.addAtEnd(imageRegistrationAndPaintCommands);
			latestImageRegistrationCommandsAndPaintCommands = imageRegistrationAndPaintCommands;
		}
	}
	
	//method
	private ChainedNode createRegisterImageCommand(final String imageId, final IImage image) {
		return
		ChainedNode.withHeaderAndNextNode(
			ObjectProtocol.GUI,
			ChainedNode.withHeaderAndAttributesFromNodes(
				CommandProtocol.REGISTER_IMAGE,
				Node.withHeader(imageId),
				image.getCompressedSpecification()
			)
		);
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
		fillUpUpdatedImageRegistrationCommandsAndPaintCommandsInto(list, widgetGUI);
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
