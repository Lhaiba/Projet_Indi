package com.example.data;

import com.j256.ormlite.field.DatabaseField;


public class Seance_Programme {

		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(foreign = true, columnName = "seance_id")
		Seance seance;
		@DatabaseField(foreign = true, columnName = "programme_id")
		Programme programme;

		public Seance_Programme() {

		}

		public Seance_Programme(Seance seance,Programme programme) {
			this.seance = seance;
			this.programme = programme;
			}

}
