//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Link;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.container.Container;
import ch.nolix.system.webgui.container.Grid;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.LinearContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class DarkModeStyleCreator {
	
	//method
	public Style createDarkModeStyle() {
		return
		new Style()
		.addAttachingAttribute("Background(Color(0x202020))")
		.addConfiguration(
			createControlStyle(),
			createLinearContainerStyle(),
			createGridStyle(),
			createLinkStyle(),
			createButtonStyle(),
			createTextboxStyle(),
			createDropdownMenuStyle(),
			createValidationLabelStyle(),
			createOverallVerticalStackStyle(),
			createHeaderHorizontalStackStyle(),
			createMainContentContainerStyle(),
			createFooterHorizontalStackStyle(),
			createTitleLabelStyle(),
			createLevel1HeaderLabelStyle(),
			createDialogLayerStyle(),
			createDialogContainerStyle()
		);
	}
	
	private DeepStyle createControlStyle() {
		return
		new DeepStyle()
		.setSelectorType(Control.class)
		.addAttachingAttribute("BaseTextColor(0xC0C0C0)");
	}
	
	//method
	private DeepStyle createLinearContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(LinearContainer.class)
		.addAttachingAttribute("BaseChildControlMargin(10)");
	}
	
	//method
	private DeepStyle createGridStyle() {
		return
		new DeepStyle()
		.setSelectorType(Grid.class)
		.addAttachingAttribute(
			"BaseChildControlMargin(10)",
			"BaseGridThickness(0)"
		);
	}
	
	//method
	private DeepStyle createLinkStyle() {
		return
		new DeepStyle()
		.setSelectorType(Link.class)
		.addAttachingAttribute(
			"BaseTextColor(Blue)",
			"HoverTextColor(DarkBlue)"
		);
	}
	
	//method
	private DeepStyle createButtonStyle() {
		return
		new DeepStyle()
		.setSelectorType(Button.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"BaseBorderThickness(1)",
			"BaseBorderColor(Grey)",
			"HoverBorderColor(White)",
			"BaseLeftPadding(10)",
			"BaseRightPadding(10)",
			"BaseTextColor(Grey)",
			"HoverTextColor(White)"
		);
	}
	
	//method
	private DeepStyle createTextboxStyle() {
		return
		new DeepStyle()
		.setSelectorType(Textbox.class)
		.addAttachingAttribute(
			"BaseWidth(200)",
			"BaseBackground(Color(Black))"
		);
	}
	
	//method
	private DeepStyle createDropdownMenuStyle() {
		return
		new DeepStyle()
		.setSelectorType(DropdownMenu.class)
		.addAttachingAttribute(
			"BaseWidth(200)",
			"BaseBackground(Color(Black))"
		);
	}
	
	//method
	private DeepStyle createValidationLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(ValidationLabel.class)
		.addAttachingAttribute("BaseTextColor(Orange)");
	}
	
	//method
	private DeepStyle createOverallVerticalStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(VerticalStack.class)
		.addSelectorRole(ContainerRole.OVERALL_CONTAINER)
		.addAttachingAttribute(
			"BaseWidth(80%)",
			"MinHeight(80%)",
			"BasePadding(20)",
			"BaseChildControlMargin(20)"
		);
	}
	
	//method
	private DeepStyle createHeaderHorizontalStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(HorizontalStack.class)
		.addSelectorRole(ContainerRole.HEADER_CONTAINER)
		.addAttachingAttribute(
			"ContentAlignment(BOTTOM)",
			"BaseChildControlMargin(50)"
		);
	}
	
	//method
	private DeepStyle createMainContentContainerStyle() {
		return
		new DeepStyle()
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("MinHeight(500)");
	}
	
	//method
	private DeepStyle createFooterHorizontalStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(HorizontalStack.class)
		.addSelectorRole(ContainerRole.FOOTER_CONTAINER)
		.addAttachingAttribute(
			"ContentAlignment(BOTTOM)",
			"BaseChildControlMargin(100)",
			"BaseTextSize(15)"
		);
	}
	
	//method
	private DeepStyle createTitleLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.TITLE)
		.addAttachingAttribute("BaseTextSize(50)", "BaseTextColor(White)");
	}
	
	//method
	private DeepStyle createLevel1HeaderLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(30)", "BaseTextColor(White)");
	}
	
	//method
	private DeepStyle createDialogLayerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addSelectorRole(LayerRole.DIALOG_LAYER)
		.addAttachingAttribute(
			"Background(Color(0x808080E0))",
			"ContentAlignment(CENTER)"
		);
	}
	
	//method
	private DeepStyle createDialogContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Container.class)
		.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
		.addAttachingAttribute(
			"MinWidth(500)",
			"MinHeight(200)",
			"BaseBackground(Color(0x202020E0))",
			"BasePadding(20)"
		);
	}
}
