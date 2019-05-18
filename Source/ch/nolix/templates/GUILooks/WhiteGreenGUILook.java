/*
 * file:	GreenDesign.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	70
 */

//package declaration
package ch.nolix.templates.GUILooks;

import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.LabelRole;

//class
public final class WhiteGreenGUILook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new green design.
	 */
	public WhiteGreenGUILook() {
		
		setName("Green");
		addAttachingAttribute(
			"ContentOrientation(Top)",
			"BackgroundColor(DarkGreen)"
		);
		
		addConfiguration(			
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"NormalBackgroundColor(White)",
				"NormalTopBorderSize(50)",
				"NormalTopBorderColor(DarkGreen)",
				"Padding(10)"),
			new DeepConfiguration()
			.setSelectorType("TabContainer")
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"MinWidth(600)",
				"MenuItemMargin(50)",
				"MenuItemLeftPadding(10)",
				"MenuItemRightPadding(10)",
				"MenuMargin(10)",
				"NormalMenuItemTextColor(DarkGreen)",
				"NormalMenuItemTextSize(30)",
				"HoverMenuItemBackgroundColor(DarkGreen)",
				"HoverMenuItemTextColor(White)",
				"FocusMenuItemBackgroundColor(DarkGreen)",
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
			.addSelectorRole(LabelRole.Title)
			.addAttachingAttribute("NormalTextSize(50)"),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"CursorIcon(Hand)",
				"MinWidth(200)",
				"ContentOrientation(Center)",
				"NormalBackgroundColor(LightGrey)",
				"HoverBackgroundColor(Grey)",
				"HoverTextColor(White)",
				"Padding(10)"
			)
		);
		
		freeze();
	}
}
