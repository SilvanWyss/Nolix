//package declaration
package ch.nolix.template.guilook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.gui.base.LayerRole;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.Grid;
import ch.nolix.element.gui.widget.BorderWidget;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.CheckBox;
import ch.nolix.element.gui.widget.Console;
import ch.nolix.element.gui.widget.Downloader;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.element.gui.widget.Widget;
import ch.nolix.element.gui.widget.ItemMenu;
import ch.nolix.element.configuration.Configuration;

//class
public final class AnthrazitGUILookCreator {
	
	//method
	public Configuration createGUILook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(0x101010)")
		.addConfiguration(
			createWidgetLook(),
			createBorderWidgetLook(),
			createOverallContainerLook(),
			createDialogLook(),
			createDialogContainerLook(),
			createGridLook(),
			createTitleLook(),
			createLevel1HeaderLook(),
			createLevel2HeaderLook(),
			createCheckboxLook(),
			createButtonLook(),
			createDownoaderLook(),
			createTextBoxLook(),
			createTextItemMenuLook(),	
			createConsoleLook()
		);
	}
	
	//method
	private DeepConfiguration createBorderWidgetLook() {
		return
		new DeepConfiguration()
		.setSelectorType(BorderWidget.class)
		.addAttachingAttribute(
			"BaseScrollBarColor(0x101010)",
			"BaseScrollCursorColor(0x000000)",
			"BaseScrollBarHoverColor(0x101010)",
			"BaseHoverScrollCursorHoverColor(0xE0E000)",
			"BaseScrollBarMoveColor(0x101010)",
			"BaseScrollCursorMoveColor(0xE0E000)"
		);
	}
	
	//method
	private DeepConfiguration createButtonLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Button.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"CursorIcon(Hand)",
			"ContentPosition(Center)",
			"BaseLeftPadding(5)",
			"BaseRightPadding(5)",
			"BaseBackground(Color(0x101010))",
			"HoverBackground(Color(0xE0E000))",
			"HoverTextColor(0x000000)"
		);
	}
	
	//method
	private DeepConfiguration createCheckboxLook() {
		return
		new DeepConfiguration()
		.setSelectorType(CheckBox.class)
		.addAttachingAttribute(
			"CursorIcon(Hand)",
			"BaseBackground(Color(0x101010))",
			"HoverBackground(Color(0xE0E000))"
		);
	}
	
	//method
	private DeepConfiguration createConsoleLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Console.class)
		.addAttachingAttribute("BaseBackground(Color(0x101010))");
	}
	
	//method
	private DeepConfiguration createDialogLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(LayerRole.DIALOG_LAYER)
		.addAttachingAttribute("Background(Color(" + Color.fromString("0x101010F0").getHexadecimalValue() + "))");
	}
	
	//method
	private DeepConfiguration createDialogContainerLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
		.addAttachingAttribute(
			"BaseBackground(Color(0x202020))",
			"BasePadding(50)",
			"ContentPosition(Top)",
			"ElementMargin(100)"
		);
	}
	
	//method
	private DeepConfiguration createDownoaderLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Downloader.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"CursorIcon(Hand)",
			"ContentPosition(Center)",
			"BaseLeftPadding(5)",
			"BaseRightPadding(5)",
			"BaseBackground(Color(0x101010))",
			"HoverBackground(Color(0xE0E000))",
			"HoverTextColor(0x000000)"
		);
	}
	
	//method
	private DeepConfiguration createGridLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Grid.class)
		.addAttachingAttribute("BaseElementMargin(10)");
	}
	
	//method
	private DeepConfiguration createLevel1HeaderLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(30)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepConfiguration createLevel2HeaderLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(20)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepConfiguration createOverallContainerLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(ContainerRole.OVERALL_CONTAINTER)
		.addAttachingAttribute(
			"ProposalWidth(1000)",
			"ProposalHeight(600)",
			"BaseBackground(Color(0x202020))",
			"BasePadding(10)"
		);
	}
	
	//method
	private DeepConfiguration createTextBoxLook() {
		return
		new DeepConfiguration()
		.setSelectorType("TextBox")
		.addAttachingAttribute(
			"BaseBackground(Color(0x101010))",
			"BasePadding(5)"
		);
	}
	
	//method
	private DeepConfiguration createTextItemMenuLook() {
		return
		new DeepConfiguration()
		.setSelectorType(ItemMenu.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"MaxHeight(200)",
			"BaseBackground(Color(0x101010))"
		);
	}
	
	//method
	private DeepConfiguration createTitleLook() {
		return
		new DeepConfiguration()
		.addSelectorRole(LabelRole.TITLE)
		.addAttachingAttribute(
			"BaseTopPadding(20)",
			"BaseBottomPadding(50)",
			"BaseTextSize(40)",
			"BaseTextColor(0xC0C0C0)"
		);
	}
	
	//method
	private DeepConfiguration createWidgetLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Widget.class)
		.addAttachingAttribute(
			"BaseFont(Verdana)",
			"BaseTextSize(20)",
			"BaseTextColor(0xF0F0F0)"
		);
	}
}
