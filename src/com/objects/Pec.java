package com.objects;

import com.orm.*;


public class Pec extends SugarRecord<Pec>{
	
	private int id_muscu;
	private Double poids_Pec;
	
		public Pec() {

		}

		public Pec(int id_muscu,Double poids_Pec) {
			
			this.id_muscu = id_muscu;
			this.poids_Pec = poids_Pec;
		}


}
