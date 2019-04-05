/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.simple.ws;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.jws.WebService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
//Service Implementation
@WebService(endpointInterface = "com.simple.ws.SimpleWS")
public class SimpleWSImpl implements SimpleWS{

	private JSONArray stashCityList = null;
 
	private JSONArray getCityList() throws Exception {
		JSONParser jsonParser = new JSONParser();
		
		try {
			String fileName = "thincitylist.json";
			String data = getFile(fileName);
    	    
			if (data != null) {
				//Read JSON file
				Object obj = jsonParser.parse(data);
	
				JSONArray cityList = (JSONArray) obj;
				return cityList;
			}
		} catch (ParseException e) {
			throw e;
		}
		throw new NullPointerException("json file not found");
	}

	@Override
	public String getRandomCity() {
		try {
			if (stashCityList ==  null) {
				stashCityList = getCityList();
			}
			JSONObject cityObject = getRandomObject(stashCityList);
//			parseCityObject( cityObject );
			return (String) cityObject.get("name");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getCountryForCity(String city) {
		System.out.println("Retrieving country for city: " + city);
		System.out.println("-------------------------------------------");
		if (stashCityList ==  null) {
			try {
				stashCityList = getCityList();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		for (int i = 0; i < stashCityList.size(); i++) {
			JSONObject cityObject = (JSONObject) stashCityList.get(i);
			parseCityObject( cityObject );
			String tempCity = (String) cityObject.get("name");
			if (tempCity.contentEquals(city)) {
				return (String) cityObject.get("country");
			}
		}
		return "Country not found for city " + city;
	}

	private static void parseCityObject(JSONObject cityObject) {
		Long id = (Long) cityObject.get("id");
		System.out.println(id);
		String name = (String) cityObject.get("name");
		System.out.println(name);
		String country = (String) cityObject.get("country");
		System.out.println(country);
	}

	public JSONObject getRandomObject(JSONArray jsonArray) {
		int randomIndex = (int) (Math.random() * jsonArray.size());
		JSONObject jsonObject = (JSONObject) jsonArray.get(randomIndex);
		return jsonObject;
	}	

	// public for testing
	public String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}