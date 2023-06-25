//package declaration
package ch.nolix.business.serverdashboardapplication.view;

//own imports
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.FloatContainer;
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
			createMainContentFloatContainerStyle(),
			createImageControlStyle(),
			createLevel1HeaderTextStyle()
		);
	}
	
	//method
	private DeepStyle createImageControlStyle() {
		return
		new DeepStyle()
		.setSelectorType(ImageControl.class)
		.addAttachingAttribute("CursorIcon(Hand)", "BaseOpacity(75%)", "HoverOpacity(25%)");
	}
	
	//method
	private DeepStyle createMainContentFloatContainerStyle() {
		return
		new DeepStyle()
		.setSelectorType(FloatContainer.class)
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("MaxWidth(80%)", "BaseTopPadding(10)", "BaseChildControlMargin(10)");
	}
	
	//method
	private DeepStyle createLayerStyle() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentPosition(TOP)");
	}
	
	//method
	private DeepStyle createLevel1HeaderTextStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(20)");
	}
}
