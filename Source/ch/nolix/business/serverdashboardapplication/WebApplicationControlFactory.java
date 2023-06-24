//package declaration
package ch.nolix.business.serverdashboardapplication;

import ch.nolix.businessapi.serverdashboardcontextapi.IApplicationSheet;
import ch.nolix.coreapi.programcontrolapi.processproperty.SecurityLevel;
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
	
	//static attribute
	public static final WebApplicationControlFactory INSTANCE = new WebApplicationControlFactory();
	
	//constructor
	private WebApplicationControlFactory() {}
	
	//method
	public IControl<?, ?> createWebApplicationControl(
		final IApplicationSheet guiApplicationSheet,
		final SecurityLevel securityLevelForConnections
	) {
		
		final var guiApplicationVerticalStack =
		new VerticalStack()
		.addControl(
			createApplicationNameLabel(guiApplicationSheet),
			createLogoImageControl(guiApplicationSheet, securityLevelForConnections)
		);
		
		if (guiApplicationSheet.hasApplicationDescription()) {
			guiApplicationVerticalStack.addControl(createApplicationDescriptionLabel(guiApplicationSheet));
		}
		
		return guiApplicationVerticalStack;
	}
	
	//method
	private IControl<?, ?> createApplicationNameLabel(final IApplicationSheet guiApplicationSheet) {
		return new Label().setRole(LabelRole.LEVEL1_HEADER).setText(guiApplicationSheet.getApplicationName());
	}
	
	//method
	private IControl<?, ?> createLogoImageControl(
		final IApplicationSheet guiApplicationSheet,
		final SecurityLevel securityLevelForConnections
	) {
		return
		new ImageControl()
		.setImage(getApplicationLogoOrDefaultLogo(guiApplicationSheet))
		.setLeftMouseButtonPressAction(
			i ->
			i
			.getOriParentGui()
			.onFrontEnd()
			.redirectTo(guiApplicationSheet.getApplicationTarget(securityLevelForConnections))
		);
	}
	
	//method
	private IImage getApplicationLogoOrDefaultLogo(final IApplicationSheet guiApplicationSheet) {

		if (!guiApplicationSheet.hasApplicationLogo()) {
			return DEFAULT_LOGO;
		}
		
		return guiApplicationSheet.getApplicationLogo().withWidthAndHeight(LOGO_IMAGE_WIDTH, LOGO_IMAGE_HEIGHT);
	}
	
	//method
	private IControl<?, ?> createApplicationDescriptionLabel(final IApplicationSheet guiApplicationSheet) {
		return new Label().setText(guiApplicationSheet.getApplicationDescription());
	}
}
