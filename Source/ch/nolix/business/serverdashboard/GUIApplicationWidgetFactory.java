//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.core.net.targetapi.IApplicationTarget;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
final class GUIApplicationWidgetFactory {
	
	//constant
	public static final IImage<?> DEFAULT_LOGO = MutableImage.withWidthAndHeightAndColor(200, 150, Color.GREY);
	
	//static attribute
	public static final GUIApplicationWidgetFactory INSTANCE = new GUIApplicationWidgetFactory();
	
	//constructor
	private GUIApplicationWidgetFactory() {}
	
	//method
	public Widget<?, ?> createGUIApplicationWidget(final IApplicationSheet pGUIApplicationSheet) {
		
		final var guiApplicationVerticalStack =
		new VerticalStack()
		.add(
			createApplicationNameLabel(pGUIApplicationSheet),
			createLogoImageWidget(pGUIApplicationSheet)
		);
		
		if (pGUIApplicationSheet.hasApplicationDescription()) {
			guiApplicationVerticalStack.add(createApplicationDescriptionLabel(pGUIApplicationSheet));
		}
		
		return guiApplicationVerticalStack;
	}
	
	//method
	private Widget<?, ?> createApplicationNameLabel(final IApplicationSheet pGUIApplicationSheet) {
		return new Label().setRole(LabelRole.LEVEL1_HEADER).setText(pGUIApplicationSheet.getApplicationName());
	}
	
	//method
	private Widget<?, ?> createLogoImageWidget(final IApplicationSheet pGUIApplicationSheet) {
		return
		new ImageWidget()
		.setLeftMouseButtonPressAction(
			i ->
			i
			.getParentGUI()
			.onFrontEnd()
			.openNewTabWithURL(getURL(pGUIApplicationSheet.getApplicationTarget())))
		.setImage(getApplicationLogoOrDefaultLogo(pGUIApplicationSheet));
	}
	
	//method
	private String getURL(final IApplicationTarget applicationTarget) {
		return
		applicationTarget.getIpOrAddressName()
		+ ":"
		+ applicationTarget.getPort()
		+ "/"
		+ applicationTarget.getApplicationName();
	}
	
	//method
	private IImage<?> getApplicationLogoOrDefaultLogo(final IApplicationSheet pGUIApplicationSheet) {

		if (!pGUIApplicationSheet.hasApplicationLogo()) {
			return DEFAULT_LOGO;
		}
			
		return pGUIApplicationSheet.getApplicationLogo();
	}
	
	//method
	private Widget<?, ?> createApplicationDescriptionLabel(final IApplicationSheet pGUIApplicationSheet) {
		return new Label().setText(pGUIApplicationSheet.getApplicationDescription());
	}
}
