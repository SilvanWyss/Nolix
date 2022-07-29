//package declaration
package ch.nolix.template.guistyle;

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
public final class AnthrazitGUIStyleCreator {
	
	//static attribute
	public static final AnthrazitGUIStyleCreator INSTANCE = new AnthrazitGUIStyleCreator();
	
	//constructor
	private AnthrazitGUIStyleCreator() {}
	
	//method
	public Style createGUIStyle() {
		return
		new Style()
		.addAttachingAttribute("BackgroundColor(0x101010)")
		.addConfiguration(
			createWidgetStyle(),
			createBorderWidgetStyle(),
			createOverallContainerStyle(),
			createDialogStyle(),
			createDialogContainerStyle(),
			createGridStyle(),
			createStackStyle(),
			createTitleStyle(),
			createLevel1HeaderStyle(),
			createLevel2HeaderStyle(),
			createCheckboxStyle(),
			createButtonStyle(),
			createDownloaderStyle(),
			createTextBoxStyle(),
			createItemMenuStyle(),
			createConsoleStyle()
		);
	}
	
	//method
	private DeepStyle createBorderWidgetStyle() {
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
	private DeepStyle createButtonStyle() {
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
	private DeepStyle createCheckboxStyle() {
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
	private DeepStyle createConsoleStyle() {
		return
		new DeepStyle()
		.setSelectorType(Console.class)
		.addAttachingAttribute("BaseBackground(Color(0x101010))");
	}
	
	//method
	private DeepStyle createDialogStyle() {
		return
		new DeepStyle()
		.addSelectorRole(LayerRole.DIALOG_LAYER)
		.addAttachingAttribute("Background(Color(" + Color.fromString("0x101010F0").getHexadecimalValue() + "))");
	}
	
	//method
	private DeepStyle createDialogContainerStyle() {
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
	private DeepStyle createDownloaderStyle() {
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
	private DeepStyle createGridStyle() {
		return
		new DeepStyle()
		.setSelectorType(Grid.class)
		.addAttachingAttribute("BaseElementMargin(10)");
	}
	
	//method
	private DeepStyle createItemMenuStyle() {
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
	private DeepStyle createLevel1HeaderStyle() {
		return
		new DeepStyle()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(30)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepStyle createLevel2HeaderStyle() {
		return
		new DeepStyle()
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute(
			"BaseTextSize(20)",
			"BaseBottomPadding(10)"
		);
	}
	
	//method
	private DeepStyle createOverallContainerStyle() {
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
	
	private DeepStyle createStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(Stack.class)
		.addAttachingAttribute("ElementMargin(10)");
	}
	
	//method
	private DeepStyle createTextBoxStyle() {
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
	private DeepStyle createTitleStyle() {
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
	private DeepStyle createWidgetStyle() {
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
