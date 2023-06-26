//package declaration
package ch.nolix.system.graphic.color;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.graphicapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.structureproperty.DirectionInRectangle;

//class
/**
 * A {@link ColorGradient} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-08-01
 */
public final class ColorGradient extends Element implements IColorGradient {
	
	//constant
	public static final ColorGradient VERTICAL_BLACK_WHITE_COLOR_GRADIENT =
	new ColorGradient(DirectionInRectangle.VERTICAL, Color.BLACK, Color.WHITE);
	
	//constant
	public static final ColorGradient VERTICAL_RED_WHITE_COLOR_GRADIENT =
	new ColorGradient(DirectionInRectangle.VERTICAL, Color.RED, Color.WHITE);
	
	//constant
	public static final DirectionInRectangle DEFAULT_DIRECTION = DirectionInRectangle.VERTICAL;
	
	//constant
	public static final Color DEFAULT_COLOR1 = Color.BLACK;
	
	//constant
	public static final Color DEFAULT_COLOR2 = Color.WHITE;

	//static method
	/**
	 * @param specification
	 * @return a new {@link ColorGradient} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ColorGradient fromSpecification(final INode<?> specification) {
		
		final var attributes = specification.getOriChildNodes();
		
		return
		switch (attributes.getElementCount()) {
			case 2 ->
				from2Attributes(attributes);
			case 3 ->
				from3Attributes(attributes);
			default ->
				throw
				InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.SPECIFICATION, specification);
		};
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link ColorGradient} from the given attributes.
	 * @throws InvalidArgumentException if the given attributes are not valid.
	 */
	private static ColorGradient from2Attributes(IContainer<? extends INode<?>> attributes) {
		
		final var color1Specification = Node.withChildNode(attributes.getOriAt1BasedIndex(1));
		final var color2Specification = Node.withChildNode(attributes.getOriAt1BasedIndex(2));
		
		return
		new ColorGradient(
			Color.fromSpecification(color1Specification),
			Color.fromSpecification(color2Specification)
		);
	}
	
	//static method
	/**
	 * @param attributes
	 * @return a new {@link ColorGradient} from the given attributes.
	 * @throws InvalidArgumentException if the given attributes are not valid.
	 */
	private static ColorGradient from3Attributes(IContainer<? extends INode<?>> attributes) {
		
		final var directionSpecification = Node.withChildNode(attributes.getOriAt1BasedIndex(1));
		final var color1Specification = Node.withChildNode(attributes.getOriAt1BasedIndex(2));
		final var color2Specification = Node.withChildNode(attributes.getOriAt1BasedIndex(3));
		
		return
		new ColorGradient(
			DirectionInRectangle.fromSpecification(directionSpecification),
			Color.fromSpecification(color1Specification),
			Color.fromSpecification(color2Specification)
		);
	}

	//attribute
	private final DirectionInRectangle direction;
	
	//attribute
	private final Color color1;
	
	//attribute
	private final Color color2;
	
	//constructor
	/**
	 * Creates a new {@link ColorGradient}.
	 */
	public ColorGradient() {
			
		//Calls other constructor.
		this(DEFAULT_DIRECTION, DEFAULT_COLOR1, DEFAULT_COLOR2);
	}
	
	//constructor
	/**
	 * Creates a new {@link ColorGradient} with the given color1 and color2.
	 * 
	 * @param color1
	 * @param color2
	 * @throws ArgumentIsNullException if the given direction is null.
	 * @throws ArgumentIsNullException if the given color1 is null.
	 * @throws ArgumentIsNullException if the given color2 is null.
	 */
	public ColorGradient(final Color color1, final Color color2) {
		
		//Calls other constructor.
		this(DEFAULT_DIRECTION, color1, color2);
	}
	
	//constructor
	/**
	 * Creates a new {@link ColorGradient} with the given direction.
	 * 
	 * @param direction
	 * @throws ArgumentIsNullException if the given direction is null.
	 */
	public ColorGradient(final DirectionInRectangle direction) {
		
		//Calls other constructor.
		this(direction, DEFAULT_COLOR1, DEFAULT_COLOR2);
	}
	
	//constructor
	/**
	 * Creates a new {@link ColorGradient} with the given direction, color 1 and color 2.
	 * 
	 * @param direction
	 * @param color1
	 * @param color2
	 * @throws ArgumentIsNullException if the given direction is null.
	 * @throws ArgumentIsNullException if the given color 1 is null.
	 * @throws ArgumentIsNullException if the given color 2 is null.
	 */
	public ColorGradient(final DirectionInRectangle direction, final Color color1, final Color color2) {
		
		//Asserts that the given direction is not null.
		GlobalValidator.assertThat(direction).thatIsNamed("direction").isNotNull();
		
		//Asserts that the given color1 is not null.
		GlobalValidator.assertThat(color1).thatIsNamed("color1").isNotNull();
		
		//Asserts that the given color2 is not null.
		GlobalValidator.assertThat(color2).thatIsNamed("color2").isNotNull();
		
		this.direction = direction;
		this.color1 = color1;
		this.color2 = color2;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<INode<?>> getAttributes() {
		return
		LinkedList.withElements(
			Node.withHeader(getDirection().toString()),
			Node.withHeader(getColor1().toHexadecimalString()),
			Node.withHeader(getColor2().toHexadecimalString())
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor1() {
		return color1;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColor2() {
		return color2;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DirectionInRectangle getDirection() {
		return direction;
	}
}
