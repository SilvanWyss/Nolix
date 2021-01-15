package ch.nolix.techtutorial.dynamicmathtutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.processproperty.ProcessingMode;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.containerwidget.HorizontalStack;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.guiclient.BackGUIClient;
import ch.nolix.system.guiclient.BackGUIClientSession;
import ch.nolix.tech.genericmath.FractalBuilder;

public final class FractalVisualizerTutorial {
	
	public static void main(String[] args) {
		
		new NetServer().addDefaultApplication(new FractalVisualizer());
		
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
	}
	
	private static class FractalVisualizer extends Application<BackGUIClient> {
		
		public static final String NAME = "Fractal Visualizer";
		
		public FractalVisualizer() {
			super(NAME, FractalSession.class);
		}
	}
	
	private static class FractalSession extends BackGUIClientSession {
		
		private final FractalBuilder fractalBuilder = new FractalBuilder();
		private final ImageWidget imageWidet = new ImageWidget();
		private final TextBox widthTextBox = new TextBox();
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			widthTextBox.setText(String.valueOf(100));
			
			getRefGUI()
			.addLayerOnTop(
				new VerticalStack()
				.addWidget(
					new VerticalStack()
					.addWidget(
						new HorizontalStack()
						.addWidget(
							new Label().setText("Width:"),
							widthTextBox
						)
					),
					new Button()
					.setText("Generate")
					.setLeftMouseButtonPressAction(this::generateFractal),
					imageWidet
				)
			);
		}
		
		private void generateFractal() {
			
			fractalBuilder.setWidthInPixel(Integer.valueOf(widthTextBox.getText()));
			
			imageWidet.setImage(pushAndGetResult(new GenerateImageSession(fractalBuilder)));
		}
	}
	
	private static final class GenerateImageSession extends BackGUIClientSession {
		
		private final FractalBuilder fractalBuilder;
		
		public GenerateImageSession(final FractalBuilder fractalBuilder) {
			this.fractalBuilder = fractalBuilder;
		}
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			getRefGUI().addLayerOnTop(new Label().setText("Please wait"));
			
			Sequencer.runInBackground(this::generateImage);
		}
		
		private void generateImage() {
			pop(fractalBuilder.build().toImage(ProcessingMode.SINGLE_THREADED).toScaledImage(2));
		}
	}
	
	private FractalVisualizerTutorial() {}
}
