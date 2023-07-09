//package declaration
package ch.nolix.tech.serverdashboardapplication.view;

//own imports
import ch.nolix.system.element.style.BaseStyle;
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.atomiccontrol.ImageControl;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.LabelRole;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;

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
	private DeepSelectingStyle createLayerStyle() {
		return
		new DeepSelectingStyle()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentAlignment(TOP)");
	}
	
	//method
	private DeepSelectingStyle createImageControlStyle() {
		return
		new DeepSelectingStyle()
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
	private DeepSelectingStyle createOverallVerticalStackContainerStyle() {
		return
		new DeepSelectingStyle()
		.setSelectorType(VerticalStack.class)
		.addSelectorRole(ContainerRole.OVERALL_CONTAINER)
		.addAttachingAttribute(
			"ContentAlignment(CENTER)",
			"BaseChildControlMargin(50)",
			"BaseTextColor(0x202020)"
		);
	}
	
	//method
	private DeepSelectingStyle createMainContentFloatContainerStyle() {
		return
		new DeepSelectingStyle()
		.setSelectorType(FloatContainer.class)
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("BaseChildControlMargin(20)")
		.addConfiguration(
			new DeepSelectingStyle()
			.setSelectorType(VerticalStack.class)
			.addAttachingAttribute("BaseChildControlMargin(10)")
		);
	}
	
	//method
	private BaseStyle<?> createTitleLabelStyle() {
		return
		new DeepSelectingStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.TITLE)
		.addAttachingAttribute("BaseTextSize(50)");
	}
	
	//method
	private DeepSelectingStyle createLevel1HeaderLabelStyle() {
		return
		new DeepSelectingStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(18)");
	}
}
