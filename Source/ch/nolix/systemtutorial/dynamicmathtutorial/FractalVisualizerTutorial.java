package ch.nolix.systemtutorial.dynamicmathtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.processproperty.ProcessingMode;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.textbox.TextBox;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.base.VoidApplicationContext;
import ch.nolix.system.client.guiclient.BackGUIClient;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.dynamicmath.FractalBuilder;

public final class FractalVisualizerTutorial {
	
	public static void main(String[] args) {
		
		final var server = Server.forDefaultPort();
		
		server.addDefaultApplication(new FractalVisualizer());
		
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
		
		Sequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static class FractalVisualizer
	extends Application<BackGUIClient<VoidApplicationContext>, VoidApplicationContext> {
		
		public static final String NAME = "Fractal Visualizer";
		
		public FractalVisualizer() {
			super(NAME, FractalSession.class, VoidApplicationContext.INSTANCE);
		}
	}
	
	private static class FractalSession extends BackGUIClientSession<VoidApplicationContext> {
		
		private final FractalBuilder fractalBuilder = new FractalBuilder();
		private final ImageWidget imageWidet = new ImageWidget();
		private final TextBox widthTextBox = new TextBox();
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			widthTextBox.setText(String.valueOf(100));
			
			getRefGUI()
			.addLayerOnTop(
				new VerticalStack()
				.add(
					new VerticalStack()
					.add(
						new HorizontalStack()
						.add(
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
	
	private static final class GenerateImageSession extends BackGUIClientSession<VoidApplicationContext> {
		
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
