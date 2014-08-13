package com.geuro.constants;

public final class GeuroConstants {
	
	private GeuroConstants(){
		
	}
	
	public static final String REQUEST_GET_METHOD = "GET";
	
	public static final String REQUEST_POST_METHOD = "POST";
	
	public static final String REQUEST_PROPERTY_1 = "Accept";
	
	public static final String REQUEST_PROPERTY_2 = "application/json";
	
	public static final String REST_URI = "http://api.goeuro.com/api/v2/position/suggest/en/";
	
	public static final String CITY_ID = "_id";
	
	public static final String CITY_TYPE = "type";
	
	public static final String CITY_COUNTRY="country";
	
	public static final String CITY_NAME = "name";
	
	public static final String CITY_GEO_POSITION = "geo_position";
	
	public static final String CITY_LATITUDE= "latitude";
	
	public static final String CITY_LONGITUDE= "longitude";
	
	public static final String CSV_FILE = "city.csv";
	
	public static final String COMMA_DELIMETER= ",";
	
	public static final String NEW_LINE = "\n";
	
	public static final String NO_ARGUMENT_ERROR_MESSAGE = "No argument passsed";
	
	public static final String CHAR_LIMIT_ERROR_MESSAGE = "city name should contain atleat two characters "+
															" and No Special characters or numerical values";
	
	public static final String NO_RESPONSE_MESSAGE = "No Response from Service or invalid city name passed";
	
	public static final String FILE_ERROR_MESSAGE = "File does exist or it is being used by other process,close the file and run the program again";
	
	//Default values for pojo
	
	public static final String DEFAULT = "Not Available";
	
	
			

}
