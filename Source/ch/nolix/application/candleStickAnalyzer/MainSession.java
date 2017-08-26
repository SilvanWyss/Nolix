//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.core.constants.CharacterManager;
import ch.nolix.core.constants.StringManager;
import ch.nolix.core.container.List;
import ch.nolix.core.fileSystem.FileSystemAccessor;
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
			
		getRefClient().setTitle(CandleStickAnalyzer.getTitle());
		
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
		
		while (true) {
			try {		
			
				final String[]inputs = getRefClient().readNextLineFromConsole().split(" ");
				
				boolean command = true;
				
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
							
							for (final String s : new Analysis(argumentOfficer).toStrings()) {
								getRefClient().writeNextLineToConsole(s);
							}
							
							break;
						case "saf":
							new Analysis(argumentOfficer).save();
							getRefClient().openFileExplorer();
							break;
						case "sdf":
										
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
							
							int i = 1;
							while (new FileSystemAccessor().fileSystemItemExists("data_" + i + ".txt")) {
								i++;
							}
							
							new FileSystemAccessor().createFile("data_" + i + ".txt", data);
							
							getRefClient().openFileExplorer();
							
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
						case StringManager.EMPTY_STRING:
							command = false;
							break;					
					default:
						throw new Exception("The entered command is invalid.");
				}
				
				if (command) {
					refreshInfoPanel();
					getRefClient().writeNextLineToConsole(Character.toString((char)0x2713) + " done");
				}
			}
			catch (final Exception exception) {
				getRefClient().writeNextLineToConsole(Character.toString((char)0x2716) + " error");
			}
		}
	}
	
	//method
	private void refreshInfoPanel() {
		
		getRefClient().clearInfoPanel();
		
		argumentOfficer.toStrings().forEach(s -> getRefClient().writeNextLineToInfoPanel(s));
		
		getRefClient().writeNextLineToInfoPanel(
			" ",
			"Enter 'sc' for showing commands."
		);
	}
	
	//method
	private void writeCommandsToConsole() {
		getRefClient().writeNextLineToConsole(
			" ",
				
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
			"q          quit program",
			
			" "
		);
	}
}
