//package declaration
package ch.nolix.business.serverdashboardapplication.view;

//own imports
import ch.nolix.businessapi.serverdashboardlogicapi.IWebApplicationSheet;
import ch.nolix.system.graphic.color.Color;
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
	public static final int LOGO_IMAGE_WIDTH = 200;
	
	//constant
	public static final int LOGO_IMAGE_HEIGHT = 150;
	
	//constant
	public static final IImage DEFAULT_LOGO =
	MutableImage.withWidthAndHeightAndColor(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT, Color.GREY);
	
	//method
	public IControl<?, ?> createWebApplicationControl(
		final IWebApplicationSheet guiApplicationSheet
	) {
		
		final var guiApplicationVerticalStack =
		new VerticalStack()
		.addControl(
			createApplicationNameLabel(guiApplicationSheet),
			createLogoImageControl(guiApplicationSheet)
		);
		
		return guiApplicationVerticalStack;
	}
	
	//method
	private IControl<?, ?> createApplicationNameLabel(final IWebApplicationSheet guiApplicationSheet) {
		return
		new Label()
		.setRole(LabelRole.LEVEL1_HEADER)
		.setText(guiApplicationSheet.getApplicationInstanceTarget().getApplicationInstanceName());
	}
	
	//method
	private IControl<?, ?> createLogoImageControl(
		final IWebApplicationSheet guiApplicationSheet
	) {
		return
		new ImageControl()
		.setImage(getApplicationLogoOrDefaultLogo(guiApplicationSheet))
		.setLeftMouseButtonPressAction(
			i ->
			i
			.getOriParentGui()
			.onFrontEnd()
			.redirectTo(guiApplicationSheet.getApplicationInstanceTarget())
		);
	}
	
	//method
	private IImage getApplicationLogoOrDefaultLogo(final IWebApplicationSheet guiApplicationSheet) {

		if (!guiApplicationSheet.hasApplicationLogo()) {
			return DEFAULT_LOGO;
		}
		
		return guiApplicationSheet.getApplicationLogo().withWidthAndHeight(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT);
	}
}
