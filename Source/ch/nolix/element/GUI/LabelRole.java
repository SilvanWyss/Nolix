//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public enum LabelRole implements ISpecifiedEnum {
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
		final Specification specification
	) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
