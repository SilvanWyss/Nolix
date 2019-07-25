//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public enum LabelRole implements IElementEnum {
	Title,
	SubTitle,
	Level1Header,
	Level2Header,
	Level3Header,
	Level4Header,
	ParagraphHeader,
	DynamicInfoLabel;

	//static method
	/**
	 * @param specification
	 * @return a new {@link LabelRole} from the given specification.
	 */
	public static LabelRole createFromSpecification(
		final DocumentNodeoid specification
	) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
