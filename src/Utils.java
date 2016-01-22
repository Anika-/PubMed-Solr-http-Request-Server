import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Utils {


	public static void createDiretory(String filePath){


		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}else{
			return;
		}
	}
	
	
	public static String getCurrentDate(){

		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		System.out.println(dateFormat.format(calendar.getTime()));
		return dateFormat.format(calendar.getTime()).toString();

	}

	public static String getCurrentDateTime(){

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		System.out.println(dateFormat.format(calendar.getTime()));
		return dateFormat.format(calendar.getTime()).toString();

	}
}
