package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.core.containers.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.element.GUI.CanvasFrame;
import ch.nolix.element.baseGUI_API.IEventTaker;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.elementEnums.UniDirection;
import ch.nolix.element.input.Key;
import ch.nolix.element.textFormat.Font;
import ch.nolix.element.textFormat.TextFormat;
import ch.nolix.system.GUIClientoid.PaintRun;

/**
 * The {@link CanvasFrameTutorial} provides a tutorial for {@link CanvasFrame}s.
 * Of the {@link CanvasFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-08
 */
public class CanvasFrameTutorial {
	
	/**
	 * Creates a new {@link CanvasFrame} and sets some paint commands to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a CanvasFrame.
		final var canvasFrame = new CanvasFrame("CanvasFrame Tutorial", new EventTaker());
		
		//Create paintCommands.
		final var paintCommands = new List<IElementTaker<PaintRun>>();
		paintCommands.addAtEnd(
			pr -> {
				var painter = pr.getRefPainterByIndex(1);
				painter.setColorGradient(new ColorGradient(UniDirection.Vertical, Color.BLUE, Color.MIDNIGHT_BLUE));
				painter.paintFilledRectangle(1000, 500);
				painter.translate(100, 100);
				painter.paintText("Nolix", new TextFormat(Font.Arial, false, true, 100, Color.SILVER));
				painter.translate(0, 150);
				painter.paintText("future technology", new TextFormat(Font.Papyrus, false, false, 50, Color.WHITE));
				painter.translate(-50, 100);
				painter.setColor(Color.SILVER);
				painter.paintFilledRectangle(900, 2);
			}
		);
		
		//Sets the paintCommands to the canvasFrame.
		canvasFrame.setPaintCommands(paintCommands);
	}
	
	private static class EventTaker implements IEventTaker {
		
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
		public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {}
		
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
