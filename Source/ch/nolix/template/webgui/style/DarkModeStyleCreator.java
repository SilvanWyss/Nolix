//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.container.Container;
import ch.nolix.system.webgui.container.GridContainer;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.system.webgui.control.ValidationLabel;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.LinearContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
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
			createGridContainerStyle(),
			createButtonStyle(),
			createTextboxStyle(),
			createDropdownMenuStyle(),
			createValidationLabelStyle(),
			createOverallVerticalStackStyle(),
			createHeaderHorizontalStackStyle(),
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
		.addAttachingAttribute("BaseTextColor(White)");
	}
	
	//method
	private DeepStyle createLinearContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(LinearContainer.class)
		.addAttachingAttribute("BaseChildControlMargin(10)");
	}
	
	//method
	private DeepStyle createGridContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(GridContainer.class)
		.addAttachingAttribute(
			"BaseChildControlMargin(10)",
			"BaseGridThickness(0)"
		);
	}
	
	//method
	private DeepStyle createButtonStyle() {
		return
		new DeepStyle()
		.setSelectorType(Button.class)
		.addAttachingAttribute(
			"BaseBackground(Color(0x202020))",
			"HoverBackground(Color(0x000000))",
			"BasePadding(2)"
		);
	}
	
	//method
	private DeepStyle createTextboxStyle() {
		return
		new DeepStyle()
		.setSelectorType(Textbox.class)
		.addAttachingAttribute(
			"BaseWidth(200)",
			"BaseBackground(Color(0x202020))",
			"HoverBackground(Color(0x000000))",
			"FocusBackground(Color(0x000000))"
		);
	}
	
	//method
	private DeepStyle createDropdownMenuStyle() {
		return
		new DeepStyle()
		.setSelectorType(DropdownMenu.class)
		.addAttachingAttribute(
			"BaseWidth(200)",
			"BaseBackground(Color(0x202020))",
			"HoverBackground(Color(0x000000))",
			"FocusBackground(Color(0x000000))"
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
			"MinHeight(100%)",
			"BaseBackground(Color(0x404040))",
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
	private DeepStyle createTitleLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.TITLE)
		.addAttachingAttribute("BaseTextSize(50)");
	}
	
	//method
	private DeepStyle createLevel1HeaderLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(30)");
	}
	
	//method
	private DeepStyle createDialogLayerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addSelectorRole(LayerRole.DIALOG_LAYER)
		.addAttachingAttribute(
			"Background(Color(0x808080E0))",
			"ContentPosition(CENTER)"
		);
	}
	
	//method
	private DeepStyle createDialogContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Container.class)
		.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
		.addAttachingAttribute(
			"BaseBackground(Color(0x404040E0))",
			"BasePadding(50)"
		);
	}
}
