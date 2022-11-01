//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.controlapi.TextRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
final class WebApplicationControlFactory {
	
	//constant
	public static final int LOGO_IMAGE_WIDTH = 200;
	
	//constant
	public static final int LOGO_IMAGE_HEIGHT = 150;
	
	//constant
	public static final IImage DEFAULT_LOGO =
	MutableImage.withWidthAndHeightAndColor(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT, Color.GREY);
	
	//static attribute
	public static final WebApplicationControlFactory INSTANCE = new WebApplicationControlFactory();
	
	//constructor
	private WebApplicationControlFactory() {}
	
	//method
	public IControl<?, ?> createWebApplicationControl(final IApplicationSheet pGUIApplicationSheet) {
		
		final var guiApplicationVerticalStack =
		new VerticalStack()
		.addControl(
			createApplicationNameLabel(pGUIApplicationSheet),
			createLogoImageControl(pGUIApplicationSheet)
		);
		
		if (pGUIApplicationSheet.hasApplicationDescription()) {
			guiApplicationVerticalStack.addControl(createApplicationDescriptionLabel(pGUIApplicationSheet));
		}
		
		return guiApplicationVerticalStack;
	}
	
	//method
	private IControl<?, ?> createApplicationNameLabel(final IApplicationSheet pGUIApplicationSheet) {
		return new Text().setRole(TextRole.LEVEL1_HEADER).setText(pGUIApplicationSheet.getApplicationName());
	}
	
	//method
	private IControl<?, ?> createLogoImageControl(final IApplicationSheet pGUIApplicationSheet) {
		return
		new ImageControl()
		.setImage(getApplicationLogoOrDefaultLogo(pGUIApplicationSheet))
		.setLeftMouseButtonPressAction(
			i ->
			i
			.getRefParentGUI()
			.onFrontEnd()
			.redirectTo(pGUIApplicationSheet.getApplicationTarget())
		);
	}
	
	//method
	private IImage getApplicationLogoOrDefaultLogo(final IApplicationSheet pGUIApplicationSheet) {

		if (!pGUIApplicationSheet.hasApplicationLogo()) {
			return DEFAULT_LOGO;
		}
		
		return pGUIApplicationSheet.getApplicationLogo().withWidthAndHeight(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT);
	}
	
	//method
	private IControl<?, ?> createApplicationDescriptionLabel(final IApplicationSheet pGUIApplicationSheet) {
		return new Text().setText(pGUIApplicationSheet.getApplicationDescription());
	}
}
