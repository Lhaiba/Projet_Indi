package com.objects;

import com.orm.*;


public class Cardio extends SugarRecord<Cardio>{

		private Long id_exo;
		private Double distanceParcourues_Cardio;
	    private Double calories_Cardio;
	    private Double vitesseMoyenne_Cardio;
	    private Double vitesseMax_Cardio;

		public Cardio() {

		}

		public Cardio(Long id_exo,Double distanceParcourues_Cardio
				, Double calories_Cardio, Double vitesseMoyenne_Cardio, Double vitesseMax_Cardio) {
			
			this.id_exo = id_exo;
			this.distanceParcourues_Cardio = distanceParcourues_Cardio;
			this.calories_Cardio = calories_Cardio;
			this.vitesseMoyenne_Cardio = vitesseMoyenne_Cardio;
			this.vitesseMax_Cardio = vitesseMax_Cardio;
		}


}
