//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum GridType {
	INNER_LINES,
	INNER_AND_OUTER_LINES;

	//static method
	public static GridType fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
