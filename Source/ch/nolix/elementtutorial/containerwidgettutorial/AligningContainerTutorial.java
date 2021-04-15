package ch.nolix.elementtutorial.containerwidgettutorial;

import ch.nolix.common.math.Calculator;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.AligningContainer;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.Label;

public final class AligningContainerTutorial {
	
	public static void main(String[] args) {
		
		final var label = new Label().setText("X");
		
		label.getRefLook().setTextSizeForState(WidgetLookState.NORMAL, 100);
		
		new Frame()
		.setTitle("AligningContainer Tutorial")
		.addLayerOnTop(
			new AligningContainer()
			.setOnTop(label)
			.setOnBottomLeft(
				new Button()
				.setText("Make smaller")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefLook().setTextSizeForState(
						WidgetLookState.NORMAL,
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
						WidgetLookState.NORMAL,
						Calculator.getMin(200, label.getRefLook().getTextSize() + 20)
					)
				)
			)
			.setProposalHeight(500)
			.setProposalWidth(500)
			.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.LAVENDER))
		);
	}
	
	private AligningContainerTutorial() {}
}
