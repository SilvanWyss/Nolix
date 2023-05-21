//package declaration
package ch.nolix.template.webapplication;

//own imports
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.graphicapi.imageapi.IImage;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

//class
public final class UnderConstructionPageSession extends BackendWebClientSession<Object> {
	
	//constant
	private static final String CRANE_IMAGE_RESOURCE_PATH = "ch/nolix/template/webapplication/resource/crane.jpg";
	
	//constant
	private static final IImage CRANE_IMAGE = Image.fromResource(CRANE_IMAGE_RESOURCE_PATH);
	
	//method
	@Override
	protected void initialize() {
		getOriGUI()
		.pushLayerWithRootControl(
			new VerticalStack()
			.setRole(ContainerRole.MAIN_CONTENT_CONTAINER)
			.addControl(
				new Label()
				.setRole(LabelRole.TITLE)
				.setText(getApplicationName()),
				new ImageControl()
				.setImage(CRANE_IMAGE)
				.editStyle(s -> s.setHeightForState(ControlState.BASE, 500)),
				new Label()
				.setText("This is page is under construction.")
				.editStyle(
					s ->
					s
					.setTextSizeForState(ControlState.BASE, 30)
					.setTextColorForState(ControlState.BASE, Color.GREY)
				),
				new Button()
				.setVisibility(hasParentSession())
				.setText("<-- Go back")
				.setLeftMouseButtonPressAction(b -> pop())
			)
			.editStyle(s -> s.setChildControlMarginForState(ControlState.BASE, 20))
		);
	}
}