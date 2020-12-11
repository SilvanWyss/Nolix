//package declaration
package ch.nolix.element.elementenum;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 60
 */
public enum RotationDirection implements IElementEnum {
	FORWARD,
	BACKWARD;
	
	//constant
	public static final String TYPE_NAME = "DirectionOfRotation";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link RotationDirection} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static RotationDirection fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
	
	//method
	/**
	 * @return 1 if the current {@link RotationDirection} is {@link RotationDirection#FORWARD},
	 * -1 if the current {@link RotationDirection} is {@link RotationDirection#BACKWARD}.
	 */
	public int toInt() {
		
		//Enumerates the current DirectionOfRotation.
		switch (this) {
			case FORWARD:
				return 1;
			case BACKWARD:
				return -1;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
