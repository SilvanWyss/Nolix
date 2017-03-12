/*
 * file:	DefaultDesign.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	50
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
public final class DefaultDesign extends StandardConfiguration {

	//constructor
	/**
	 * Creates new default design that is frozen.
	 */
	public DefaultDesign() {
		
		addAttachingAttribute(
			"BackgroundColor(White)",
			"ContentOrientation(Top)"
		);
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorType("Stack")
			.addAttachingAttribute("ElementMargin(10)"),
			new DeepConfiguration()
			.setSelectorType("TabContainer")
			.addAttachingAttribute(
				"MenuItemMargin(10)",
				"MenuMargin(10)",
				"NormalMenuItemTextSize(30)",
				"HoverMenuItemBackgroundColor(LightGrey)",
				"FocusMenuItemBackgroundColor(Grey)"
			),
			new DeepConfiguration()
			.setSelectorType("Label")
			.setSelectorRole("Title")
			.addAttachingAttribute("NormalTextSize(40)"),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"NormalBackgroundColor(LightGrey)",
				"HoverBackgroundColor(Grey)",
				"LeftPadding(10)",
				"RightPadding(10)"
			)
		);
		
		freeze();
	}
}
