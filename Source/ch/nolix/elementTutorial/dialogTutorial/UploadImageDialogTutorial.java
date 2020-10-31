package ch.nolix.elementTutorial.dialogTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.dialog.UploadImageDialog;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Uploader;
import ch.nolix.element.widget.VerticalStack;

public class UploadImageDialogTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("ErrorDialog Tutorial");
		
		//Loads an Image from file.
		final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
		
		//Create a ImageWidget.
		final var imageWidget = new ImageWidget(image);
		
		frame.addLayerOnTop(
			new VerticalStack()
			.addWidget(
				imageWidget,
				new Button()
				.setText("Change image")
				.setLeftMouseButtonPressAction(
					() -> {
						if (imageWidget.isEmpty()) {
							frame.addLayerOnTop(new UploadImageDialog(imageWidget::setImage));
						}
						else {
							frame.addLayerOnTop(
								new UploadImageDialog(imageWidget.getRefImage(), imageWidget::setImage)
							);
						}
					}
				)
			)
		);
		
		//Sets a Configuration to the Frame.
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
				.addSelectorRole(ContainerRole.DialogContainer)
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
				.addSelectorRole(ButtonRole.ConfirmButton)
				.addAttachingAttribute(
					"ProposalWidth(250)",
					"BaseBackgroundColor(LightGreen)",
					"HoverBackgroundColor(Green)"
				),
				new DeepConfiguration()
				.addSelectorRole(ButtonRole.CancelButton)
				.addAttachingAttribute("ProposalWidth(250)", "BaseBackgroundColor(Salmon)", "HoverBackgroundColor(Red)")
				
			)
		);
	}
	
	private UploadImageDialogTutorial() {}
}
