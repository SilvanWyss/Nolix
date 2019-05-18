/*
 * file:	BlackNBlueDesign.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	80
 */

//package declaration
package ch.nolix.templates.GUILooks;

import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.LabelRole;

//class
public final class BlackBlueGUILook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new green design.
	 */
	public BlackBlueGUILook() {
		
		addAttachingAttribute(
			"ContentOrientation(Top)",
			"BackgroundColor(Black)"
		);
		
		addConfiguration(			
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"NormalBackgroundColor(Anthrazit)",
				"NormalTopBorderSize(50)",
				"NormalTopBorderColor(Black)",
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
			.addSelectorRole(LabelRole.Title)
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
