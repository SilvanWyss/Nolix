//package declaration
package ch.nolix.templates.GUILooks;

//own imports
import ch.nolix.element.GUI.ButtonRole;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
public final class RedLineGUILook extends StandardConfiguration {

	//constructor
	public RedLineGUILook() {
		
		addAttachingAttribute(
			"BackgroundColorGradient(Vertical,0x400080,0x100080)",
			"ContentPosition(LeftTop)"
		);
		
		addConfiguration(
			new DeepConfiguration()
			.addAttachingAttribute(
				"BaseTextFont(Arial)",
				"BaseTextSize(25)",
				"BaseTextColor(White)",
				"BaseBaseScrollbarLook(ScrollbarColor(Black),ScrollbarCursorColor(DarkBlue))",
				"BaseHoverScrollbarLook(ScrollbarColor(Black),ScrollbarCursorColor(Blue))",
				"BaseSelectionScrollbarLook(ScrollbarColor(Black),ScrollbarCursorColor(Blue))"
			),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.OverallContainer)
			.addAttachingAttribute(
				"MinWidth(1000)",
				
				"BasePadding(50)"
			),
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute("MaxHeight(500)")
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
				"CursorIcon(Hand)",
				"ContentPosition(Center)",
				"BaseLeftPadding(5)",
				"BaseRightPadding(5)",
				"BaseTextColor(Red)",
				"HoverTextColor(0xF0F0F0)"
			),
			new DeepConfiguration()
			.addSelectorRole(
				ButtonRole.ActionButton,
				ButtonRole.CancelButton,
				ButtonRole.CreateButton,
				ButtonRole.DeleteButton,
				ButtonRole.SaveButton)
			.addAttachingAttribute(
				"MinWidth(100)",
				"BaseBackgroundColor(0x100060)",						
				"HoverBackgroundColor(0x100040)"
			),
			new DeepConfiguration()
			.addSelectorRole(ButtonRole.LinkButton)
			.addAttachingAttribute(						
				"HoverTextColor(0xF0F0F0)"
			),
			new DeepConfiguration()
			.setSelectorType("TextBox")
			.addAttachingAttribute(
				"BaseBackgroundColor(0xE0E0E0)",
				"BaseTextColor(Red)"
			),
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
