//package declaration
package ch.nolix.template.guilook;

import ch.nolix.system.element.configuration.Configuration;
import ch.nolix.system.element.configuration.DeepConfiguration;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.Grid;
import ch.nolix.system.gui.containerwidget.Stack;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.CheckBox;
import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.ItemMenu;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.widgetguiapi.LayerRole;

//class
public final class AnthrazitGUILookCreator {
	
	//static attribute
	public static final AnthrazitGUILookCreator INSTANCE = new AnthrazitGUILookCreator();
	
	//constructor
	private AnthrazitGUILookCreator() {}
	
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
			createStackLook(),
			createTitleLook(),
			createLevel1HeaderLook(),
			createLevel2HeaderLook(),
			createCheckboxLook(),
			createButtonLook(),
			createDownloaderLook(),
			createTextBoxLook(),
			createItemMenuLook(),
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
			"BaseScrollBarHoverColor(0x101010)",
			"BaseScrollBarMoveColor(0x101010)",
			"BaseScrollCursorColor(0x404040)",
			"BaseScrollCursorMoveColor(0xE0E000)",
			"BaseHoverScrollCursorHoverColor(0xE0E000)"
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
			"ContentPosition(CENTER)",
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
			"ContentPosition(Top)"
		);
	}
	
	//method
	private DeepConfiguration createDownloaderLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Downloader.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"CursorIcon(Hand)",
			"ContentPosition(CENTER)",
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
	private DeepConfiguration createItemMenuLook() {
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
	
	private DeepConfiguration createStackLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Stack.class)
		.addAttachingAttribute("ElementMargin(10)");
	}
	
	//method
	private DeepConfiguration createTextBoxLook() {
		return
		new DeepConfiguration()
		.setSelectorType("TextBox")
		.addAttachingAttribute(
			"ProposalWidth(200)",
			"BaseBackground(Color(0x101010))",
			"BasePadding(5)"
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
			"BaseFont(VERDANA)",
			"BaseTextSize(20)",
			"BaseTextColor(0xF0F0F0)"
		);
	}
}
