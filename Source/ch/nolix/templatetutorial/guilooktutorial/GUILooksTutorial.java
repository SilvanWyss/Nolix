package ch.nolix.templatetutorial.guilooktutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.Grid;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.dialog.InfoDialogCreator;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.CheckBox;
import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widget.SelectionMenu;
import ch.nolix.template.guilook.AnthrazitGUILookCreator;

public final class GUILooksTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a SelectionMenu.
		final var selectionMenu =
		new SelectionMenu()
		.addItem("Anthrazit", i -> i.getParentGUI().setConfiguration(new AnthrazitGUILookCreator().createGUILook()));
		
		//Creates a Frame with the SelectionMenu and several other Widgets.
		new Frame()
		.setTitle("GUILooks tutorial")
		.pushLayerWithWidget(
			new VerticalStack()
			.setRole(ContainerRole.OVERALL_CONTAINTER)
			.add(
				new Label()
				.setRole(LabelRole.TITLE)
				.setText("Configure your GUI !"),
				new HorizontalStack()
				.add(
					selectionMenu,
					new Grid()
					.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
					.setWidget(1, 1, "Checkbox")
					.setWidget(1, 2, new CheckBox())
					.setWidget(2, 1, "Button")
					.setWidget(
						2,
						2,
						new Button()
						.setText("Action")
						.setLeftMouseButtonPressAction(
							b -> 
							b.getParentGUI().pushLayer(
								new InfoDialogCreator().createInfoDialogWithInfo("Button pressed!")
							)
						)
					)
					.setWidget(3, 1, "TextBox")
					.setWidget(3, 2, new TextBox())
					.setWidget(4, 1, "DropdownMenu")
					.setWidget(4, 2, new DropdownMenu().addItem("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"))
					.setWidget(5, 1, "Console")
					.setWidget(5, 2, new Console())
					.setWidget(6, 1, "Downloader")
					.setWidget(6, 2, new Downloader().setFileProvider(() -> new byte[0]))
				)
			)
		);
		
		selectionMenu.selectFirstItem();
	}
	
	private GUILooksTutorial() {}
}
