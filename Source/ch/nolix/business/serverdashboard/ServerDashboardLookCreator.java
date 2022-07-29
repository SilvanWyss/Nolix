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
public final class ServerDashboardLookCreator {
	
	//static attribute
	public static final ServerDashboardLookCreator INSTANCE = new ServerDashboardLookCreator();
	
	//constructor
	private ServerDashboardLookCreator() {}
	
	//method
	public Style createServerDashboardLook() {
		return
		new Style()
		.addConfiguration(
			createLayerLook(),
			createMainContentFloatContainerLook(),
			createImageWidgetLook(),
			createLevel1HeaderLabelLook()
		);
	}
	
	//method
	private DeepStyle createImageWidgetLook() {
		return
		new DeepStyle()
		.setSelectorType(ImageWidget.class)
		.addAttachingAttribute("CursorIcon(Hand)", "BaseOpacity(75%)", "HoverOpacity(25%)");
	}
	
	//method
	private DeepStyle createMainContentFloatContainerLook() {
		return
		new DeepStyle()
		.setSelectorType(FloatContainer.class)
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("ProposalWidth(80%)", "BaseTopPadding(50)", "BaseElementMargin(50)");
	}
	
	//method
	private DeepStyle createLayerLook() {
		return
		new DeepStyle()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentPosition(TOP)");
	}
	
	//method
	private DeepStyle createLevel1HeaderLabelLook() {
		return
		new DeepStyle()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(20)");
	}
}
