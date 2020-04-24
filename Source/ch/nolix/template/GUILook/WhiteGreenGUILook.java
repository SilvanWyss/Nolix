/*
 * file:	GreenDesign.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	70
 */

//package declaration
package ch.nolix.template.GUILook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.widgets.LabelRole;

//class
public final class WhiteGreenGUILook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new green design.
	 */
	public WhiteGreenGUILook() {
		
		setName("Green");
		addAttachingAttribute("BackgroundColor(DarkGreen)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorType("Layer")
			.addAttachingAttribute("ContentPosition(Top)"),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"BaseBackgroundColor(White)",
				"BaseTopBorderThickness(50)",
				"BaseTopBorderColor(DarkGreen)",
				"BasePadding(10)"),
			new DeepConfiguration()
			.setSelectorType("TabContainer")
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"MinWidth(600)",
				"MenuItemMargin(50)",
				"MenuItemLeftPadding(10)",
				"MenuItemRightPadding(10)",
				"MenuMargin(10)",
				"BaseMenuItemTextColor(DarkGreen)",
				"BaseMenuItemTextThickness(30)",
				"HoverMenuItemBackgroundColor(DarkGreen)",
				"HoverMenuItemTextColor(White)",
				"FocusMenuItemBackgroundColor(DarkGreen)",
				"FocusMenuItemTextColor(White)"
			),
			new DeepConfiguration()
			.setSelectorType("Stack")
			.addAttachingAttribute(
				"ElementMargin(10)",
				"ContentPosition(Center)"
			),
			new DeepConfiguration()
			.setSelectorType("Label")
			.addSelectorRole(LabelRole.Title)
			.addAttachingAttribute("BaseTextSize(50)"),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"CursorIcon(Hand)",
				"MinWidth(200)",
				"ContentPosition(Center)",
				"BaseBackgroundColor(LightGrey)",
				"HoverBackgroundColor(Grey)",
				"HoverTextColor(White)",
				"BasePadding(10)"
			)
		);
		
		freeze();
	}
}
