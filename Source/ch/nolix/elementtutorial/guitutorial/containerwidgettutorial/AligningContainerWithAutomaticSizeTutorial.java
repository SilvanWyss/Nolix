package ch.nolix.elementtutorial.guitutorial.containerwidgettutorial;

import ch.nolix.common.math.Calculator;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.AligningContainer;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;

public final class AligningContainerWithAutomaticSizeTutorial {
	
	public static void main(String[] args) {
		
		final var label = new Label().setText("X");
		
		label.getRefLook().setTextSizeForState(WidgetLookState.BASE, 100);
		
		new Frame()
		.setTitle("AligningContainer with automatic size tutorial")
		.addLayerOnTop(
			new AligningContainer()
			.setOnTop(label)
			.setOnBottomLeft(
				new Button()
				.setText("Make smaller")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefLook().setTextSizeForState(
						WidgetLookState.BASE,
						Calculator.getMax(20, label.getRefLook().getTextSize() - 20)
					)
				)
			)
			.setOnBottomRight(
				new Button()
				.setText("Make bigger")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefLook().setTextSizeForState(
							WidgetLookState.BASE,
						Calculator.getMin(200, label.getRefLook().getTextSize() + 20)
					)
				)
			)
			.activateAutomaticSize()
			.onLook(l -> l.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER))
		);
	}
	
	private AligningContainerWithAutomaticSizeTutorial() {}
}
