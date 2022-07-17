package ch.nolix.systemtutorial.guitutorial.containerwidgettutorial;

//own imports
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class AligningContainerTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var label = new Label().setText("X");
		
		label.getRefActiveLook().setTextSizeForState(ControlState.BASE, 100);
		
		new Frame()
		.setTitle("AligningContainer tutorial")
		.pushLayerWithRootWidget(
			new AligningContainer()
			.setOnTop(label)
			.setOnBottomLeft(
				new Button()
				.setText("Make smaller")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefActiveLook().setTextSizeForState(
						ControlState.BASE,
						GlobalCalculator.getMax(20, label.getRefActiveLook().getTextSize() - 20)
					)
				)
			)
			.setOnBottomRight(
				new Button()
				.setText("Make bigger")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefActiveLook().setTextSizeForState(
						ControlState.BASE,
						GlobalCalculator.getMin(200, label.getRefActiveLook().getTextSize() + 20)
					)
				)
			)
			.setProposalHeight(500)
			.setProposalWidth(500)
			.onLook(l -> l.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER))
		);
	}
	
	private AligningContainerTutorial() {}
}
