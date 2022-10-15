//package declaration
package ch.nolix.business.serverdashboard;

import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.TextRole;

//TODO: Use IControls only.
//class
public final class ServerDashboardStyleCreator {
	
	//static attribute
	public static final ServerDashboardStyleCreator INSTANCE = new ServerDashboardStyleCreator();
	
	//constructor
	private ServerDashboardStyleCreator() {}
	
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
		.addAttachingAttribute("ProposalWidth(80%)", "BaseTopPadding(50)", "BaseElementMargin(50)");
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
		.setSelectorType(Text.class)
		.addSelectorRole(TextRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(20)");
	}
}
