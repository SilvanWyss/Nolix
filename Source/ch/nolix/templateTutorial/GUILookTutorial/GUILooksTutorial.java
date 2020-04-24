package ch.nolix.templateTutorial.GUILookTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.containerWidgets.Grid;
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
import ch.nolix.template.GUILook.AnthrazitGUILook;
import ch.nolix.template.GUILook.BlackBlueGUILook;
import ch.nolix.template.GUILook.RedLineGUILook;
import ch.nolix.template.GUILook.WhiteGreenGUILook;

public final class GUILooksTutorial {
	
	public static void main(String[] args) {
		
		//Creates a SelectionMenu.
		final var selectionMenu =
		new SelectionMenu()
		.addItem("Anthrazit", i -> i.getRefGUI().setConfiguration(new AnthrazitGUILook()))
		.addItem("BlackBlue", i -> i.getRefGUI().setConfiguration(new BlackBlueGUILook()))
		.addItem("RedLine", i -> i.getRefGUI().setConfiguration(new RedLineGUILook()))
		.addItem("WhiteGreen", i -> i.getRefGUI().setConfiguration(new WhiteGreenGUILook()));	
		
		//Creates a Frame with the SelectionMenu and several other Widgets.
		new Frame(
			"GUI Looks Tutorial",
			new VerticalStack(
				new Label("Configure your GUI !").setRole(LabelRole.Title),
				new HorizontalStack(
					selectionMenu,
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
			.setRole(ContainerRole.OverallContainer)
		);
		
		selectionMenu.selectFirstItem();
	}
	
	private GUILooksTutorial() {}
}
