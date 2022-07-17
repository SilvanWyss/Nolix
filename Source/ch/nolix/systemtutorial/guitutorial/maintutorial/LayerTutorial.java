package ch.nolix.systemtutorial.guitutorial.maintutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link LayerTutorial} is a tutorial for {@link Layer}s.
 * Of the {@link LayerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-05-22
 */
public final class LayerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * When the user clicks on the {@link Button} a second {@link Layer} will be added to the {@link Frame}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Layer tutorial").setBackgroundColor(Color.LAVENDER);
		
		//Creates dialogLayer.
		final var dialogLayer =
		new Layer()
		.setContentPosition(ExtendedContentPosition.CENTER)
		.setRootWidget(
			new VerticalStack()
			.addWidget(
				new Button()
				.setText("Close")
				.setLeftMouseButtonReleaseAction(frame::removeTopLayer)
			)
			.onLook(
				l ->
				l
				.setBorderThicknessForState(ControlState.BASE, 5)
				.setBackgroundColorForState(ControlState.BASE,Color.WHITE)
				.setLeftPaddingForState(ControlState.BASE,200)
				.setRightPaddingForState(ControlState.BASE,200)
				.setTopPaddingForState(ControlState.BASE,200)
				.setBottomPaddingForState(ControlState.BASE,5)
			)
		);
		
		//Creates showButton.
		final var showButton = new Button()
		.setText("Show")
		.setLeftMouseButtonReleaseAction(() -> frame.pushLayer(dialogLayer));
		
		//Adds the showButton to the Frame.
		frame.pushLayer(ExtendedContentPosition.CENTER, showButton);
	}
	
	/**
	 * Prevents that an instance of the {@link LayerTutorial} can be created.
	 */
	private LayerTutorial() {}
}
