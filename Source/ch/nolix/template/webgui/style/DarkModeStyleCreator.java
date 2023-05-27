//package declaration
package ch.nolix.template.webgui.style;

//own imports
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.container.GridContainer;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.itemmenu.DropdownMenu;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class DarkModeStyleCreator {
	
	//constant
	public static final DarkModeStyleCreator INSTANCE = new DarkModeStyleCreator();
	
	//constructor
	private DarkModeStyleCreator() {}
	
	//method
	public Style createDarkModeStyle() {
		return
		new Style()
		.addAttachingAttribute(
			"Background(Color(0x202020))"
		)
		.addConfiguration(
			createVerticalStackStyle(),
			createGridContainerStyle(),
			createTitleStyle(),
			createButtonStyle(),
			createDropdownMenuStyle(),
			createDialogLayerStyle(),
			createOverallVerticalStackStyle(),
			createMainLabelStyle()
			//createActionButtonStyle()
		);
	}
	
	//method
	private DeepStyle createVerticalStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(VerticalStack.class)
		.addAttachingAttribute(
			
		);
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
	private DeepStyle createTitleStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(
			LabelRole.TITLE
		)
		.addAttachingAttribute(
			"BaseTextSize(50)"
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
			"BasePadding(5)"
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
	private DeepStyle createDialogLayerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addSelectorRole(
			LayerRole.DIALOG_LAYER
		)
		.addAttachingAttribute(
			"Opacity(90%)",
			"Background(Color(0x808080))",
			"ContentPosition(CENTER)"
		);
	}

	//method
	private DeepStyle createOverallVerticalStackStyle() {
		return
		new DeepStyle()
		.setSelectorType(VerticalStack.class)
		.addSelectorRole(
			ContainerRole.OVERALL_CONTAINER
		)
		.addAttachingAttribute(
			"BaseWidth(80%)",
			"BaseBackground(Color(0x404040))",
			"BasePadding(20)",
			"BaseChildControlMargin(20)",
			"BaseTextColor(White)"
		);
	}

	//method
	private DeepStyle createMainLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(
			LabelRole.MAIN_LABEL
		)
		.addAttachingAttribute(
			"BaseTextSize(50)",
			"BaseTextColor(White)"
		);
	}
}
