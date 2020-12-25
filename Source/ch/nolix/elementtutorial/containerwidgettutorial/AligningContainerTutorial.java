package ch.nolix.elementtutorial.containerwidgettutorial;

import ch.nolix.common.math.Calculator;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerwidget.AligningContainer;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.Label;

public final class AligningContainerTutorial {
	
	public static void main(String[] args) {
		
		final var label = new Label().setText("X").applyOnBaseLook(bl -> bl.setTextSize(100));
		
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
					label.getRefBaseLook().setTextSize(
						Calculator.getMax(20, label.getRefBaseLook().getRecursiveOrDefaultTextSize() - 20)
					)
				)
			)
			.setOnBottomRight(
				new Button()
				.setText("Make bigger")
				.setLeftMouseButtonPressAction(
					() ->
					label.getRefBaseLook().setTextSize(
						Calculator.getMin(200, label.getRefBaseLook().getRecursiveOrDefaultTextSize() + 20)
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
