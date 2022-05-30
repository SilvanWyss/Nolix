package ch.nolix.systemtutorial.guitutorial.dialogtutorial;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.dialog.UploadImageDialogCreator;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Uploader;
import ch.nolix.system.gui.widgetgui.Layer;

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
		
		frame.pushLayerWithWidget(
			new VerticalStack()
			.add(
				imageWidget,
				new Button()
				.setText("Change image")
				.setLeftMouseButtonPressAction(
					() -> frame.pushLayer(createImageDialogForImageWidget(imageWidget))
				)
			)
		);
		
		frame.setConfiguration(
			new Configuration()
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType(Button.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackground(Color(SkyBlue))",
					"HoverBackground(Color(Blue))",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.setSelectorType(ImageWidget.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"ProposalHeight(400)",
					"BaseBackground(Color(WhiteSmoke))",
					"BasePadding(10)",
					"ContentPosition(Center)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute("ContentPosition(Center)"),
				new DeepConfiguration()
				.setSelectorType(Uploader.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"ProposalHeight(100)",
					"CursorIcon(Hand)",
					"BaseBackground(Color(Gainsboro))",
					"ContentPosition(Center)"
				),
				new DeepConfiguration()
				.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
				.addAttachingAttribute(
					"ProposalWidth(250)",
					"BaseBackground(Color(LightGreen))",
					"HoverBackground(Color(Green))"
				),
				new DeepConfiguration()
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
