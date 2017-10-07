//package declaration
package ch.nolix.application.candlestickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.ClosedStateException;
import ch.nolix.element.core.Time;
import ch.nolix.element.finance.QuandlDataProvider;
import ch.nolix.element.finance.VolumeCandlestick;
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
			
		refreshInfoPanel();
		
		//Processes until the user quits the program.
		while (true) {
			try {
				
				final String[]inputs = getRefClient().readNonEmptyLineFromConsole().split(" ");
							
				//Enumerates the entered command.
				switch (inputs[0]) {	
					
					//Handles input commands.										
					case CommandCatalogue.SELECT_RPODUCT_SYMBOL_COMMAND:
						argumentOfficer.setProductSymbol(inputs[1]);
						break;
					case CommandCatalogue.SELECT_START_TIME_COMMAND:
						argumentOfficer.setStartTime(new Time(inputs[1]));
						break;
					case CommandCatalogue.SELECT_END_TIME_COMMAND:
						argumentOfficer.setEndTime(new Time(inputs[1]));							
						break;	
					case CommandCatalogue.SELECT_RED_CANDLE_STICKS_BEFORE_HAMMER_COMMAND:
						argumentOfficer.setRedCandlestickCountBeforeHammer(Integer.valueOf(inputs[1]));
						break;
					case CommandCatalogue.SELECT_GREEN_CANDLE_STICKS_AFTER_HAMMER_COMMAND:
						argumentOfficer.setGreenCandlestickCountAfterHammer(Integer.valueOf(inputs[1]));
						break;
					case CommandCatalogue.SELECT_HAMMER_MINIMAL_LOWER_WICK_LENGTH_RATIO_COMMAND:
						argumentOfficer.setHammerMinLowerWickLengthRation(Double.valueOf(inputs[1]));
						break;
					case CommandCatalogue.SELECT_MAX_LOSS_RATIO_PER_DAY_COMMAND:
						argumentOfficer.setMaxLossRatioPerDay(Double.valueOf(inputs[1]));
						break;
					case CommandCatalogue.SELECT_MAX_KEEPING_DAYS_COMMAND:
						argumentOfficer.setMaxKeepingDayCount(Integer.valueOf(inputs[1]));
						break;
							
					//Handles output commands.
					case CommandCatalogue.SHOW_ANALYSIS_COMMAND:							
						getRefClient().writeLinesToConsole(new Analysis(argumentOfficer).toStrings());
						break;
					case CommandCatalogue.SAVE_PRODUCT_DATA_TO_FILE_COMMAND:
						saveDataToFile();														
						break;
					case CommandCatalogue.SAVE_ANALYSIS_TO_FILE_COMMAND:
						saveAnalysisToFile();
						break;
					case CommandCatalogue.SHOW_ALGORITHM_FACTS_COMMAND:
						showAlgorithmFacts();
						break;
						
					//Handles system commands.
					case CommandCatalogue.SHOW_COMMANDS_COMMAND:
						showCommands();
						break;
					case CommandCatalogue.QUIT_COMMAND:
						getRefClient().quit();
						break;
					
					default:
						throw new Exception("The entered command is invalid.");
				}
				
				refreshInfoPanel();
				getRefClient().writeLineToConsole(CharacterCatalogue.FAT_CHECK_MARK);
			}
			catch (final ClosedStateException exception) {
				break;
			}
			catch (final Exception exception) {
				getRefClient().writeLineToConsole(CharacterCatalogue.FAT_CROSS);
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
	/**
	 * Lets the counterpart of  the client of this main session
	 * save the data to a file.
	 */
	private void saveDataToFile() {
		
		final List<VolumeCandlestick> candleSticks
		= new QuandlDataProvider().getCandleSticksPerDay(
			argumentOfficer.getProductSymbol(),
			argumentOfficer.getStartTime(),
			argumentOfficer.getEndTime()
		);
		
		String data = StringCatalogue.EMPTY_STRING;
		for (final VolumeCandlestick vcs : candleSticks) {
			data += vcs.getSpecification().toString() + CharacterCatalogue.NEW_LINE;
		}
		
		getRefClient().createFile("data.txt", data);
		getRefClient().openFileExplorer();
	}
	
	//method
	/**
	 * Writes the algorithm facts to the console
	 * of the counterpart of the client of this main session.
	 */
	private void showAlgorithmFacts() {
		getRefClient().writeLineToConsole(
			"-The product is bougth at the opening",
			" of the next day after the confirmation.",
			StringCatalogue.EMPTY_STRING,
			"-The product is sold at the opening of the next day",
			" if the loss ratio of the current day is too big.",
			StringCatalogue.EMPTY_STRING,
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
			"s x    select product symbol x",
			"ss x   select start time x in format YYYY-MM-DD",
			"se x   select end time x in format YYYY-MM-DD",
			"sr x   select x red candle sticks before hammer",
			"sg x   select x green candle sticks after hammer",
			"sh x   select x as hammer min. lower wick length ratio",
			"sm x   select x as max. loss ratio per day in format {d}.{d}",
			"smk x  select x as max. keeping days",
					
			//output commands
			"sa     show analysis",
			"sp     save product data to file",
			"sat    save analysis to file",
			"saf    show algorithm facts",
		
			//system commands
			"q      quit program"
		);
	}
}
