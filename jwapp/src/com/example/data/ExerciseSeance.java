package com.example.data;

import com.j256.ormlite.field.DatabaseField;


public class ExerciseSeance {

		@DatabaseField(generatedId = true)
		private int id;
		@DatabaseField(foreign = true, columnName = "exercice_id")
		Exercise exercice;
		@DatabaseField(foreign = true, columnName = "seance_id")
		Seance seance;

		public ExerciseSeance() {

		}

		public ExerciseSeance(Exercise exercice, Seance seance) {
			this.exercice = exercice;
			this.seance = seance;
			}

}
