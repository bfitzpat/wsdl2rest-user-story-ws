package com.simple.ws.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.simple.ws.SimpleWSImpl;

public class JSONLoadTest {

	@Test
	public void testRandomCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String response = impl.getRandomCity();
		assertTrue("Did not successfully retrieve a random city", response != null);
	}

	@Test
	public void testGetCountryForRandomCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String city = impl.getRandomCity();
		assertTrue("Did not successfully retrieve a random city", city != null);
		String response = impl.getCountryForCity(city);
		assertTrue("Did not successfully retrieve a country for random city (" + city + ")", response != null);
	}

	@Test
	public void testGetCountryForFixedCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String city = "Brisbane";
		String response = impl.getCountryForCity(city);
		assertTrue("Did not successfully retrieve Australia for Brisbane", response.equalsIgnoreCase("AU"));

		String city2 = "Makati City";
		String response2 = impl.getCountryForCity(city2);
		assertTrue("Did not successfully retrieve PH for Makati City", response2.equalsIgnoreCase("PH"));
	}
}
