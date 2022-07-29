package ch.nolix.systemtutorial.guitutorial.containerwidgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link FloatContainerTutorial} is a tutorial for {@link FloatContainer}s.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-06-01
 */
public final class FloatContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link FloatContainer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Float Container tutorial");
		
		//Creates a FloatConatiner.
		final var floatContainer =
		new FloatContainer()
		.addWidget(
			new Label().setText("A"),
			new Label().setText("B"),
			new Label().setText("C"),
			new Label().setText("D"),
			new Label().setText("E"),
			new Label().setText("F"),
			new Label().setText("G"),
			new Label().setText("H")
		);
		
		//Configures the style of the FloatContainer.
		floatContainer
		.setMaxWidth(1000)
		.onStyle(
			l ->
			l
			.setBorderThicknessForState(ControlState.BASE, 5)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setPaddingForState(ControlState.BASE, 20)
			.setElementMarginForState(ControlState.BASE, 10)
		);
		
		//Configures the style of the child Widgets of the FloatContainer.
		var index = 1;
		for (final var cw : floatContainer.getChildWidgets()) {
			
			((Label)cw)
			.setProposalWidth(100 + (index % 3) * 50)
			.setProposalHeight(100)
			.setContentPosition(ContentPosition.CENTER)
			.onStyle(
				l ->
				l
				.setBackgroundColorForState(ControlState.BASE, Color.BLUE)
				.setTextSizeForState(ControlState.BASE, 50)
			);
			
			index++;
		}
		
		//Adds the FloatContainer to the frame.
		frame.pushLayerWithRootWidget(floatContainer);
	}
	
	/**
	 * Prevents that an instance of the {@link FloatContainerTutorial} can be created.
	 */
	private FloatContainerTutorial() {}
}
