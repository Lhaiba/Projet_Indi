package com.objects;

import com.orm.*;


public class Footing extends SugarRecord<Footing>{
	
	private int id_cardio;
	private int frequenceMesureGPS_Footing;

		public Footing() {

		}

		public Footing(int id_cardio,int frequenceMesureGPS_Footing) {
			
			this.id_cardio = id_cardio;
			this.frequenceMesureGPS_Footing = frequenceMesureGPS_Footing;
		}


}
