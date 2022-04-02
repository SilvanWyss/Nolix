//package declaration
package ch.nolix.element.elementenum;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.elementapi.baseapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2019-07-28
 */
public enum RotationDirection implements IElement<RotationDirection> {
	FORWARD,
	BACKWARD;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link RotationDirection} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static RotationDirection fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
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
