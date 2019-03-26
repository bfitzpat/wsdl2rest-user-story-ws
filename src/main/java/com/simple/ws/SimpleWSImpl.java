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

import javax.jws.WebService;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
//Service Implementation
@WebService(endpointInterface = "com.simple.ws.SimpleWS")
public class SimpleWSImpl implements SimpleWS{

	private String cityStash = null;
	private String countryStash = null;
	private JSONArray stashCityList = null;
 
	private JSONArray getCityList() throws Exception {
		JSONParser jsonParser = new JSONParser();
		
		try {
			String fileName = "com/simple/ws/thin.city.list.json";
			Path path = Paths.get(this.getClass().getClassLoader()
      			.getResource(fileName).toURI());
			if (path != null) {
				String content = new String(Files.readAllBytes(path));

				//Read JSON file
				Object obj = jsonParser.parse(content);
	
				JSONArray cityList = (JSONArray) obj;
				return cityList;
			}
 		} catch (FileNotFoundException e) {
			 throw e;
		} catch (IOException e) {
			throw e;
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
			parseCityObject( cityObject );
			cityStash = (String) cityObject.get("name");
			countryStash = (String) cityObject.get("country");
		} catch (Exception e) {
			cityStash = null;
			countryStash = null;
			e.printStackTrace();
		}
		return cityStash;
	}

	@Override
	public String getCountryForCity(String city) {
		if (countryStash != null) {
			return countryStash;
		}
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
		return null;
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
}