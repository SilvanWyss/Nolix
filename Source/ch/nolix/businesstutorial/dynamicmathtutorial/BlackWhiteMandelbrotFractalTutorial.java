package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.bigdecimalmath.ComplexNumber;
import ch.nolix.business.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.business.bigdecimalmath.FractalBuilder;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.webgui.control.ImageControl;

public final class BlackWhiteMandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication(
			"Black-white Mandelbrot fractal tutorial",
			MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
		
			getRefGUI()
			.pushLayerWithRootControl(
				new ImageControl()
				.setImage(
					new FractalBuilder()
					.setRealComponentInterval(-2.0, 1.0)
					.setImaginaryComponentInterval(-1.5, 1.5)
					.setWidthInPixel(500)
					.setHeightInPixel(500)
					.setSequenceCreator(
						z ->
						new ComplexSequenceDefinedBy1Predecessor(
							new ComplexNumber(0.0, 0.0),
							p -> p.getPower2().getSum(z)
						)
					)
					.setMinMagnitudeForDivergence(10.0)
					.setMaxIterationCount(50)
					.setColorFunction(i -> Color.WHITE)
					.setBigDecimalScale(10)
					.build()
					.startImageGeneration()
					.getRefImage()
				)
			);
			
			GlobalSequencer.asLongAs(this::isOpen).afterAllSeconds().runInBackground(this::updateCounterpart);
		}
	}
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
