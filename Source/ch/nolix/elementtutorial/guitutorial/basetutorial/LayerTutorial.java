package ch.nolix.elementtutorial.guitutorial.basetutorial;

//own imports
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.WidgetLookState;

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
			.add(
				new Button()
				.setText("Close")
				.setLeftMouseButtonReleaseAction(frame::removeTopLayer)
			)
			.onLook(
				l ->
				l
				.setBorderThicknessForState(WidgetLookState.BASE, 5)
				.setBackgroundColorForState(WidgetLookState.BASE,Color.WHITE)
				.setLeftPaddingForState(WidgetLookState.BASE,200)
				.setRightPaddingForState(WidgetLookState.BASE,200)
				.setTopPaddingForState(WidgetLookState.BASE,200)
				.setBottomPaddingForState(WidgetLookState.BASE,5)
			)
		);
		
		//Creates showButton.
		final var showButton = new Button()
		.setText("Show")
		.setLeftMouseButtonReleaseAction(() -> frame.addLayerOnTop(dialogLayer));
		
		//Adds the showButton to the Frame.
		frame.addLayerOnTop(ExtendedContentPosition.CENTER, showButton);
	}
	
	/**
	 * Prevents that an instance of the {@link LayerTutorial} can be created.
	 */
	private LayerTutorial() {}
}
