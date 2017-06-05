/*
 * file:	BlackNBlueDesign.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	80
 */

//package declaration
package ch.nolix.system.designs;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
public final class BlackNBlueDesign extends StandardConfiguration {

	//constructor
	/**
	 * Creates new green design.
	 */
	public BlackNBlueDesign() {
		
		setName("Black N' Blue");
		addAttachingAttribute(
			"ContentOrientation(Top)",
			"BackgroundColor(Black)"
		);
		
		addConfiguration(			
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"NormalBackgroundColor(Anthrazit)",
				"NormalTopBorderSize(50)",
				"NormalTopBorderColor(Black)",
				"Padding(10)"),
			new DeepConfiguration()
			.setSelectorType("TabContainer")
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"MinWidth(600)",
				"MenuItemMargin(50)",
				"MenuItemLeftPadding(10)",
				"MenuItemRightPadding(10)",
				"MenuMargin(10)",
				"NormalMenuItemTextColor(Blue)",
				"NormalMenuItemTextSize(30)",
				"HoverMenuItemBackgroundColor(Blue)",
				"HoverMenuItemTextColor(White)",
				"FocusMenuItemBackgroundColor(Blue)",
				"FocusMenuItemTextColor(White)"
			),
			new DeepConfiguration()
			.setSelectorType("Stack")
			.addAttachingAttribute(
				"ElementMargin(10)",
				"ContentOrientation(Center)"
			),
			new DeepConfiguration()
			.setSelectorType("Label")
			.setSelectorRole(LabelRole.Title)
			.addAttachingAttribute(
				"NormalTextSize(50)",
				"NormalTextColor(Blue)"
			),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"CursorIcon(Hand)",
				"MinWidth(200)",
				"ContentOrientation(Center)",
				"NormalBackgroundColor(DarkGrey)",
				"NormalTextColor(Blue)",
				"HoverBackgroundColor(Black)",
				"HoverTextColor(White)",
				"Padding(10)"
			)
		);
			
		freeze();
	}
}
