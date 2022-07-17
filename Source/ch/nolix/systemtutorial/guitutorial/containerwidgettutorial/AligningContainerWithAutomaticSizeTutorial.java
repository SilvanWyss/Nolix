package ch.nolix.systemtutorial.guitutorial.containerwidgettutorial;

//own imports
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class AligningContainerWithAutomaticSizeTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var label = new Label().setText("X");
		
		label.getRefLook().setTextSizeForState(ControlState.BASE, 100);
		
		new Frame()
		.setTitle("AligningContainer with automatic size tutorial")
		.pushLayerWithRootWidget(
			new AligningContainer()
			.setOnTop(label)
			.setOnBottomLeft(
				new Button()
				.setText("Make smaller")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefLook().setTextSizeForState(
						ControlState.BASE,
						GlobalCalculator.getMax(20, label.getRefLook().getTextSize() - 20)
					)
				)
			)
			.setOnBottomRight(
				new Button()
				.setText("Make bigger")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefLook().setTextSizeForState(
							ControlState.BASE,
						GlobalCalculator.getMin(200, label.getRefLook().getTextSize() + 20)
					)
				)
			)
			.activateAutomaticSize()
			.onLook(l -> l.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER))
		);
	}
	
	private AligningContainerWithAutomaticSizeTutorial() {}
}
