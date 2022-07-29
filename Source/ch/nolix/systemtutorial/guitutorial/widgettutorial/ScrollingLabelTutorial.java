package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link ScrollingLabelTutorial} is a tutorial for the scroll feature of {@link BorderWidget}s.
 * Of the {@link ScrollingLabelTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-04-02
 */
public final class ScrollingLabelTutorial {
	
	/**
	 * Creates a {@link Frame} with a scrollable {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Scrolling Label tutorial");
		
		//Creates a Label.
		final var label = new Label().setText("PLATON");
		
		//Configures the look of the Label.
		label
		.setMaxWidth(1000)
		.setMaxHeight(500)
		.onStyle(
			l ->
			l
			.setBorderThicknessForState(ControlState.BASE, 5)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setTextSizeForState(ControlState.BASE, 500)
		);
		
		//Adds the Label to the frame.
		frame.pushLayerWithRootWidget(label);
	}
	
	/**
	 * Prevents that an instance of the {@link ScrollingLabelTutorial} can be created.
	 */
	private ScrollingLabelTutorial() {}
}
