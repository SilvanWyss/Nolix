//package declaration
package ch.nolix.template.GUILook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.GUI.LayerRole;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.widget.BorderWidget;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.CheckBox;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.TextItemMenu;

//class
public final class AnthrazitGUILook extends Configuration {
	
	//nested class
	private static final class BorderWidgetLook extends DeepConfiguration {
		
		//constructor
		public BorderWidgetLook() {
			
			setSelectorType(BorderWidget.class);
			
			addAttachingAttribute(
				"BaseBaseScrollBarLook(ScrollBarColor(0x101010),ScrollCursorColor(0x000000))",
				"BaseHoverScrollBarLook(ScrollBarColor(0x101010),ScrollCursorColor(0xE0E000))",
				"BaseSelectionScrollBarLook(ScrollBarColor(0x101010),ScrollCursorColor(0xE0E000))"
			);
		}
	}
	
	//nested class
	private static final class ButtonLook extends DeepConfiguration {
		
		//constructor
		public ButtonLook() {
			
			setSelectorType(Button.class);
			
			addAttachingAttribute(
				"MinWidth(200)",
				"CursorIcon(Hand)",
				"ContentPosition(Center)",
				"BaseLeftPadding(5)",
				"BaseRightPadding(5)",
				"BaseBackgroundColor(0x101010)",
				"HoverBackgroundColor(0xE0E000)",
				"HoverTextColor(0x000000)"
			);
		}
	}
	
	//nested class
	private static final class CheckboxLook extends DeepConfiguration {
		
		//constructor
		public CheckboxLook() {
			
			setSelectorType(CheckBox.class);
			
			addAttachingAttribute(
				"CursorIcon(Hand)",
				"BaseBackgroundColor(0x101010)",
				"HoverBackgroundColor(0xE0E000)"
			);
		}
	}
	
	//nested class
	private static final class ConsoleLook extends DeepConfiguration {
		
		//constructor
		public ConsoleLook() {
			
			setSelectorType(Console.class);
			
			addAttachingAttribute("BaseBackgroundColor(0x101010)");
		}
	}
	
	//nested class
	private static final class DialogLook extends DeepConfiguration {
		
		//constructor
		public DialogLook() {
			
			addSelectorRole(LayerRole.DIALOG_LAYER);
			
			addAttachingAttribute(
				"BackgroundColor(" + new Color("0x101010F0").getHexadecimalValue() + ")"
			);
			
			addConfiguration(new DialogContainerLook());
		}
	}
	
	private static final class DialogContainerLook extends DeepConfiguration {
		
		//constructor
		public DialogContainerLook() {
			
			addSelectorRole(ContainerRole.DialogContainer);
			
			addAttachingAttribute(
				"BaseBackgroundColor(0x202020)",
				"BasePadding(50)",
				"ContentPosition(Top)",
				"ElementMargin(100)"
			);
		}
	}
	
	//nested class
	private static final class DownoaderLook extends DeepConfiguration {
		
		//constructor
		public DownoaderLook() {
			
			setSelectorType(Downloader.class);
			
			addAttachingAttribute(
				"MinWidth(200)",
				"CursorIcon(Hand)",
				"ContentPosition(Center)",
				"BaseLeftPadding(5)",
				"BaseRightPadding(5)",
				"BaseBackgroundColor(0x101010)",
				"HoverBackgroundColor(0xE0E000)",
				"HoverTextColor(0x000000)"
			);
		}
	}
	
	//nested class
	private static final class GridLook extends DeepConfiguration {
		
		//constructor
		public GridLook() {
			
			setSelectorType(Grid.class);
			
			addAttachingAttribute("BaseElementMargin(10)");
		}
	}
	
	//nested class
	private static final class Level1HeaderLook extends DeepConfiguration {
		
		//constructor
		public Level1HeaderLook() {
			
			addSelectorRole(LabelRole.Level1Header);
			
			addAttachingAttribute(
				"BaseTextSize(30)",
				"BaseBottomPadding(10)"
			);
		}
	}
	
	//nested class
	private static final class Level2HeaderLook extends DeepConfiguration {
		
		//constructor
		public Level2HeaderLook() {
			
			addSelectorRole(LabelRole.Level1Header);
			
			addAttachingAttribute(
				"BaseTextSize(20)",
				"BaseBottomPadding(10)"
			);
		}
	}
	
	//nested class
	private static final class OverallContainerLook extends DeepConfiguration {
		
		public OverallContainerLook() {
			
			addSelectorRole(ContainerRole.OverallContainer);
			
			addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(600)",
				"BaseBackgroundColor(0x202020)",
				"BasePadding(10)"
			);
		}
	}
	
	//nested class
	private static final class TextBoxLook extends DeepConfiguration {
		
		//constructor
		public TextBoxLook() {
			
			setSelectorType("TextBox");
			
			addAttachingAttribute(
				"BaseBackgroundColor(0x101010)",
				"BasePadding(5)"
			);
		}
	}
		
	//nested class
	private static final class TextItemMenuLook extends DeepConfiguration {
		
		//constructor
		public TextItemMenuLook() {
			
			setSelectorType(TextItemMenu.class);
			
			addAttachingAttribute(
				"MinWidth(200)",
				"MaxHeight(200)",
				"BaseBackgroundColor(0x101010)"
			);
		}
	}
	
	//nested class
	private static final class TitleLook extends DeepConfiguration {
		
		//constructor
		public TitleLook() {
			
			addSelectorRole(LabelRole.Title);
			
			addAttachingAttribute(
				"BaseTopPadding(20)",
				"BaseBottomPadding(50)",
				"BaseTextSize(40)",
				"BaseTextColor(0xC0C0C0)"
			);
		}
	}
	
	//nested class
	private static final class WidgetLook extends DeepConfiguration {
		
		//constructor
		public WidgetLook() {
			
			setSelectorType(Widget.class);
			
			addAttachingAttribute(
				"BaseFont(Verdana)",
				"BaseTextSize(20)",
				"BaseTextColor(0xF0F0F0)"
			);
		}
	}
	
	//constructor
	public AnthrazitGUILook() {
		
		addAttachingAttribute("BackgroundColor(0x101010)");
		
		addConfiguration(
			new WidgetLook(),
			new BorderWidgetLook(),
			new OverallContainerLook(),
			new DialogLook(),
			new GridLook(),
			new TitleLook(),
			new Level1HeaderLook(),
			new Level2HeaderLook(),
			new CheckboxLook(),
			new ButtonLook(),
			new DownoaderLook(),
			new TextBoxLook(),
			new TextItemMenuLook(),	
			new ConsoleLook()
		);
	}
}
