//package declaration
package ch.nolix.business.serverdashboard;

//own imports
import ch.nolix.businessapi.serverdashboardaccessapi.IApplicationSheet;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.controlapi.TextRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
final class GUIApplicationWidgetFactory {
	
	//constant
	public static final int LOGO_IMAGE_WIDTH = 200;
	
	//constant
	public static final int LOGO_IMAGE_HEIGHT = 150;
	
	//constant
	public static final IImage DEFAULT_LOGO =
	MutableImage.withWidthAndHeightAndColor(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT, Color.GREY);
	
	//static attribute
	public static final GUIApplicationWidgetFactory INSTANCE = new GUIApplicationWidgetFactory();
	
	//constructor
	private GUIApplicationWidgetFactory() {}
	
	//method
	public IControl<?, ?> createGUIApplicationWidget(final IApplicationSheet pGUIApplicationSheet) {
		
		final var guiApplicationVerticalStack =
		new VerticalStack()
		.addControl(
			createApplicationNameLabel(pGUIApplicationSheet),
			createLogoImageWidget(pGUIApplicationSheet)
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
	private IControl<?, ?> createLogoImageWidget(final IApplicationSheet pGUIApplicationSheet) {
		return
		new VerticalStack()
		.addControl(
			new ImageControl().setImage(getApplicationLogoOrDefaultLogo(pGUIApplicationSheet)),
			new Button()
			.setText("Open")
			.setLeftMouseButtonPressAction(
				i ->
				i
				.getRefParentGUI()
				.onFrontEnd()
				.redirectTo(pGUIApplicationSheet.getApplicationTarget())
			)
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
