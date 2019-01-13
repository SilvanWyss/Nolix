//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.system.GUIClientoid.FrontGUIClientoid;
import ch.nolix.system.GUIClientoid.FrontGUIClientoidType;
import ch.nolix.system.client.Application;

//class
/**
 * A {@link FrontBrowserGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue of a {@link FrontGUIClient} allow
 * that a browser-analogue of a {@link FrontGUIClient} can be implemented in JavaScript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 50
 */
public final class FrontBrowserGUIClient extends FrontGUIClientoid<FrontBrowserGUIClient> {
	
	//constructor
	/**
	 * Creates a new {@link FrontBrowserGUIClient} that will connect to the given application.
	 * 
	 * @throws NullArgumentException if the given application is null.
	 */
	public FrontBrowserGUIClient(final Application<BackGUIClient> application) {
		
		super(true);
		
		internal_connectTo(application);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public FrontGUIClientoidType getFrontEndType() {
		return FrontGUIClientoidType.PainterCommandsConsumer;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internal_finishSessionInitialization() {}
}
