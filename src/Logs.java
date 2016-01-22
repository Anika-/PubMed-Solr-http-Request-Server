import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Logs {

	//create log Folder
	
	//create LogFile per day
	
	//save errors during execution
	
	public File writeToLog(String text){
		
		//create Log folder if it doesnt exist
		Path currentRelativePath = Paths.get("");
		String currentPath = currentRelativePath.toAbsolutePath().toString();
		String logPath = currentPath+File.separatorChar+"Log";
		Utils.createDiretory(logPath);
		
		//create Log file if it doesnt exist
		File file = new File(resultPath.concat().concat(".xml"));
		
	}
	
	public void logError(Error e){
		String message = e.getMessage().toString(); 
		String cause = e.getCause().toString();
		writeToLog(Utils.getCurrentDateTime());
		writeToLog(message.concat("\nCause:").concat(cause));
	}
	
	
}
