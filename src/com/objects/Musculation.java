package com.objects;

import com.orm.*;


public class Musculation extends SugarRecord<Musculation>{

		private Long id_exo;
		private int nombreRepetitions_Musculation;
	    private int tempsRepos_Musculation; /*En seconde*/

		public Musculation() {

		}

		public Musculation(Long id_exo,int nombreRepetitions_Musculation
				, int tempsRepos_Musculation) {
			
			this.id_exo = id_exo;
			this.nombreRepetitions_Musculation = nombreRepetitions_Musculation;
			this.tempsRepos_Musculation = tempsRepos_Musculation;
			
		}


}
