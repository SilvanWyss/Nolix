package ch.nolix.systemtutorial.guitutorial.dialogtutorial;

import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.dialog.UploadImageDialogCreator;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Uploader;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;

public final class UploadImageDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("ErrorDialog tutorial");
		
		//Loads an Image from file.
		final var image =
		MutableImage.fromResource("ch/nolix/elementTutorial/guitutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Create a ImageWidget.
		final var imageWidget = new ImageWidget().setImage(image);
		
		frame.pushLayerWithRootWidget(
			new VerticalStack()
			.addWidget(
				imageWidget,
				new Button()
				.setText("Change image")
				.setLeftMouseButtonPressAction(
					() -> frame.pushLayer(createImageDialogForImageWidget(imageWidget))
				)
			)
		);
		
		frame.setStyle(
			new Style()
			.addConfiguration(
				new DeepStyle()
				.setSelectorType(Button.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackground(Color(SkyBlue))",
					"HoverBackground(Color(Blue))",
					"BaseTextSize(30)"
				),
				new DeepStyle()
				.setSelectorType(ImageWidget.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"ProposalHeight(400)",
					"BaseBackground(Color(WhiteSmoke))",
					"BasePadding(10)",
					"ContentPosition(Center)"
				),
				new DeepStyle()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute("ContentPosition(Center)"),
				new DeepStyle()
				.setSelectorType(Uploader.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"ProposalHeight(100)",
					"CursorIcon(Hand)",
					"BaseBackground(Color(Gainsboro))",
					"ContentPosition(Center)"
				),
				new DeepStyle()
				.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
				.addAttachingAttribute(
					"ProposalWidth(250)",
					"BaseBackground(Color(LightGreen))",
					"HoverBackground(Color(Green))"
				),
				new DeepStyle()
				.addSelectorRole(ButtonRole.CANCEL_BUTTON)
				.addAttachingAttribute("ProposalWidth(250)", "BaseBackground(Color(Salmon))", "HoverBackground(Color(Red))")
				
			)
		);
	}
	
	private static Layer createImageDialogForImageWidget(final ImageWidget imageWidget) {
		
		if (imageWidget.isEmpty()) {
			return new UploadImageDialogCreator().createUploadImageDialogWithImageTaker(imageWidget::setImage);
		}
		
		return
		new UploadImageDialogCreator()
		.createUploadImageDialogWithCurrentImageAndImageTaker(imageWidget.getRefImage(), imageWidget::setImage);
	}
	
	private UploadImageDialogCreatorTutorial() {}
}
