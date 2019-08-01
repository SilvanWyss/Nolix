package ch.nolix.templatesTutorial.GUILooksTutorial;

import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Checkbox;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.Downloader;
import ch.nolix.element.widgets.DropdownMenu;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.LabelRole;
import ch.nolix.element.widgets.SelectionMenu;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;
import ch.nolix.templates.GUILooks.BlackBlueGUILook;
import ch.nolix.templates.GUILooks.RedLineGUILook;
import ch.nolix.templates.GUILooks.WhiteGreenGUILook;

public final class GUILooksTutorial {
	
	public static void main(String[] args) {
		
		final var selectionMenu =
		new SelectionMenu()
		.addItem("Anthrazit", im -> im.getParentGUI().setConfiguration(new AnthrazitGUILook()))
		.addItem("BlackBlue", im -> im.getParentGUI().setConfiguration(new BlackBlueGUILook()))
		.addItem("RedLine", im -> im.getParentGUI().setConfiguration(new RedLineGUILook()))
		.addItem("WhiteGreen", im -> im.getParentGUI().setConfiguration(new WhiteGreenGUILook()));	
		
		//Creates a Frame with several Widgets and the possibility to select a GUI look.
		new LayerFrame(
			"GUI Looks Tutorial",
			new VerticalStack(
				new Label("Configure your GUI !").setRole(LabelRole.Title),
				new HorizontalStack(
					new VerticalStack(
						new Label("Select a GUI look."),
						selectionMenu
					),
					new VerticalStack(
						new Label("Widgets").setRole(LabelRole.Level1Header),
						new Grid()
						.setWidget(1, 1, "Checkbox")
						.setWidget(1, 2, new Checkbox())
						.setWidget(2, 1, "Button")
						.setWidget(2, 2, new Button())
						.setWidget(3, 1, "TextBox")
						.setWidget(3, 2, new TextBox())
						.setWidget(4, 1, "DropdownMenu")
						.setWidget(4, 2, new DropdownMenu("A", "B", "C", "D", "E", "F", "G", "H", "I", "J"))
						.setWidget(5, 1, "Console")
						.setWidget(5, 2, new Console())
						.setWidget(6, 1, "Downloader")
						.setWidget(6, 2, new Downloader().setFileProvider(() -> new byte[0]))
					)
				)
			)
			.setRole(ContainerRole.OverallContainer)
		);
		
		selectionMenu.selectFirstItem();
	}
	
	private GUILooksTutorial() {}
}
