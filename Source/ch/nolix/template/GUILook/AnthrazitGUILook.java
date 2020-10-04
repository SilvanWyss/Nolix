//package declaration
package ch.nolix.template.GUILook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.LabelRole;

//class
public final class AnthrazitGUILook extends Configuration {
	
	//constructor
	public AnthrazitGUILook() {
		
		addAttachingAttribute("BackgroundColor(0x101010)");
		
		addConfiguration(
			new AnthrazitDialogLook(),
			new DeepConfiguration()
			.setSelectorType("Widget")
			.addAttachingAttribute(
				"BaseFont(Arial)",
				"BaseTextSize(20)",
				"BaseTextColor(0x808080)"
			),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"MinWidth(800)",
				"BaseBackgroundColor(0x202020)",
				"BasePadding(10)"
			),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute("MinHeight(500)", "MaxHeight(600)")
			.addConfiguration(
				new DeepConfiguration()
				.addAttachingAttribute("BaseTextSize(15)")
			),
			new DeepConfiguration()
			.setSelectorType("Grid")
			.addAttachingAttribute("BaseElementMargin(10)"),
			new DeepConfiguration()
			.addSelectorRole(LabelRole.Title)
			.addAttachingAttribute(
				"BaseTextSize(40)",
				"BaseBottomPadding(10)"
			),
			new DeepConfiguration()
			.addSelectorRole(LabelRole.Level1Header)
			.addAttachingAttribute(
				"BaseTextSize(30)",
				"BaseBottomPadding(10)"
			),
			new DeepConfiguration()
			.addSelectorRole(LabelRole.Level2Header)
			.addAttachingAttribute(
				"BaseTextSize(20)",
				"BaseBottomPadding(10)"
			),
			new DeepConfiguration()
			.setSelectorType("Button")
			.addAttachingAttribute(
				"MinWidth(50)",
				"CursorIcon(Hand)",
				"ContentPosition(Center)",
				"BaseLeftPadding(5)",
				"BaseRightPadding(5)",
				"BaseTextColor(0x0000C0)",
				"HoverTextColor(0x0040FF)"		
			),
			new DeepConfiguration()
			.setSelectorType("TextBox")
			.addAttachingAttribute("BaseBackgroundColor(0xE0E0E0)"),
			new DeepConfiguration()
			.setSelectorType("SelectionMenu")
			.addAttachingAttribute(
				"MinWidth(100)",
				"MaxHeight(200)",
				"BaseBackgroundColor(0xE0E0E0)"
			)
		);
		
		freeze();
	}
}
