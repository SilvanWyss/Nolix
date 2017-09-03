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
 * @lines 210
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
		getRefClient().writeLineToConsole("Enter the password.");
		while (true) {
			
			final String password = getRefClient().readLineFromConsole();
			
			if (password.equals("justin")) {
				break;
			}
			
			getRefClient().writeLineToConsole("Wrong password. Try again.");
		}
		
		refreshInfoPanel();
		getRefClient().clearConsole();
		
		//Processes until the user quits the program.
		while (true) {
			try {		
				final String[]inputs = getRefClient().readNonEmptyLineFromConsole().split(" ");
							
				//Enumerates the entered command.
				switch (inputs[0]) {	
					
					//Handles input commands.										
						case CommandManager.SELECT_RPODUCT_SYMBOL_COMMAND:
							argumentOfficer.setProductSymbol(inputs[1]);
							break;
						case CommandManager.SELECT_START_TIME_COMMAND:
							argumentOfficer.setStartTime(new Time(inputs[1]));
							break;
						case CommandManager.SELECT_END_TIME_COMMAND:
							argumentOfficer.setEndTime(new Time(inputs[1]));							
							break;	
						case CommandManager.SELECT_RED_CANDLE_STICKS_BEFORE_HAMMER_COMMAND:
							argumentOfficer.setRedCandleStickCountBeforeHammer(Integer.valueOf(inputs[1]));
							break;
						case CommandManager.SELECT_GREEN_CANDLE_STICKS_AFTER_HAMMER_COMMAND:
							argumentOfficer.setGreenCandleStickCountAfterHammer(Integer.valueOf(inputs[1]));
							break;
						case CommandManager.SELECT_HAMMER_MINIMAL_LOWER_WICK_LENGTH_RATIO_COMMAND:
							argumentOfficer.setHammerMinLowerWickLengthRation(Double.valueOf(inputs[1]));
							break;
						case CommandManager.SELECT_MAX_LOSS_RATIO_PER_DAY_COMMAND:
							argumentOfficer.setMaxLossRatioPerDay(Double.valueOf(inputs[1]));
							break;
						case CommandManager.SELECT_MAX_KEEPING_DAYS_COMMAND:
							argumentOfficer.setMaxKeepingDayCount(Integer.valueOf(inputs[1]));
							break;
							
					//Handles output commands.
						case CommandManager.SHOW_ANALYSIS_COMMAND:							
							getRefClient().writeLinesToConsole(new Analysis(argumentOfficer).toStrings());
							break;
						case CommandManager.SAVE_PRODUCT_DATA_TO_FILE_COMMAND:
							saveDataToFile();														
							break;
						case CommandManager.SAVE_ANALYSIS_TO_FILE_COMMAND:
							saveAnalysisToFile();
							break;
						case CommandManager.SHOW_ALGORITHM_FACTS_COMMAND:
							showAlgorithmFacts();
							break;
						
					//Handles system commands.
						case CommandManager.SHOW_COMMANDS_COMMAND:
							showCommands();
							break;
						case CommandManager.QUIT_COMMAND:
							getRefClient().quit();
							break;				
					default:
						throw new Exception("The entered command is invalid.");
				}
				
				refreshInfoPanel();
				getRefClient().writeLineToConsole(Character.toString((char)0x2714));
			}
			catch (final Exception exception) {
				getRefClient().writeLineToConsole(Character.toString((char)0x2716));
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
		.writeLinesToInfoPanel(argumentOfficer.toStrings())
		.writeEmptyTextLineToInfoPanel()
		.writeLineToInfoPanel("Enter 'sc' for showing commands.");
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
	 * Writes algorithm facts to the console
	 * of the counterpart of the client of this main session.
	 */
	private void showAlgorithmFacts() {
		getRefClient().writeLineToConsole(
			"-The product is bougth at the opening",
			" of the next day after the confirmation.",
			StringManager.EMPTY_STRING,
			"-The product is sold at the opening of the next day",
			" if the loss ratio of the current day is too big.",
			StringManager.EMPTY_STRING,
			"-The product is sold at the opening of the last day otherwise."
		);
	}
	
	//method
	/**
	 * Writes the available commands to the console
	 * of the counterpart of the client of this main session.
	 */
	private void showCommands() {
		getRefClient().writeLineToConsole(	
				
			//input commands
			"sps x      select product symbol x",
			"sst x      select start time x in format YYYY-MM-DD",
			"set x      select end time x in format YYYY-MM-DD",
			"srcsbh x   select x red candle sticks before hammer",
			"sgcsah x   select x green candle sticks after hammer",
			"shmlwlr x  select x as hammer minimal lower wick length ratio",
			"smlrpd x   select x as max. loss ratio per day in format {d}.{d}",
			"smkd x     select x as max. keeping days",
					
			//output commands
			"sa         show analysis",
			"spdtf      save product data to file",
			"satf       save analysis to file",
			"saf        show algorithm facts",
		
			//system commands
			"q          quit program"
		);
	}
}
