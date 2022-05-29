//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widgetgui.Layer;

//class
public final class ServerDashboardLookCreator {
	
	//static attribute
	public static final ServerDashboardLookCreator INSTANCE = new ServerDashboardLookCreator();
	
	//constructor
	private ServerDashboardLookCreator() {}
	
	//method
	public Configuration createServerDashboardLook() {
		return
		new Configuration()
		.addConfiguration(
			createLayerLook(),
			createMainContentFloatContainerLook(),
			createImageWidgetLook(),
			createLevel1HeaderLabelLook()
		);
	}
	
	//method
	private DeepConfiguration createImageWidgetLook() {
		return
		new DeepConfiguration()
		.setSelectorType(ImageWidget.class)
		.addAttachingAttribute("CursorIcon(Hand)", "BaseOpacity(75%)", "HoverOpacity(25%)");
	}
	
	//method
	private DeepConfiguration createMainContentFloatContainerLook() {
		return
		new DeepConfiguration()
		.setSelectorType(FloatContainer.class)
		.addSelectorRole(ContainerRole.MAIN_CONTENT_CONTAINER)
		.addAttachingAttribute("ProposalWidth(80%)", "BaseTopPadding(50)", "BaseElementMargin(50)");
	}
	
	//method
	private DeepConfiguration createLayerLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Layer.class)
		.addAttachingAttribute("ContentPosition(Top)");
	}
	
	//method
	private DeepConfiguration createLevel1HeaderLabelLook() {
		return
		new DeepConfiguration()
		.setSelectorType(Label.class)
		.addSelectorRole(LabelRole.LEVEL1_HEADER)
		.addAttachingAttribute("BaseTextSize(20)");
	}
}
