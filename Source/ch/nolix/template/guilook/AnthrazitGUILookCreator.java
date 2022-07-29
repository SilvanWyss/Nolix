//package declaration
package ch.nolix.template.guilook;

import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.style.DeepStyle;
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
	public Style createGUILook() {
		return
		new Style()
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
	private DeepStyle createBorderWidgetLook() {
		return
		new DeepStyle()
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
	private DeepStyle createButtonLook() {
		return
		new DeepStyle()
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
	private DeepStyle createCheckboxLook() {
		return
		new DeepStyle()
		.setSelectorType(CheckBox.class)
		.addAttachingAttribute(
			"CursorIcon(Hand)",
			"BaseBackground(Color(0x101010))",
			"HoverBackground(Color(0xE0E000))"
		);
	}
	
	//method
	private DeepStyle createConsoleLook() {
		return
		new DeepStyle()
		.setSelectorType(Console.class)
		.addAttachingAttribute("BaseBackground(Color(0x101010))");
	}
	
	//method
	private DeepStyle createDialogLook() {
		return
		new DeepStyle()
		.addSelectorRole(LayerRole.DIALOG_LAYER)
		.addAttachingAttribute("Background(Color(" + Color.fromString("0x101010F0").getHexadecimalValue() + "))");
	}
	
	//method
	private DeepStyle createDialogContainerLook() {
		return
		new DeepStyle()
		.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
		.addAttachingAttribute(
			"BaseBackground(Color(0x202020))",
			"BasePadding(50)",
			"ContentPosition(Top)"
		);
	}
	
	//method
	private DeepStyle createDownloaderLook() {
		return
		new DeepStyle()
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
	private DeepStyle createGridLook() {
		return
		new DeepStyle()
		.setSelectorType(Grid.class)
		.addAttachingAttribute("BaseElementMargin(10)");
	}
	
	//method
	private DeepStyle createItemMenuLook() {
		return
		new DeepStyle()
		.setSelectorType(ItemMenu.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"MaxHeight(200)",
			"BaseBackground(Color(0x101010))"
		);
	}
	
	//method
	private DeepStyle createLevel1HeaderLook() {
		return
		new DeepStyle()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(30)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepStyle createLevel2HeaderLook() {
		return
		new DeepStyle()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(20)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepStyle createOverallContainerLook() {
		return
		new DeepStyle()
		.addSelectorRole(ContainerRole.OVERALL_CONTAINTER)
		.addAttachingAttribute(
			"ProposalWidth(1000)",
			"ProposalHeight(600)",
			"BaseBackground(Color(0x202020))",
			"BasePadding(10)"
		);
	}
	
	private DeepStyle createStackLook() {
		return
		new DeepStyle()
		.setSelectorType(Stack.class)
		.addAttachingAttribute("ElementMargin(10)");
	}
	
	//method
	private DeepStyle createTextBoxLook() {
		return
		new DeepStyle()
		.setSelectorType("TextBox")
		.addAttachingAttribute(
			"ProposalWidth(200)",
			"BaseBackground(Color(0x101010))",
			"BasePadding(5)"
		);
	}
	
	//method
	private DeepStyle createTitleLook() {
		return
		new DeepStyle()
		.addSelectorRole(LabelRole.TITLE)
		.addAttachingAttribute(
			"BaseTopPadding(20)",
			"BaseBottomPadding(50)",
			"BaseTextSize(40)",
			"BaseTextColor(0xC0C0C0)"
		);
	}
	
	//method
	private DeepStyle createWidgetLook() {
		return
		new DeepStyle()
		.setSelectorType(Widget.class)
		.addAttachingAttribute(
			"BaseFont(VERDANA)",
			"BaseTextSize(20)",
			"BaseTextColor(0xF0F0F0)"
		);
	}
}
