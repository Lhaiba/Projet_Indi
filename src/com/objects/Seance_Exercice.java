package com.objects;

import com.orm.*;


public class Seance_Exercice extends SugarRecord<Seance_Exercice>{

		private Long id_exo;
		private Long id_seance;
	   

		public Seance_Exercice() {

		}

		public Seance_Exercice(Long id_exo,Long id_seance) {
			
			this.id_exo = id_exo;
			this.id_seance = id_seance;
		}


}
