//package declaration
package ch.nolix.business.serverdashboardapplication.view;

//own imports
import ch.nolix.system.element.style.BaseStyle;
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;

//class
final class ServerDashboardStyleCreator {
	
	//method
	public Style createServerDashboardStyle() {
		return
		new Style()
		.addConfiguration(
			createLayerStyle(),
			createImageControlStyle(),
			createOverallVerticalStackContainerStyle(),
			createMainContentFloatContainerStyle(),
			createTitleLabelStyle(),
			createLevel1HeaderLabelStyle()
		);
	}
	
	//method
	private DeepStyle createLayerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentPosition(TOP)");
	}
	
	//method
	private DeepStyle createImageControlStyle() {
		return
		new DeepStyle()
		.setSelectorType(ImageControl.class)
		.addAttachingAttribute(
			"BaseWidth(250)",
			"BaseHeight(200)",
			"CursorIcon(Hand)",
			"BaseOpacity(50%)",
			"HoverOpacity(90%)"
		);
	}
	
	//method
	private DeepStyle createOverallVerticalStackContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(VerticalStack.class)
		.addSelectorRole(ContainerRole.OVERALL_CONTAINER)
		.addAttachingAttribute(
			"ContentAlignment(CENTER)",
			"BaseChildControlMargin(50)",
			"BaseTextColor(0x202020)"
		);
	}
	
	//method
	private DeepStyle createMainContentFloatContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(FloatContainer.class)
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("BaseChildControlMargin(20)")
		.addConfiguration(
			new DeepStyle()
			.setSelectorType(VerticalStack.class)
			.addAttachingAttribute("BaseChildControlMargin(10)")
		);
	}
	
	//method
	private BaseStyle<?> createTitleLabelStyle() {
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
		.addAttachingAttribute("BaseTextSize(18)");
	}
}
