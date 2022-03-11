package ch.nolix.elementtutorial.guitutorial.dialogtutorial;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.dialog.UploadImageDialogCreator;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Uploader;

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
		
		frame.addLayerOnTop(
			new VerticalStack()
			.add(
				imageWidget,
				new Button()
				.setText("Change image")
				.setLeftMouseButtonPressAction(
					() -> frame.addLayerOnTop(createImageDialogForImageWidget(imageWidget))
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
