//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2019-05-18
 */
public enum ExtendedContentPosition {
	TOP_LEFT,
	TOP,
	TOP_RIGHT,
	LEFT,
	CENTER,
	RIGHT,
	BOTTOM_LEFT,
	BOTTOM,
	BOTTOM_RIGHT,
	FREE;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ExtendedContentPosition} from the given specification.
	 * @throws RuntimeException* if the given specification does not represent a {@link ExtendedContentPosition}.
	 */
	public static ExtendedContentPosition fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
