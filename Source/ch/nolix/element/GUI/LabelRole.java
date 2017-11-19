//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public enum LabelRole implements SpecifiedByClassNameAndOneAttribute {
	Title,
	SubTitle,
	Level1Header,
	Level2Header,
	Level3Header,
	Level4Header,
	ParagraphHeader,
	DynamicInfoLabel;

	//method
	/**
	 * @return the attribute of this label role.
	 */
	public StandardSpecification getAttribute() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
}
