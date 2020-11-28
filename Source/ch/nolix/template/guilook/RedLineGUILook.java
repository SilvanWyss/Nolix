//package declaration
package ch.nolix.template.guilook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.LabelRole;

//class
public final class RedLineGUILook extends Configuration {
	
	//nested class
	private static final class ButtonLook extends DeepConfiguration {
		
		//constructor
		public ButtonLook() {
			
			setSelectorType(Button.class);
			
			addAttachingAttribute(
				"CursorIcon(Hand)",
				"ContentPosition(Center)",
				"BaseLeftPadding(5)",
				"BaseRightPadding(5)",
				"BaseTextColor(Red)",
				"HoverTextColor(0xF0F0F0)"
			);
		}
	}
	
	//constructor
	public RedLineGUILook() {
		
		addAttachingAttribute("BackgroundColorGradient(Vertical,0x400080,0x100080)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorType("Layer")
			.addAttachingAttribute("ContentPosition(LeftTop)"),
			new DeepConfiguration()
			.setSelectorType("Widget")
			.addAttachingAttribute(
				"BaseFont(Arial)",
				"BaseTextSize(25)",
				"BaseTextColor(White)"
			),
			new DeepConfiguration()
			.setSelectorType("BorderWidget")
			.addAttachingAttribute(
				"BaseBaseScrollBarLook(ScrollBarColor(Black),ScrollCursorColor(DarkBlue))",
				"BaseHoverScrollBarLook(ScrollBarColor(Black),ScrollCursorColor(Blue))",
				"BaseSelectionScrollBarLook(ScrollBarColor(Black),ScrollCursorColor(Blue))"
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
			new ButtonLook(),
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
	}
}
