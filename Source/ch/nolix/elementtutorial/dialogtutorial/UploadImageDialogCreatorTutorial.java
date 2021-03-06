package ch.nolix.elementtutorial.dialogtutorial;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.dialog.UploadImageDialogCreator;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Uploader;

public final class UploadImageDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("ErrorDialog Tutorial");
		
		//Loads an Image from file.
		final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Create a ImageWidget.
		final var imageWidget = new ImageWidget().setImage(image);
		
		frame.addLayerOnTop(
			new VerticalStack()
			.addWidget(
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
					"BaseBackgroundColor(SkyBlue)",
					"HoverBackgroundColor(Blue)",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.setSelectorType(ImageWidget.class)
				.addAttachingAttribute(
					"ProposalWidth(500)",
					"ProposalHeight(400)",
					"BaseBackgroundColor(WhiteSmoke)",
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
					"BaseBackgroundColor(Gainsboro)",
					"ContentPosition(Center)"
				),
				new DeepConfiguration()
				.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
				.addAttachingAttribute(
					"ProposalWidth(250)",
					"BaseBackgroundColor(LightGreen)",
					"HoverBackgroundColor(Green)"
				),
				new DeepConfiguration()
				.addSelectorRole(ButtonRole.CANCEL_BUTTON)
				.addAttachingAttribute("ProposalWidth(250)", "BaseBackgroundColor(Salmon)", "HoverBackgroundColor(Red)")
				
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
