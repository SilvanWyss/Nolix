//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.List;
import ch.nolix.element.basic.Time;
import ch.nolix.element.finance.QuandlDataProvider;
import ch.nolix.element.finance.VolumeCandleStick;
import ch.nolix.system.client.Session;
import ch.nolix.system.consoleClient.ConsoleBackClient;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 190
 */
public final class MainSession extends Session<ConsoleBackClient> {
	
	//attribute
	private final ArgumentOfficer argumentOfficer = new ArgumentOfficer();

	//method
	/**
	 * Initializes this main session.
	 */
	public void initialize() {
			
		//Sets the title of the console.
		getRefClient().setTitle(CandleStickAnalyzer.getTitle());
		
		//Asks for the password.
		getRefClient().writeNextLineToConsole("Enter the password.");
		while (true) {
			
			final String password = getRefClient().readNextLineFromConsole();
			
			if (password.equals("justin")) {
				break;
			}
			
			getRefClient().writeNextLineToConsole("Wrong password. Try again.");
		}
		
		refreshInfoPanel();
		getRefClient().clearConsole();
		
		//Processes until the user quits the program.
		while (true) {
			try {		
			
				final String[]inputs = getRefClient().readNextNonEmptyLineFromConsole().split(" ");
							
				//Enumerates the entered command.
				switch (inputs[0]) {	
					
					//Handles input commands.
						case "set":
							argumentOfficer.setEndTime(new Time(inputs[1]));							
							break;					
						case "sps":
							argumentOfficer.setProductSymbol(inputs[1]);
							break;
						case "sst":
							argumentOfficer.setStartTime(new Time(inputs[1]));
							break;
						case "srcsbh":
							argumentOfficer.setRedCandleStickCountBeforeHammer(Integer.valueOf(inputs[1]));
							break;
						case "sgcsah":
							argumentOfficer.setGreenCandleStickCountAfterHammer(Integer.valueOf(inputs[1]));
							break;
						case "shmlwlr":
							argumentOfficer.setHammerMinLowerWickLengthRation(Double.valueOf(inputs[1]));
							break;
						case "smlrd":
							argumentOfficer.setMaxLossRatioPerDay(Double.valueOf(inputs[1]));
							break;
						case "smkd":
							argumentOfficer.setMaxKeepingDayCount(Integer.valueOf(inputs[1]));
							break;
							
					//Handles output commands.
						case "sa":							
							getRefClient().writeNextLinesToConsole(new Analysis(argumentOfficer).toStrings());
							break;
						case "saf":
							saveAnalysisToFile();
							break;
						case "sdf":
							saveDataToFile();														
							break;
						case "sad":
							
							getRefClient().writeNextLineToConsole(
								" ",
								"The product is bougth at the opening",
								"of the next day after the confirmation.",
								" ",
								"The product is sold at the opening of the next day",
								"if the loss ratio of the current day is too big.",
								" ",
								"The product is sold at the opening of the last day otherwise.",
								" "
							);						
							
							break;
						
					//Handles system commands.
						case "sc":
							writeCommandsToConsole();
							break;
						case "q":
							
							//Stops the console front client.
							//TODO: Add stop connection method to client.
							System.exit(0);
							break;				
					default:
						throw new Exception("The entered command is invalid.");
				}
				
				refreshInfoPanel();
				getRefClient().writeNextLineToConsole(Character.toString((char)0x2714));
			}
			catch (final Exception exception) {
				getRefClient().writeNextLineToConsole(Character.toString((char)0x2716));
			}
		}
	}
	
	//method
	/**
	 * Refreshes the info panel
	 * of the counterpart of the client of this main session.
	 */
	private void refreshInfoPanel() {		
		getRefClient()
		.clearInfoPanel()
		.writeNextLinesToInfoPanel(argumentOfficer.toStrings())
		.writeNextEmptyLineToInfoPanel()
		.writeNextLineToInfoPanel("Enter 'sc' for showing commands.");
	}
	
	//method
	/**
	 * Lets the counterpart of the client of this main session
	 * save the analysis to a file.
	 */
	private void saveAnalysisToFile() {
		getRefClient().createFile("analysis.txt", new Analysis(argumentOfficer).getData());
		getRefClient().openFileExplorer();
	}
	
	//method
	private void saveDataToFile() {
		
		final List<VolumeCandleStick> candleSticks
		= new QuandlDataProvider().getCandleSticksPerDay(
			argumentOfficer.getProductSymbol(),
			argumentOfficer.getStartTime(),
			argumentOfficer.getEndTime()
		);
		
		String data = StringManager.EMPTY_STRING;
		for (final VolumeCandleStick vcs : candleSticks) {
			data += vcs.getSpecification().toString() + CharacterManager.NEW_LINE;
		}
		
		getRefClient().createFile("data.txt", data);
		getRefClient().openFileExplorer();
	}
	
	//method
	/**
	 * Writes the available commands to the console
	 * of the counterpart of the client of this main session..
	 */
	private void writeCommandsToConsole() {
		getRefClient().writeNextLineToConsole(	
				
			//input commands
			"sps x      select product symbol x",
			"sst x      select start time x in format YYYY-MM-DD",
			"set x      select end time x in format YYYY-MM-DD",
			"srcsbh x   select x red candle sticks before hammer",
			"sgcsah x   select x green candle sticks after hammer",
			"shmlwlr x  select x as hammer minimal lower wick to length ratio",
			"smlrd x    select x as max. loss ratio per day in format {d}.{d}",
			"smkd x     select x max. keeping days from buying to selling",
					
			//output commands
			"sa         show analysis",
			"sdf        save data to a file",
			"saf        save analysis to a file",
			"sad        show algorithm description",
		
			//system commands
			"q          quit program"
		);
	}
}
