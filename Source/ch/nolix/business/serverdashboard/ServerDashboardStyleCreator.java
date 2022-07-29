//package declaration
package ch.nolix.business.serverdashboard;

import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;

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
			createImageWidgetStyle(),
			createLevel1HeaderLabelStyle()
		);
	}
	
	//method
	private DeepStyle createImageWidgetStyle() {
		return
		new DeepStyle()
		.setSelectorType(ImageWidget.class)
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
	private DeepStyle createLevel1HeaderLabelStyle() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(20)");
	}
}
