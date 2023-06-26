//package declaration
package ch.nolix.business.serverdashboardapplication.view;

//own imports
import ch.nolix.businessapi.serverdashboardlogicapi.IWebApplicationSheet;
import ch.nolix.system.graphic.image.MutableImage;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
final class WebApplicationControlFactory {
	
	//constant
	public static final int APPLICATION_LOGO_IMAGE_WIDTH = 250;
	
	//constant
	public static final int APPLICATION_LOGO_IMAGE_HEIGHT = 200;
	
	//constant
	private static final String DEFAULT_APPLICATION_LOGO_RESOURCE_PATH =
	"ch/nolix/business/serverdashboardapplication/resource/default_application_logo.jpg";
	
	//constant
	public static final IImage DEFAULT_APPLICATION_LOGO =
	MutableImage
	.fromResource(DEFAULT_APPLICATION_LOGO_RESOURCE_PATH)
	.withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);
	
	//method
	public IControl<?, ?> createWebApplicationControl(
		final IWebApplicationSheet guiApplicationSheet
	) {
		return
		new VerticalStack()
		.addControl(
			new ImageControl()
			.setImage(getApplicationLogoOrDefaultApplicationLogo(guiApplicationSheet))
			.setLeftMouseButtonPressAction(
				ic ->
				ic
				.getOriParentGui()
				.onFrontEnd()
				.redirectTo(guiApplicationSheet.getApplicationInstanceTarget())
			),
			new Label()
			.setRole(LabelRole.LEVEL1_HEADER)
			.setText(guiApplicationSheet.getApplicationInstanceTarget().getApplicationInstanceName())
		);
	}
	
	//method
	private IImage getApplicationLogoOrDefaultApplicationLogo(final IWebApplicationSheet guiApplicationSheet) {
		
		if (!guiApplicationSheet.hasApplicationLogo()) {
			return DEFAULT_APPLICATION_LOGO;
		}
		
		return
		guiApplicationSheet
		.getApplicationLogo()
		.withWidthAndHeight(APPLICATION_LOGO_IMAGE_WIDTH, APPLICATION_LOGO_IMAGE_HEIGHT);
	}
}
