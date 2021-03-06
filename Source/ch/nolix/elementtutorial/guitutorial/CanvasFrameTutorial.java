package ch.nolix.elementtutorial.guitutorial;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.elementenum.UniDirection;
import ch.nolix.element.gui.base.CanvasFrame;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.textformat.Font;
import ch.nolix.element.textformat.TextFormat;
import ch.nolix.system.client.baseguiclient.PaintRun;

/**
 * The {@link CanvasFrameTutorial} is a tutorial for {@link CanvasFrame}s.
 * Of the {@link CanvasFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-08-15
 * @lines 110
 */
public final class CanvasFrameTutorial {
	
	/**
	 * Creates a new {@link CanvasFrame} and sets some paint commands to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates CanvasFrame.
		final var canvasFrame =
		new CanvasFrame(new InputTaker())
		.setTitle("CanvasFrame Tutorial");
		
		//Create paint commands.
		@SuppressWarnings("unchecked")
		final IContainer<IElementTaker<PaintRun>> paintCommands =
		LinkedList.withElements(
			pr -> {
				final var painter = pr.getRefPainterByIndex(1);
				painter.setColorGradient(new ColorGradient(UniDirection.VERTICAL, Color.BLUE, Color.MIDNIGHT_BLUE));
				painter.paintFilledRectangle(canvasFrame.getViewAreaWidth(), canvasFrame.getViewAreaHeight());
				painter.translate(100, 100);
				painter.paintText("Nolix", new TextFormat(Font.ARIAL, false, true, 100, Color.SILVER));
				painter.translate(0, 200);
				painter.setColor(Color.SILVER);
				painter.paintFilledRectangle(900, 2);
			}
		);
		
		//Sets the paint commands to the CanvasFrame.
		canvasFrame.setPaintCommands(paintCommands);
	}
	
	private static class InputTaker implements IResizableInputTaker {
		
		@Override
		public void noteKeyPress(final Key key) {}
		
		@Override
		public void noteKeyRelease(final Key key) {}
		
		@Override
		public void noteKeyTyping(final Key key) {}
		
		@Override
		public void noteLeftMouseButtonClick() {}
		
		@Override
		public void noteLeftMouseButtonPress() {}
		
		@Override
		public void noteLeftMouseButtonRelease() {}
		
		@Override
		public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {}
		
		@Override
		public void noteMouseWheelClick() {}
		
		@Override
		public void noteMouseWheelPress() {}
		
		@Override
		public void noteMouseWheelRelease() {}
		
		@Override
		public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {}
		
		@Override
		public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {}
		
		@Override
		public void noteRightMouseButtonClick() {}
			
		@Override
		public void noteRightMouseButtonPress() {}
		
		@Override
		public void noteRightMouseButtonRelease() {}
	}
	
	/**
	 * Avoids that an instance of the {@link CanvasFrameTutorial} can be created.
	 */
	private CanvasFrameTutorial() {}
}
