package swipecontent.exercise;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.Category;
import com.example.data.DatabaseHelper;
import com.example.data.Exercise;
import com.example.jwapp.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class Fragment_Details extends Fragment {

	DetailsContainer detailsContainer;

	public interface DetailsContainer {
		public void onValidateExercise(Exercise e);
	}

	public void setDetailsContainer(DetailsContainer detailsContainer) {
		this.detailsContainer = detailsContainer;
	}

	private DatabaseHelper databaseHelper = null;

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}

	private DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(getActivity(),
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	int mode = 1; // 1 : création d'exercice, 2 : édition d'exercice
	// --------------------
	// DATA
	// --------------------
	// General exercise data
	String nom;
	int image = -1;
	long idkey_category = -1;

	// Musculation related data
	int nbRepetitions = -1;
	String tpsRepos;

	// Cardio related data
	String parcours;
	long temps = -1;

	// --------------------
	// VIEWS
	// --------------------
	// General views-related
	LayoutInflater inflater;
	ViewGroup container;

	LinearLayout layoutContainer; // layout qui va contenir toutes les vues du
									// fragment
	LinearLayout layoutBase; // layout du contenu commun à tous les exercices
	LinearLayout layoutSpec; // layout du contenu spécifique à une catégorie

	EditText ET_name = null;
	ImageView IV_icon = null;

	// Musculation related views
	EditText ET_nbRepetitions = null;
	EditText ET_tpsRepos = null;

	// Cardio related views
	EditText ET_parcours = null;
	EditText ET_temps = null;

	/*
	 * Initialisation des variables depuis le Bundle
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null) {
			mode = savedInstanceState.getInt("mode");

			idkey_category = savedInstanceState.getLong("idkey_category");

			nom = savedInstanceState.getString("nom");

			image = savedInstanceState.getInt("image");

			nbRepetitions = savedInstanceState.getInt("nbRepetitions");
			tpsRepos = savedInstanceState.getString("tpsRepos");

			parcours = savedInstanceState.getString("parcours");
			temps = savedInstanceState.getLong("temps");
		}
	}

	/*
	 * Sauvegarde des données
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		// Mise à jour des valeurs depuis les vues
		collectDataFromViews();

		// Sauvegarde
		outState.putInt("mode", mode);
		outState.putString("nom", nom);
		outState.putInt("image", image);
		outState.putLong("idkey_category", idkey_category);
		outState.putInt("nbRepetitions", nbRepetitions);
		outState.putString("tpsRepos", tpsRepos);
		outState.putString("parcours", parcours);
		outState.putLong("temps", temps);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.container = container;

		View view = inflater.inflate(R.layout.fragment_details_layout,
				container, false);

		layoutContainer = (LinearLayout) view
				.findViewById(R.id.exercise_create_details_container);

		setContent();

		return view;
	}

	/*
	 * Crée un DialogFragment chargé de récupérer une nouvelle image
	 */
	void startSelectImageDialog() {
		FragmentManager fm = getFragmentManager();
		DialogFragment_SelectImage dialogFragment_SelectImage = new DialogFragment_SelectImage();
		dialogFragment_SelectImage.show(fm, "Select Image DialogFragment");
	}

	/*
	 * Fonction appelée pour créer ou mettre à jour nos vues
	 */
	void setContent() {
		// On applique cette fonction uniquement si l'on a bien choisi
		// auparavant sa catégorie
		if (idkey_category != -1) {

			// Supprime la vue par défaut
			layoutContainer.removeAllViewsInLayout();

			// Réinitialisation des vues
			ET_name = null;
			IV_icon = null;

			// Musculation
			ET_nbRepetitions = null;
			ET_tpsRepos = null;

			// Cardio
			ET_parcours = null;
			ET_temps = null;

			// --------------------
			// Création des vues communes
			// --------------------

			layoutBase = null;
			layoutSpec = null;

			// Création de la vue layoutBase va contenir les vues communes a
			// toutes les catégories d'exercice
			layoutBase = (LinearLayout) inflater.inflate(
					R.layout.exercise_create_details_base, layoutContainer,
					false);

			String header = "";
			// Si on est en mode création d'exercice
			if (mode == 1) {
				header = header + "Création d'un exercice ";
			} else {
				header = header + "Modification d'un exercice ";
			}

			ET_name = (EditText) layoutBase
					.findViewById(R.id.exercise_create_details_base_name);

			// Si nom est défini, mettre à jour la vue
			if (nom != null) {
				ET_name.setText(nom);
			}

			IV_icon = (ImageView) layoutBase
					.findViewById(R.id.exercise_create_details_base_image);

			Button select_image = (Button) layoutBase
					.findViewById(R.id.exercise_create_details_base_image_button);
			select_image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startSelectImageDialog();
				}
			});

			// --------------------
			// Traitement pour des exercices de type
			// MUSCULATION
			// --------------------
			if (idkey_category == 1) {
				header = header + "de type Musculation";
				((TextView) layoutBase
						.findViewById(R.id.exercise_create_details_base_header))
						.setText(header);

				if (image == -1) {
					// définition de l'icone par défaut pour musculation
					image = R.drawable.muscu_icon_24;
				}
				IV_icon.setImageResource(image);

				// layoutSpec va contenir toutes les vues spécifiques à la
				// catégorie Musculation
				layoutSpec = (LinearLayout) inflater.inflate(
						R.layout.exercise_create_details_muscu,
						layoutContainer, false);

				ET_nbRepetitions = (EditText) layoutSpec
						.findViewById(R.id.exercise_create_details_muscu_nb_repetitions);

				// si nbRepetitions est deja défini, mettre a jour le contenu de
				// la vue
				if (nbRepetitions != -1) {
					ET_nbRepetitions.setText("" + nbRepetitions);
				}

				// de même
				ET_tpsRepos = (EditText) layoutSpec
						.findViewById(R.id.exercise_create_details_muscu_tps_repos);

				if (tpsRepos != null) {
					ET_tpsRepos.setText(tpsRepos);
				}

				Button validate = (Button) layoutSpec
						.findViewById(R.id.exercise_create_details_muscu_validate_button);
				validate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						validateExercise();
					}
				});

				Button reset = (Button) layoutSpec
						.findViewById(R.id.exercise_create_details_muscu_reset_button);
				reset.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						resetViewsContent();
					}
				});

				// ajout du nouveau layout au layout qui le contient
				layoutContainer.addView(layoutBase);
				layoutContainer.addView(layoutSpec);
			}

			// --------------------
			// Traitement pour des exercices de type
			// CARDIO
			// --------------------
			if (idkey_category == 2) {
				header = header + "de type Cardio";
				((TextView) layoutBase
						.findViewById(R.id.exercise_create_details_base_header))
						.setText(header);

				if (image == -1) {
					// définition de l'icone par défaut pour musculation
					image = R.drawable.cardio_icon_24;
				}
				IV_icon.setImageResource(image);

				layoutSpec = (LinearLayout) inflater.inflate(
						R.layout.exercise_create_details_cardio,
						layoutContainer, false);

				ET_parcours = (EditText) layoutSpec
						.findViewById(R.id.exercise_create_details_cardio_parcours);
				if (parcours != null) {
					ET_parcours.setText(parcours);
				}

				ET_temps = (EditText) layoutSpec
						.findViewById(R.id.exercise_create_details_cardio_temps);
				if (temps != -1) {
					ET_temps.setText("" + temps);
				}

				Button validate = (Button) layoutSpec
						.findViewById(R.id.exercise_create_details_cardio_validate_button);
				validate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						validateExercise();
					}
				});

				Button reset = (Button) layoutSpec
						.findViewById(R.id.exercise_create_details_cardio_reset_button);
				reset.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						resetViewsContent();
					}
				});

				layoutContainer.addView(layoutBase);
				layoutContainer.addView(layoutSpec);
			}

		}
	}

	/*
	 * Appelé lorsque l'utilisateur a choisi sa catégorie depuis l'activité
	 * principale
	 */
	public void setIdkey_category(long idkey_category) {

		// suppression des valeurs courantes
		resetValues();
		this.idkey_category = idkey_category;

		// création des vues des vues
		setContent();
	}

	/*
	 * Appelée lorsque l'utilisateur choisit un exercice depuis l'activité
	 * principale
	 */
	public void setExerciseModel(Exercise exercise) {
		if (idkey_category == exercise.getCategory().getId()) {
			nom = exercise.getNom();
			image = exercise.getImage();

			// Musculation
			if (idkey_category == 1) {
				nbRepetitions = exercise.getNbRepetitions();
				tpsRepos = exercise.getTpsRepos();
			}

			// Cardio
			if (idkey_category == 2) {
				parcours = exercise.getParcours();
				temps = exercise.getTemps();
			}
		}
		setContent();
	}

	public void setExerciseModelNoViewsUpdate(Exercise exercise) {

		idkey_category = exercise.getCategory().getId();
		nom = exercise.getNom();
		image = exercise.getImage();

		// Musculation
		if (idkey_category == 1) {
			nbRepetitions = exercise.getNbRepetitions();
			tpsRepos = exercise.getTpsRepos();
		}

		// Cardio
		if (idkey_category == 2) {
			parcours = exercise.getParcours();
			temps = exercise.getTemps();
		}

	}

	/*
	 * Récupère le contenu des views et met a jour les valeurs courantes
	 */
	void collectDataFromViews() {

		String st;
		// Traitement de(s) vue(s) commune(s)

		// vue contenant le nom de l'exercice
		if (ET_name != null) {
			st = ET_name.getText().toString();
			if (!st.equals("")) {
				nom = st;
			} else {
				// si le champs est vide, nom prend la valeur par defaut
				// null
				nom = null;
			}
		}

		// Traitement des vues qui concernent les exercices de type Musculation
		if (idkey_category == 1) {

			// meme procédés
			if (ET_nbRepetitions != null) {

				st = ET_nbRepetitions.getText().toString();
				if (!st.equals("")) {
					nbRepetitions = Integer.parseInt(st);
				} else {
					nbRepetitions = -1;
				}
			}
			if (ET_tpsRepos != null) {
				st = ET_tpsRepos.getText().toString();
				if (!st.equals("")) {
					tpsRepos = st;
				} else {
					tpsRepos = null;
				}
			}
		}

		// Traitement des vues qui concernent les exercices de type Cardio
		if (idkey_category == 2) {

			if (ET_parcours != null) {
				st = ET_parcours.getText().toString();
				if (!st.equals("")) {
					parcours = ET_parcours.getText().toString();
				} else {
					parcours = null;
				}
			}

			if (ET_temps != null) {
				st = ET_temps.getText().toString();
				if (!st.equals("")) {
					temps = Long.parseLong(st);
				} else {
					temps = -1;
				}
			}
		}

	}

	/*
	 * Reset les valeurs actuelles. Utilisée ensuite avec setContent pour
	 * recréer les vues
	 */
	void resetValues() {
		nom = null;
		image = -1;
		idkey_category = -1;

		// Musculation related data
		nbRepetitions = -1;
		tpsRepos = null;

		// Cardio related data
		parcours = null;
		temps = -1;
	}

	/*
	 * Réinitialise les valeurs actuelles + les champs qui sont dans les vues
	 * Contrairement a resetValues + setContent, cette fonction ne crée pas de
	 * vues mais utilise des vues deja initialisées. Utilisée lorsque
	 * l'utilisateur clique sur le bouton "Réinitialiser les champs"
	 */
	void resetViewsContent() {
		nom = null;
		ET_name.setText("");

		// Musculation
		if (idkey_category == 1) {

			image = R.drawable.muscu_icon_24;
			IV_icon.setImageResource(image);

			nbRepetitions = -1;
			tpsRepos = null;

			ET_nbRepetitions.setText("");
			ET_tpsRepos.setText("");
		}

		// Cardio
		if (idkey_category == 2) {

			image = R.drawable.cardio_icon_24;
			IV_icon.setImageResource(image);

			parcours = null;
			temps = -1;

			ET_parcours.setText("");
			ET_temps.setText("");
		}

	}

	/*
	 * Controle les données. Si les données sont valides, on construit une
	 * instance correspondante d'exercice et on fait appel à la méthode
	 * onCreateNewExercise(exercise) de detailsContainer (l'activité principale)
	 */
	void validateExercise() {

		// Récupere les données des vues
		collectDataFromViews();

		// --------------------
		// Traitement pour des exercices de type
		// MUSCULATION
		// --------------------
		if (idkey_category == 1) {
			boolean ok = true;

			if (nom == null) {
				Toast.makeText(getActivity(),
						"Choisissez un nom pour votre exercice",
						Toast.LENGTH_SHORT).show();
				ok = false;
			}

			if (nbRepetitions < 1) {
				Toast.makeText(
						getActivity(),
						"Choisissez un nombre de répétitions supérieur à 0 pour votre exercice",
						Toast.LENGTH_SHORT).show();
				ok = false;
			}

			// tpsRepos can be Null

			if (ok) {
				RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
						.getCategoryRuntimeDao();
				Category category = categoryDao.queryForId(idkey_category);

				Exercise e = new Exercise(nom, image, category);
				e.setMuscleSpec(nbRepetitions, tpsRepos);

				detailsContainer.onValidateExercise(e);
			}

		}
		// --------------------
		// Traitement pour des exercices de type
		// CARDIO
		// --------------------
		if (idkey_category == 2) {
			boolean ok = true;

			if (nom == null) {
				Toast.makeText(getActivity(),
						"Choisissez un nom pour votre exercice",
						Toast.LENGTH_SHORT).show();
				ok = false;
			}

			if (parcours == null) {
				Toast.makeText(getActivity(),
						"Choisissez un parcours pour votre exercice",
						Toast.LENGTH_SHORT).show();
				ok = false;
			}

			if (temps < 1) {
				Toast.makeText(
						getActivity(),
						"Définissez un temps supérieur à 0 pour votre exercice",
						Toast.LENGTH_SHORT).show();
				ok = false;
			}

			if (ok) {
				RuntimeExceptionDao<Category, Long> categoryDao = getHelper()
						.getCategoryRuntimeDao();
				Category category = categoryDao.queryForId(idkey_category);

				Exercise e = new Exercise(nom, image, category);
				e.setCardioSpec(parcours, temps);

				detailsContainer.onValidateExercise(e);
			}

		}

	}

	/*
	 * Met a jour image + Le contenu des vues correspondantes
	 */
	public void setImage(int img) {
		image = img;

		if (IV_icon != null) {
			IV_icon.setImageResource(img);
		}
	}
}
