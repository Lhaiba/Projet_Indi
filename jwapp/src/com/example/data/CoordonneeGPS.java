package com.example.data;

import com.j256.ormlite.field.DatabaseField;



public class CoordonneeGPS {

	@DatabaseField(generatedId = true)
	private int id;
	@DatabaseField
	private Double latitude_CoordonneeGPS;
	@DatabaseField
    private Double longitude_CoordonneeGPS;

	public CoordonneeGPS() {		
	}

	public CoordonneeGPS(Double latitude_CoordonneeGPS, Double longitude_CoordonneeGPS) {

		this.latitude_CoordonneeGPS = latitude_CoordonneeGPS;
		this.longitude_CoordonneeGPS = longitude_CoordonneeGPS;
	}

	
	

	
	

}
