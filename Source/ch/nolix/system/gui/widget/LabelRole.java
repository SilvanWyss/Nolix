//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public enum LabelRole {
	TITLE,
	SUB_TITLE,
	LEVEL1_HEADER,
	LEVEL2_HEADER,
	LEVEL3_HEADER,
	LEVEL4_HEADER,
	PARAGRAPH_HEADER,
	MAIN_TEXT,
	ERROR_TEXT;

	//static method
	/**
	 * @param specification
	 * @return a new {@link LabelRole} from the given specification.
	 * @throws RuntimeException if the given specification does not represent a {@link LabelRole}.
	 */
	public static LabelRole fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
