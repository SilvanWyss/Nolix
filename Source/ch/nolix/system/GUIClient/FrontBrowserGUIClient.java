//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.util.Package;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.system.client.Client;

//class
/**
 * A {@link FrontBrowserGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue allow
 * that a browser-analogue can be implemented in Javascript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 100
 */
public final class FrontBrowserGUIClient extends Client<FrontBrowserGUIClient> {
	
	//multi-attribute
	private final List<Package<IPainter>> painters = new List<Package<IPainter>>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void internal_finishSessionInitialization() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case BackGUIClient.PAINTER_BY_INDEX_HEADER:
				
				runPainterCommand(
					getRefPainterByIndex(command.getOneAttributeAsInt()),
					command.getRefNextStatement()
				);
				
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * @return the {IPainter} with the given index from the current {@link FrontBrowserGUIClient}.
	 * @param index
	 */
	private IPainter getRefPainterByIndex(final int index) {
		return painters.getRefFirst(p -> p.hasIndex(index)).getRefContent();
	}

	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given painter command.
	 * 
	 * @param painterCommand
	 * @throws InvalidArgumentException if the given painter command is not valid.
	 */
	private void runPainterCommand(final IPainter painter, final Statement painterCommand) {
		
		//Enumerates the header of the given painter command.
		switch (painterCommand.getHeader()) {
			case BackGUIClient.PAINT_IMAGE_HEADER:
				runPaintImageCommand(painter, painterCommand);				
				break;
			case BackGUIClient.PAINT_FILLED_RECTANGLE_HEADER:
				runPaintFilledRectangleCommand(painter, painterCommand);
				break;
			case BackGUIClient.SET_COLOR_HEADER:
				runSetColorCommand(painter, painterCommand);
				break;
			case BackGUIClient.SET_COLOR_GRADIENT_HEADER:
				runSetColorGradientCommand(painter, painterCommand);
				break;
			default:
				throw
				new InvalidArgumentException(
					new ArgumentName("painter command"),
					new Argument(painterCommand)
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint filled rectangle command.
	 * 
	 * @param painter
	 * @param paintFilledRectangleCommand
	 * @throws InvalidArgumentException if the given paint filled rectangle command is not valid.
	 */
	private void runPaintFilledRectangleCommand(
		final IPainter painter,
		final Statement paintFilledRectangleCommand
	) {
		
		//Extracts the attributes of the given paint filled rectangle command.
		final var attributes = paintFilledRectangleCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint filled rectangle command.
		switch (paintFilledRectangleCommand.getAttributeCount()) {
			case 2:
				
				painter.paintFilledRectangle(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt()
				);				
				
				break;
			case 4:
				
				painter.paintFilledRectangle(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt(),
					attributes.getRefAt(3).toInt(),
					attributes.getRefAt(4).toInt()
				);
				
				break;
			default:
				throw
				new InvalidArgumentException(
					new ArgumentName("paint filled rectangle command"),
					new Argument(paintFilledRectangleCommand)
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint image command.
	 * 
	 * @param painter
	 * @param paintImageCommand
	 * @throws InvalidArgumentException if the given paint image command is not valid.
	 */
	private void runPaintImageCommand(
		final IPainter painter,
		final Statement paintImageCommand
	) {
		
		//Extracts the attributes of the given paint image command.
		final var attributes = paintImageCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint image command.
		switch (attributes.getElementCount()) {
			case 1:
				
				painter.paintImage(Image.createFromSpecification(
					attributes.getRefAt(1))
				);
				
				break;
			case 3:
												
				painter.paintImage(
					Image.createFromSpecification(attributes.getRefAt(1)),
					attributes.getRefAt(2).toInt(),
					attributes.getRefAt(3).toInt()
				);
				
				break;
			default:
				throw
				new InvalidArgumentException(
					new ArgumentName("paint image command"),
					new Argument(paintImageCommand)
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color command.
	 * 
	 * @param painter
	 * @param setColorCommand
	 * @throws InvalidArgumentException if the given set color command is not valid.
	 */
	private void runSetColorCommand(final IPainter painter, final Statement setColorCommand) {
		painter.setColor(
			Color.createFromSpecification(setColorCommand.getRefOneAttribute())
		);	
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color gradient command.
	 * 
	 * @param painter
	 * @param setColorGradientCommand
	 * @throws InvalidArgumentException if the given set color gradient command is not valid.
	 */
	private void runSetColorGradientCommand(
		final IPainter painter,
		final Statement setColorGradientCommand
	) {
		painter.setColorGradient(
			ColorGradient.createFromSpecification(setColorGradientCommand.getRefOneAttribute())
		);	
	}
}
