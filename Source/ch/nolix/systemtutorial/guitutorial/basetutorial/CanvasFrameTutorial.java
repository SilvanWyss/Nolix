package ch.nolix.systemtutorial.guitutorial.basetutorial;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.system.application.baseguiapplication.PaintRun;
import ch.nolix.system.gui.base.CanvasFrame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.textformat.Font;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

/**
 * The {@link CanvasFrameTutorial} is a tutorial for {@link CanvasFrame}s.
 * Of the {@link CanvasFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-08-15
 */
public final class CanvasFrameTutorial {
	
	/**
	 * Creates a new {@link CanvasFrame} and sets some paint commands to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates CanvasFrame.
		@SuppressWarnings("resource")
		final var canvasFrame = new CanvasFrame(new InputTaker()).setTitle("CanvasFrame tutorial");
		
		//Create paint commands.
		@SuppressWarnings("unchecked")
		final IContainer<IElementTaker<PaintRun>> paintCommands =
		LinkedList.withElements(
			pr -> {
				final var painter = pr.getRefPainterByIndex(1);
				painter.setColorGradient(new ColorGradient(DirectionInRectangle.VERTICAL, Color.BLUE, Color.MIDNIGHT_BLUE));
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
		public void noteKeyDown(final Key key) {
			//Does nothing.
		}
		
		@Override
		public void noteKeyRelease(final Key key) {
			//Does nothing.
		}
		
		@Override
		public void noteKeyTyping(final Key key) {
			//Does nothing.
		}
		
		@Override
		public void noteLeftMouseButtonClick() {
			//Does nothing.
		}
		
		@Override
		public void noteLeftMouseButtonPress() {
			//Does nothing.
		}
		
		@Override
		public void noteLeftMouseButtonRelease() {
			//Does nothing.
		}
		
		@Override
		public void noteMouseMove(final int cursorXPositionOnViewArea, final int cursorYPositionOnViewArea) {
			//Does nothing.
		}
		
		@Override
		public void noteMouseWheelClick() {
			//Does nothing.
		}
		
		@Override
		public void noteMouseWheelPress() {
			//Does nothing.
		}
		
		@Override
		public void noteMouseWheelRelease() {
			//Does nothing.
		}
		
		@Override
		public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
			//Does nothing.
		}
		
		@Override
		public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
			//Does nothing.
		}
		
		@Override
		public void noteRightMouseButtonClick() {
			//Does nothing.
		}
			
		@Override
		public void noteRightMouseButtonPress() {
			//Does nothing.
		}
		
		@Override
		public void noteRightMouseButtonRelease() {
			//Does nothing.
		}
	}
	
	/**
	 * Prevents that an instance of the {@link CanvasFrameTutorial} can be created.
	 */
	private CanvasFrameTutorial() {}
}
