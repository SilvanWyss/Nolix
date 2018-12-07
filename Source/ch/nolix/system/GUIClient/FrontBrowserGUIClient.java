//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.system.GUIClientoid.FrontGUIClientoid;
import ch.nolix.system.GUIClientoid.FrontGUIClientoidType;

//class
/**
 * A {@link FrontBrowserGUIClient} does the same as a browser-analogue of a {@link FrontGUIClient}.
 * 
 * The requirements for a browser-analogue allow
 * that a browser-analogue can be implemented in Javascript.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 100
 */
public final class FrontBrowserGUIClient extends FrontGUIClientoid<FrontBrowserGUIClient> {

	public FrontBrowserGUIClient() {
		super(true);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internal_finishSessionInitialization() {}

	//method
	@Override
	public FrontGUIClientoidType getFrontEndType() {
		return FrontGUIClientoidType.PainterCommandsConsumer;
	}
}
