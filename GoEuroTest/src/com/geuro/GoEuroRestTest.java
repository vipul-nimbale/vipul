package com.geuro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.geuro.bean.CityInfo;
import com.geuro.constants.GeuroConstants;

public class GoEuroRestTest implements GeuroConstants  {

	/**
	 * @param args
	 */
	public static int  counter=1;
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		if(args.length<1){
			
			System.out.println(NO_ARGUMENT_ERROR_MESSAGE);
			
		}else{
			
			String city = args[0];
			
			if(city.length()==0 || city.length()<2 || !city.matches("[a-zA-Z]*")){
				
				System.out.println(CHAR_LIMIT_ERROR_MESSAGE);
				
			}
			else{
				GoEuroRestTest get = new GoEuroRestTest();
				
				get.process(get,city);
			}	

		}
			}

	//Method to process access and process response from Service
	public void process(GoEuroRestTest get,String city) {
		try {
			
			URL url = new URL(REST_URI+city);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(REQUEST_GET_METHOD);
			connection.setRequestProperty(REQUEST_PROPERTY_1, REQUEST_PROPERTY_2);

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed with the code: "+connection.getResponseCode());
			}
            //Response from Service
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			String output = null;
			output = br.readLine();
	        //JSON Processing
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(output); 
			
			JSONArray ja = (JSONArray)obj;
			if(!ja.isEmpty()){
            JSONObject cityDetails;
            JSONObject geoDetails;
            //if file does not exists, this will create the CSV file where jar resides
           
            File f = new File(CSV_FILE);
            

            
            CityInfo info = new CityInfo();
                       
			Iterator it = ja.iterator();
			
			while(it.hasNext()){
				
				cityDetails = (JSONObject)it.next();
				geoDetails = (JSONObject)cityDetails.get(CITY_GEO_POSITION);
				info = writeToPojo(info,cityDetails,geoDetails);
				
					get.writeFile(info,f);
				
				
				
				}
			
			System.out.println("Data has been written successfully on "+f.getAbsolutePath());
			}else{
				System.out.println(NO_RESPONSE_MESSAGE);
			}
			
			connection.disconnect();
			
		} catch (MalformedURLException e) {

			e.printStackTrace();

		}
		
		catch (IOException e) {
			e.printStackTrace();

		} catch(ParseException pe){

		}

	}
	public CityInfo writeToPojo(CityInfo info,JSONObject cityDetails,JSONObject geoDetails){
		
		if(cityDetails.get(CITY_ID)!=null){
			info.set_id(cityDetails.get(CITY_ID).toString());
		}else
		{
			info.set_id(Integer.toString(counter));}
		if(cityDetails.get(CITY_NAME)!=null){
			info.setName(cityDetails.get(CITY_NAME).toString());
		}else
		{
			info.setName(DEFAULT);
		}
		if(cityDetails.get(CITY_COUNTRY)!=null){
			info.setCountry(cityDetails.get(CITY_COUNTRY).toString());
		}else
		{
			info.setCountry(DEFAULT);
		}
		if(cityDetails.get(CITY_TYPE)!=null){
			info.setType(cityDetails.get(CITY_TYPE).toString());
		}else
		{
			info.setType(DEFAULT);
		}
		if(geoDetails.get(CITY_LATITUDE)!= null){
			info.setLatitude(geoDetails.get(CITY_LATITUDE).toString());
		}else{
			info.setLatitude(DEFAULT);
		}
		if(geoDetails.get(CITY_LONGITUDE)!=null){
			info.setLongitude(geoDetails.get(CITY_LONGITUDE).toString());
		}else
		{
			info.setLongitude(DEFAULT);
		}
		GoEuroRestTest.counter++;
		return info;
	}
	
	//Method to write city details in file city.csv
	public  void writeFile(CityInfo info,File file){
		
		try
		{  
			FileWriter writer = new FileWriter(file,true);
			if(file.length()==0){
				writer.append(CITY_ID);
		    	writer.append(COMMA_DELIMETER);
		    	writer.append(CITY_COUNTRY);
		    	writer.append(COMMA_DELIMETER);
		    	writer.append(CITY_NAME);
		    	writer.append(COMMA_DELIMETER);
		    	writer.append(CITY_TYPE);
		    	writer.append(COMMA_DELIMETER);
		    	writer.append(CITY_LATITUDE);
		    	writer.append(COMMA_DELIMETER);
		    	writer.append(CITY_LONGITUDE);
		    	writer.append(NEW_LINE);
            }
		    writer.append(info.get_id());
	    	writer.append(COMMA_DELIMETER);
	    	writer.append(info.getCountry());
	    	writer.append(COMMA_DELIMETER);
	    	writer.append(info.getName());
	    	writer.append(COMMA_DELIMETER);
	    	writer.append(info.getType());
	    	writer.append(COMMA_DELIMETER);
	    	writer.append(info.getLatitude());
	    	writer.append(COMMA_DELIMETER);
	    	writer.append(info.getLongitude());
	    	writer.append(NEW_LINE);
		    
		    writer.flush();
	
		}
		catch(FileNotFoundException fne){
			System.out.println(FILE_ERROR_MESSAGE);
			System.exit(0);
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 
	}
	
}

 
