
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class main {
	public static void main(String[] args) {
		Scanner inscaner = new Scanner(System.in);
		String urlVK = "https://api.vk.com/method/photos.search?&radius=10000&lat=48.24523&long=58.2523&access_token=7075966f7075966f7075966f577018909e770757075966f2dfd2579655817dfd28d3bfd&v=5.103";
			try {	
				URL url = new URL(urlVK);
	    // read from the URL
				Scanner scan = new Scanner(url.openStream());
				String str = new String();
				while (scan.hasNext())
					str += scan.nextLine();
					scan.close();
	    // build a JSON object
	    JSONObject obj = new JSONObject(str);
	    int pageName = obj.getJSONObject("response").getInt("count");
	    System.out.print("Колличество фотографий " + pageName);
	     		try {
	     			for(int i=0;i < pageName; i=i + 300) {
	     				String urlVKoffset = "https://api.vk.com/method/photos.search?&radius=10000&lat=48.24523&long=58.2523&access_token=7075966f7075966f7075966f577018909e770757075966f2dfd2579655817dfd28d3bfd&v=5.103"
	     						+ "&offset=" + i;
	     						URL objVK = new URL(urlVKoffset);
	     						HttpURLConnection connection = (HttpURLConnection) objVK.openConnection();
	     						connection.setRequestMethod("GET");
				    		    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				    		    String inputLine;
				    		    StringBuffer response = new StringBuffer();
				    		    	while ((inputLine = in.readLine()) != null) {
				    		    		response.append(inputLine);
				    		    		}
				    		    		in.close();
	    		    /*
	    		    System.out.println(response.toString());
	    		    */
	    		    try (FileWriter file = new FileWriter("response-vk" + i + ".json")) {
	    		        file.write(response.toString());
	    		    }    
	 		}
	     }catch(Exception e){}
		}
		catch(Exception e){}
	}		
}



/*==================================================================== */
/* Перевод даты в unixtime форму 
System.out.println("Введите начальную дату не раньше которой искать (Year-mon-day: 2018-09-27) = ");
String start_time = inscaner.next();

LocalDate localDate = LocalDate.parse(start_time);
ZoneId zoneId = ZoneId.of("Europe/Moscow"); 
ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
long epoch = zdt.toEpochSecond();
	String key = "7075966f7075966f7075966f577018909e770757075966f2dfd2579655817dfd28d3bfd";
String Vers = "5.103";
System.out.println("Широта (например, -78.464996) = ");
String LATITUDE  = inscaner.next();
System.out.println("Долгота (например, 106.826117) = ");
String LONGITUDE = inscaner.next();
System.out.println("Укажите количество загружаемых фотографий (не больше 1000) = ");
String count = inscaner.next();
System.out.println("Укажите радиус поиска (например, 100 метров) = ");
String radius = inscaner.next();
String urlVK = "https://api.vk.com/method/photos.search?"
+ "&lat=" + LATITUDE 
+ "&long=" + LONGITUDE
+ "&count=" + count
+ "&radius=" +radius 
+ "&access_token=" + key
+ "&v=" + Vers;
/* try {
	    URL objVK = new URL(urlVK);
	    HttpURLConnection connection = (HttpURLConnection) objVK.openConnection();
	    	connection.setRequestMethod("GET");
	    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	    while ((inputLine = in.readLine()) != null) {
	      response.append(inputLine);
	    }
	    in.close();
	    
	    System.out.println(response.toString());
	    	try (FileWriter file = new FileWriter("response-vk.json")) {
	        file.write(response.toString());
	    }    
}catch(Exception e){}
   */
