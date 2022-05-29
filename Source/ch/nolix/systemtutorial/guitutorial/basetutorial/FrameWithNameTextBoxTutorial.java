package ch.nolix.systemtutorial.guitutorial.basetutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.baseapi.CursorIcon;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;

/**
 * The {@link FrameWithNameTextBoxTutorial} is a tutorial for
 * {@link Frame}s, {@link Layer}s, {@link Label}s, {@link TextBox}es and {@link Button}s.
 * Of the {@link FrameWithNameTextBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-25
 */
public final class FrameWithNameTextBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox} to enter your name.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame, Layers and Widgets.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Frame with name TextBox tutorial");
		final var layer1 = new Layer();
		final var layer1VerticalStack = new VerticalStack();
		final var welcomeLabel = new Label().setText("Welcome! Please enter your name.");
		final var nameTextBox = new TextBox();
		final var confirmButton = new Button().setText("Ok");
		final var layer2 = new Layer();
		final var layer2AligningContainer = new AligningContainer();
		final var nameLabel = new Label();
		final var returnButton = new Button().setText("Ok");
		
		//Assembles the Frame, Layers and Widgets.
		frame.pushLayer(layer1);
		layer1.setRootWidget(layer1VerticalStack);
		layer1VerticalStack.add(welcomeLabel, nameTextBox, confirmButton);
		layer2.setRootWidget(layer2AligningContainer);
		layer2AligningContainer.setOnTop(nameLabel).setOnBottom(returnButton);
		
		//Sets actions to the Buttons.
		returnButton.setLeftMouseButtonPressAction(b -> b.getParentLayer().removeSelfFromGUI());
		confirmButton.setLeftMouseButtonPressAction(
			b -> {
				
				if (nameTextBox.getText().isBlank()) {
					nameLabel.setText("Please enter a name.");
				} else {
					nameLabel.setText("Hi " + nameTextBox.getText() + "!");
				}
				
				b.getParentGUI().pushLayer(layer2);
			}
		);
		
		//Configures the Look of the Frame, Layers and Widgets.
		layer1.setBackgroundColor(Color.LAVENDER);
		layer1VerticalStack.setElementMargin(10);
		confirmButton.setCustomCursorIcon(CursorIcon.HAND);
		layer2.setBackgroundColor(Color.BLACK.withAlphaValue(0.5)).setContentPosition(ExtendedContentPosition.CENTER);
		layer2AligningContainer
		.setMinWidth(500)
		.setMinHeight(200)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBorderColorForState(WidgetLookState.BASE,Color.NAVY)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.WHITE)
			.setPaddingForState(WidgetLookState.BASE, 10)
		);
		returnButton.setCustomCursorIcon(CursorIcon.HAND);
	}
	
	/**
	 * Prevents that an instance of the {@link FrameWithNameTextBoxTutorial} can be created.
	 */
	private FrameWithNameTextBoxTutorial() {}
}
