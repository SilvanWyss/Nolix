//package declaration
package ch.nolix.templates.GUILooks;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
public final class AnthrazitGUILook extends StandardConfiguration {

	//constructor
	public AnthrazitGUILook() {
		
		addAttachingAttribute("BackgroundColor(0x101010)");
		
		addConfiguration(
			new DeepConfiguration()
			.addAttachingAttribute(
				"BaseTextFont(Arial)",
				"BaseTextSize(20)",
				"BaseTextColor(0x808080)"
			),
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"MinWidth(800)",
				"BaseBackgroundColor(0x202020)",
				"BasePadding(10)"
			),
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute("MaxHeight(500)")
			.addConfiguration(
				new DeepConfiguration()
				.addAttachingAttribute("BaseTextSize(15)")
			),
			new DeepConfiguration()
			.setSelectorType("Grid")
			.addAttachingAttribute("BaseElementMargin(10)"),
			new DeepConfiguration()
			.setSelectorRole(LabelRole.Title)
			.addAttachingAttribute(
				"BaseTextSize(40)",
				"BaseBottomPadding(10)"
			),
			new DeepConfiguration()
			.setSelectorRole(LabelRole.Level1Header)
			.addAttachingAttribute(
				"BaseTextSize(30)",
				"BaseBottomPadding(10)"
			),
			new DeepConfiguration()
			.setSelectorRole(LabelRole.Level2Header)
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
				"BaseTextColor(0x000080)",
				"HoverTextColor(0x0000F0)"		
			),
			new DeepConfiguration()
			.setSelectorType("TextBox")
			.addAttachingAttribute("BaseBackgroundColor(0x808080)")
		);
		
		freeze();
	}
}
