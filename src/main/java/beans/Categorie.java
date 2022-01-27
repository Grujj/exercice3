package beans;

public class Categorie {
	
	private String titre;

	public Categorie() {
		super();
	}

	public Categorie(String titre) {
		super();
		this.titre = titre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@Override
	public String toString() {
		return "Categorie [titre=" + titre + "]";
	}
}
