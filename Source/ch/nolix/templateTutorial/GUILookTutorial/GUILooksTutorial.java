package ch.nolix.templateTutorial.GUILookTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.dialog.InfoDialog;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.CheckBox;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.SelectionMenu;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.template.GUILook.AnthrazitGUILook;
import ch.nolix.template.GUILook.BlackBlueGUILook;
import ch.nolix.template.GUILook.RedLineGUILook;
import ch.nolix.template.GUILook.WhiteGreenGUILook;

public final class GUILooksTutorial {
	
	public static void main(String[] args) {
		
		//Creates a SelectionMenu.
		final var selectionMenu =
		new SelectionMenu()
		.addItem("Anthrazit", i -> i.getParentGUI().setConfiguration(new AnthrazitGUILook()))
		.addItem("BlackBlue", i -> i.getParentGUI().setConfiguration(new BlackBlueGUILook()))
		.addItem("RedLine", i -> i.getParentGUI().setConfiguration(new RedLineGUILook()))
		.addItem("WhiteGreen", i -> i.getParentGUI().setConfiguration(new WhiteGreenGUILook()));	
		
		//Creates a Frame with the SelectionMenu and several other Widgets.
		new Frame()
		.setTitle("GUILooks Tutorial")
		.addLayerOnTop(
			new VerticalStack()
			.setRole(ContainerRole.OverallContainer)
			.addWidget(
				new Label("Configure your GUI !").setRole(LabelRole.Title),
				new HorizontalStack(
					selectionMenu,
					new Grid()
					.setRole(ContainerRole.MainContainer)
					.setWidget(1, 1, "Checkbox")
					.setWidget(1, 2, new CheckBox())
					.setWidget(2, 1, "Button")
					.setWidget(
						2,
						2,
						new Button()
						.setText("Action")
						.setLeftMouseButtonPressAction(
							b -> b.getParentGUI().addLayerOnTop(new InfoDialog("Button pressed!"))
						)
					)
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
		);
		
		selectionMenu.selectFirstItem();
	}
	
	private GUILooksTutorial() {}
}
