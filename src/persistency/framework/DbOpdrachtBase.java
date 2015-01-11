package persistency.framework;
	import java.sql.SQLException;
import javax.sql.RowSet;
import persistency.DbSqlHandler;
import model.Leraar;
import model.Opdracht;
import model.opdrachten.OpdrachtCategorie;
import model.opdrachten.OpdrachtTypen;
import utils.date.gregorian.Datum;
public abstract class DbOpdrachtBase {

		private int id; 
		private String vraag;
		private String juisteAntwoord;
		private int maxAantalPogingen;
		private int maxAntwoordTijd;
	//	private List<QuizOpdracht> quizOpdrachten;
		private OpdrachtCategorie categorie;
		private Datum datumRegistratie;
		private Leraar auteur;
		private String hint; 
		public abstract OpdrachtTypen getType();
		public abstract String[] asStringArray();	
		public abstract Opdracht CreateOpdracht();
		public abstract void SaveData(DbSqlHandler sqlHandler) throws SQLException;
		
		/**
		* Returns String to save in TXTdatabase
		* String array fields:
		* 0: opdrachtID
		* 1: Vraag
		* 2: Antwoord
		* 3: Categorie
		* 4: antwoordHint
		* 5: maxAantalPogingen
		* 6: maxAntwoordTijdInSec
		* 7: Creatie datum
		* 8: Auteur
		* 9: OpdrachtType
		*/
		protected void fillStringArray(String[] data)
		{
			if (data != null && data.length >= 9)
			{
				data[0] = Integer.toString(getId());
				data[9] = getType().name();
				data[1] = getVraag();
				data[2] = getJuisteAntwoord();
				data[3] = getCategorie().name();
				data[4] = getHint();
				data[5] = Integer.toString(getMaxAantalPogingen());
				data[6] = Integer.toString(getMaxAntwoordTijd());				
				data[7] = getDatumRegistratie().getEuropeanFormat();
				data[8] = getAuteur().name();
			}
		}
		
		DbOpdrachtBase(RowSet row) throws SQLException
		{
			this.id = row.getInt("OpdrachtID");
			this.vraag = row.getString("Vraag");
			this.juisteAntwoord = row.getString("JuisteAntwoord");
			this.categorie = OpdrachtCategorie.valueOf(row.getString("Categorie"));
			this.hint = row.getString("Hints");
			this.maxAantalPogingen = row.getInt("maxAantalpogingen");
			this.maxAntwoordTijd = row.getInt("maxAntwoordTijd");				
			this.datumRegistratie = new Datum(row.getDate("datumRegistratie"));
			this.auteur = Leraar.valueOf(row.getString("auteur"));	
		}
		
		DbOpdrachtBase(String[] dataRow)
		{
			this.id = Integer.parseInt(dataRow[0]);
			this.vraag = dataRow[1];
			this.juisteAntwoord = dataRow[2];
			this.categorie = OpdrachtCategorie.valueOf(dataRow[3]);
			this.hint = dataRow[4];
			this.maxAantalPogingen = Integer.parseInt(dataRow[5]);
			this.maxAntwoordTijd = Integer.parseInt(dataRow[6]);				
			this.datumRegistratie = new Datum(dataRow[7]);
			this.auteur = Leraar.valueOf(dataRow[8]);
		}
		
		DbOpdrachtBase(Opdracht opdracht) {
			this.id = opdracht.getOpdrachtID();
			this.vraag = opdracht.getVraag();
			this.hint = opdracht.getAntwoordHint();
			this.juisteAntwoord = opdracht.getJuisteAntwoord();
			this.maxAntwoordTijd = opdracht.getMaxAntwoordTijdInSec();
			this.categorie = opdracht.getCategorie();
			this.datumRegistratie = opdracht.getRegistratie();
			this.auteur = opdracht.getAuteur();
		}

		public int getId()
		{ return id; }
		
		public String getHint() {
			return hint;
		}
		
		public String getVraag() {
			return vraag;
		}

		public void setVraag(String vraag) {
			this.vraag = vraag;
		}

		public String getJuisteAntwoord() {
			return juisteAntwoord;
		}

		public void setJuisteAntwoord(String juisteAntwoord) {
			this.juisteAntwoord = juisteAntwoord;
		}

		public int getMaxAantalPogingen() {
			return maxAantalPogingen;
		}

		public void setMaxAantalPogingen(int maxAantalPogingen) {
			this.maxAantalPogingen = maxAantalPogingen;
		}

		public int getMaxAntwoordTijd() {
			return maxAntwoordTijd;
		}

		public void setMaxAntwoordTijd(int maxAntwoordTijd) {
			this.maxAntwoordTijd = maxAntwoordTijd;
		}

//		public List<QuizOpdracht> getQuizOpdrachten() {
//			return quizOpdrachten;
//		}
//
//		public void setQuizOpdrachten(List<QuizOpdracht> quizOpdrachten) {
//			this.quizOpdrachten = quizOpdrachten;
//		}

		public OpdrachtCategorie getCategorie() {
			return categorie;
		}

		public void setCategorie(OpdrachtCategorie categorie) {
			this.categorie = categorie;
		}

		public Datum getDatumRegistratie() {
			return datumRegistratie;
		}

		public void setDatumRegistratie(Datum datumRegistratie) {
			this.datumRegistratie = datumRegistratie;
		}

		public Leraar getAuteur() {
			return auteur;
		}

		public void setAuteur(Leraar auteur) {
			this.auteur = auteur;
		}
	
}
