package com.simple.ws.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.simple.ws.SimpleWSImpl;

public class JSONLoadTest {

	@Test
	public void testRandomCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String response = impl.getRandomCity();
		assertTrue("Successfully retrieved a city", response != null);
	}

	@Test
	public void testGetCountryForRandomCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String city = impl.getRandomCity();
		String response = impl.getCountryForCity(city);
		assertTrue("Successfully retrieved a country", response != null);
	}

	@Test
	public void testGetCountryForFixedCity() {
		SimpleWSImpl impl = new SimpleWSImpl();
		String city = "Brisbane";
		String response = impl.getCountryForCity(city);
		assertTrue("Successfully retrieved Australia for Brisbane", response.equalsIgnoreCase("AU"));

		String city2 = "Makati City";
		String response2 = impl.getCountryForCity(city2);
		assertTrue("Successfully retrieved PH for Makati City", response2.equalsIgnoreCase("PH"));
	}
}
