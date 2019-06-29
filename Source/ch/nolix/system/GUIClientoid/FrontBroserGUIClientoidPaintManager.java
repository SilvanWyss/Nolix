//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;

//package-visible class
final class FrontBroserGUIClientoidPaintManager {
	
	//multi-attribute
	private final List<IElementTaker<FrontBrowserGUIClientoidPainting>> painterCommands = new List<>();
	
	//constructor
	public FrontBroserGUIClientoidPaintManager() {
		this(new List<Statement>());
	}
	
	//constructor
	public FrontBroserGUIClientoidPaintManager(final IContainer<Statement> painterCommands) {
		painterCommands.forEach(pc -> this.painterCommands.addAtEnd(createPainterCommand(pc)));
	}
	
	//method
	public void paint(final IPainter painter) {
		new FrontBrowserGUIClientoidPainting(painter, painterCommands);
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createCreatePainerCommand(
		final int painterIndex,
		final Statement painterCommand
	) {
		return painting -> painting.addPainter(painting.getRefPainterByIndex(1).createPainter());
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createPainterCommand(final Statement painterCommand) {
		
		final var painterIndex = painterCommand.getOneAttributeAsInt();
		
		return createPainterCommand(painterIndex, painterCommand.getRefNextStatement());
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createPainterCommand(
		final int painterIndex,
		final Statement painterCommand
	) {
		
		//Enumerates the header of the given painter command.
		switch (painterCommand.getHeader()) {
			case Protocol.CREATE_PAINTER_HEADER:
				return createCreatePainerCommand(painterIndex, painterCommand);
			case Protocol.PAINT_FILLED_RECTANGLE_HEADER:
				return createPaintFilledRectangleCommand(painterIndex, painterCommand);
			case Protocol.PAINT_IMAGE_HEADER:
				return createPaintImageCommand(painterIndex, painterCommand);
			case Protocol.PAINT_TEXT_HEADER:
				return createPaintTextCommand(painterIndex, painterCommand);
			case Protocol.SET_COLOR_HEADER:
				return createSetColorCommand(painterIndex, painterCommand);
			case Protocol.SET_COLOR_GRADIENT_HEADER:
				return createSetColorGradientCommand(painterIndex, painterCommand);
			default:
				throw new InvalidArgumentException("painter command",	painterCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createPaintFilledRectangleCommand(
		final int painterIndex,
		final Statement paintFilledRectangleCommand
	) {
		
		//Extracts the attributes of the given paint filled rectangle command.
		final var attributes = paintFilledRectangleCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint filled rectangle command.
		switch (paintFilledRectangleCommand.getAttributeCount()) {
			case 2: {
				final var width = attributes.getRefAt(1).toInt();
				final var height = attributes.getRefAt(2).toInt();
				
				return painting ->	painting.getRefPainterByIndex(painterIndex).paintFilledRectangle(width,	height);
			}
			case 4:
				
				final var xPosition = attributes.getRefAt(1).toInt();
				final var yPosition = attributes.getRefAt(2).toInt();
				final var width = attributes.getRefAt(3).toInt();
				final var height = attributes.getRefAt(4).toInt();
				
				return
				painting ->
				painting.getRefPainterByIndex(painterIndex).paintFilledRectangle(xPosition, yPosition, width, height);
			
			default:
				throw
				new InvalidArgumentException(
					"paint filled rectangle command",
					paintFilledRectangleCommand,
					"is not valid"
				);
		}
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createPaintImageCommand(
		final int painterIndex,
		final Statement paintImageCommand
	) {
		
		//Extracts the attributes of the given paint image command.
		final var attributes = paintImageCommand.getRefAttributes();
		
		
		//Enumerates the number of attributes of the given paint image command.
		switch (attributes.getSize()) {
			case 1:	{
				final var image = Image.createFromSpecification(attributes.getRefAt(1));
				
				return painting ->	painting.getRefPainterByIndex(painterIndex).paintImage(image);
			}
			case 3:
				
				final var image = Image.createFromSpecification(attributes.getRefAt(1));
				final var width = attributes.getRefAt(2).toInt();
				final var height = attributes.getRefAt(3).toInt();
				
				return
				painting ->
				painting.getRefPainterByIndex(painterIndex).paintImage(
					image,
					width,
					height
				);
			default:
				throw
				new InvalidArgumentException("paint image command",	paintImageCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createPaintTextCommand(
		final int painterIndex,
		final Statement paintTextCommand
	) {
		
		//Extracts the attributes of the given paint text command.
		final var attributes = paintTextCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint text command.
		switch (attributes.getSize()) {
			case 1: {
				
				final var text = attributes.getRefOne().toString();
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text);
			}
			case 2: {
				
				final var text = attributes.getRefAt(1).toString();
				final var textFormat = TextFormat.createFromSpecification(attributes.getRefAt(2));
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text, textFormat);
			}
			case 3:
				
				final var text = attributes.getRefAt(1).toString();
				final var textFormat = TextFormat.createFromSpecification(attributes.getRefAt(2));
				final var maxLength = attributes.getRefAt(3).toInt();
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text, textFormat, maxLength); 
			default:
				throw
				new InvalidArgumentException("paint text command", paintTextCommand,"is not valid");
		}
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createSetColorCommand(
		final int painterIndex,
		final Statement setColorCommand
	) {
		
		final var color = Color.createFromSpecification(setColorCommand.getRefOneAttribute());
		
		return painting -> painting.getRefPainterByIndex(painterIndex).setColor(color);
	}
	
	//method
	private IElementTaker<FrontBrowserGUIClientoidPainting> createSetColorGradientCommand(
		final int painterIndex,
		final Statement setColorGradientCommand
	) {
		
		final var colorGradient = ColorGradient.createFromSpecification(setColorGradientCommand.getRefOneAttribute());
		
		return painting -> painting.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
	}
}
