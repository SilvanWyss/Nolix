//package declaration
package ch.nolix.template.guilook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.widget.LabelRole;

//class
public final class BlackBlueGUILook extends Configuration {
	
	//constructor
	public BlackBlueGUILook() {
		
		addAttachingAttribute("BackgroundColor(Black)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorType("Layer")
			.addAttachingAttribute("ContentPosition(Top)"),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"BaseBackgroundColor(0xE0E0E0)",
				"BaseTopBorderThickness(50)",
				"BaseTopBorderColor(Black)",
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
				"BaseMenuItemTextColor(Blue)",
				"BaseMenuItemTextThickness(30)",
				"HoverMenuItemBackgroundColor(Blue)",
				"HoverMenuItemTextColor(White)",
				"FocusMenuItemBackgroundColor(Blue)",
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
			.addAttachingAttribute(
				"BaseTextSize(50)",
				"BaseTextColor(Blue)"
			),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"CursorIcon(Hand)",
				"MinWidth(200)",
				"ContentPosition(Center)",
				"BaseBackgroundColor(DarkGrey)",
				"BaseTextColor(Blue)",
				"HoverBackgroundColor(Black)",
				"HoverTextColor(White)",
				"BasePadding(10)"
			)
		);
	}
}
