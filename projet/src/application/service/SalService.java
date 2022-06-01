package application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import application.connexion.Connexion;
import application.entities.Employes;
import application.entities.Salarié;
import application.entities.Vendeur;

public class SalService {
	public Salarié getSalarie(int id) {
		try {
			String reqRole = "Select role from salaire where matricule = " + id;
			Statement stmt = Connexion.getCn().createStatement();
			ResultSet rs = stmt.executeQuery(reqRole);
			if (rs.next()) {
				if (rs.getString(1).equals("Vendeur")) {
					String req = "Select s.matricule, nom, email, role, anneRecruit,(salaire + Vente * Pourcentage) from salaire s, vendeur v where s.matricule = v.Matricule and s.matricule = "
							+ id;
					ResultSet rsv = stmt.executeQuery(req);
					if (rsv.next())
						return new Salarié(rsv.getInt(1), rsv.getString(2), rsv.getString(3), rsv.getString(4),
								rsv.getDouble(5), rsv.getDouble(6));
				} else {
					String req = "Select s.matricule, nom, email, role, anneRecruit,(salaire + HSupp * PHSupp) from salaire s, employe v where s.matricule = v.Matricule and s.matricule = "
							+ id;
					ResultSet rsv = stmt.executeQuery(req);
					if (rsv.next())
						return new Salarié(rsv.getInt(1), rsv.getString(2), rsv.getString(3), rsv.getString(4),
								rsv.getDouble(5), rsv.getDouble(6));
				}
			}
		} catch (SQLException ex) {
			System.out.println("SQL ERROR" + ex);
		}
		return null;
	}
	


	public boolean createEmploye(Employes E) {
		try {
			Statement stmt = Connexion.getCn().createStatement();
			if (E.getAnneRecruit() < 2005.0)
				E.setSalaire(400);
			else
				E.setSalaire(280);
			System.out.println(E.getMatricule());
			String req = "Insert into salaire(matricule,nom,email,anneRecruit,salaire,role) values(" + E.getMatricule()
					+ ",'" + E.getNom() + "','" + E.getEmail() + "'," + E.getAnneRecruit() + "," + E.getSalaire()
					+ ",'Employe');";
			if (stmt.executeUpdate(req) == 1) {
				String reqEmployee = "Insert into employe values (" + E.getMatricule() + "," + E.getHsupp() + ","
						+ E.getPhsupp() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public boolean createVendeur(Vendeur V) {
		try {
			Statement stmt = Connexion.getCn().createStatement();
			if (V.getAnneRecruit() < 2005)
				V.setSalaire(400);
			else
				V.setSalaire(280);
			String req = "Insert into salaire(matricule,nom,email,anneRecruit,salaire,role) values(" + V.getMatricule()
					+ ",'" + V.getNom() + "','" + V.getEmail() + "'," + V.getAnneRecruit() + "," + V.getSalaire()
					+ ",'Vendeur');";
			if (stmt.executeUpdate(req) == 1) {
				String reqEmployee = "Insert into vendeur values (" + V.getMatricule() + "," + V.getVente() + ","
						+ V.getPourcentage() + ");";
				if (stmt.executeUpdate(reqEmployee) == 1) {
					return true;
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public List<Salarié> listerEmploye() {
		List<Salarié> emps = new ArrayList<>();
		try {
			String reqE = "SELECT s.matricule, nom, email, role, anneRecruit, (salaire + HSupp * PHSupp) as salaireTot  from salaire s, employe e where s.matricule = e.Matricule;";
			Statement stmt = Connexion.getCn().createStatement();
			ResultSet rsE = stmt.executeQuery(reqE);

			while (rsE.next()) {
				emps.add(new Salarié(rsE.getInt(1), rsE.getString(2), rsE.getString(3), rsE.getString(4),
						rsE.getDouble(5), rsE.getDouble(6)));
			}
			return emps;

		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List<Salarié> listerVendeur() {
		List<Salarié> vdrs = new ArrayList<>();
		try {
			String reqV = "SELECT s.matricule, nom, email, anneRecruit, (salaire + vente * pourcentage) as salaireTot, role from salaire s, vendeur v where s.matricule = v.Matricule;";
			Statement stmt = Connexion.getCn().createStatement();
			ResultSet rsE = stmt.executeQuery(reqV);
			while (rsE.next()) {
				vdrs.add(new Salarié(rsE.getInt(1), rsE.getString(2), rsE.getString(3), rsE.getString(6),
						rsE.getDouble(4), rsE.getDouble(5)));
			}
			return vdrs;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;

	}
	public boolean delete(int id) {
		 {
				try {
					String req = "Delete from salaire where matricule =" + id;
					Statement stmt = Connexion.getCn().createStatement();
					if (stmt.executeUpdate(req) == 1) {
						return true;
					}
				} catch (SQLException ex) {
					System.out.println("SQL EXCEPTION");
				}
				return false;
			}
		}
	public void modifieremp(Employes e) 
		{
			try {
				String req = "update salaire set matricule=" + e.getMatricule() + ",nom='" + e.getNom() + "', email='"+ e.getEmail() + "', anneRecruit=" + e.getAnneRecruit() + " where matricule=" + e.getMatricule() ; 			
				Statement stmt = Connexion.getCn().createStatement();
if(stmt.executeUpdate(req)==1) {
	System.out.println("modifier de salaire")
;}
           String r ="update employe set matricule=" + e.getMatricule() + ",nom='" + e.getNom() + "', email='"+ e.getEmail() + "', anneRecruit=" + e.getAnneRecruit() + " where matricule=" + e.getMatricule() ; 			
           if(stmt.executeUpdate(req)==1) {
        		System.out.println("modifier de employe")
        	;}
			}catch(SQLException ex) {
				ex.printStackTrace();

		}
	}
	public Employes employeDetails(int id) {

		try {
			String req = "SELECT s.matricule, nom, email, anneRecruit,(salaire + HSupp * PHSupp) as slt,  HSupp, PHSupp, role  from employee e, salarie s where e.Matricule = s.matricule and e.Matricule ="
					+ id;
			Statement stmt = Connexion.getCn().createStatement();
			ResultSet rs = stmt.executeQuery(req);

			if (rs.next())
				return new Employes(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(8), rs.getDouble(4),
						rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));

		} catch (SQLException ex) {
ex.printStackTrace();		}
		return null;
	}
	public Vendeur VendeurDetails(int vdrId) {
		try {
			String req = "SELECT s.matricule, nom, email, anneRecruit,(salaire + Vente * Pourcentage) as salaireTot, Vente, Pourcentage, role from vendeur v, salaire s where v.Matricule = s.matricule and  v.Matricule ="
					+ vdrId;
			Statement stmt = Connexion.getCn().createStatement();
			ResultSet rs = stmt.executeQuery(req);

			if (rs.next()) {
				return new Vendeur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(8), rs.getDouble(4),
						rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
			}

		} catch (SQLException ex) {
			System.out.println("Erreur SQL " + ex);
		}
		return null;
	}	
	public Employes findEmployeById(int mat) {
		try {
			Statement st =Connexion.getCn().createStatement();
			Statement st1 =Connexion.getCn().createStatement();

			ResultSet res=st.executeQuery("SELECT * FROM salaire WHERE matricule="+mat);
			ResultSet res1=st1.executeQuery("SELECT * FROM employe WHERE matricule="+mat);

			if(res.next() && res1.next()) {
				System.out.println("employe trouve");
				return new Employes(res.getInt("matricule"),res.getString("nom"),res.getString("email"),res.getString("role"),res.getDouble("anneRecruit"),res.getDouble("salaire")+res1.getDouble("HSupp")*res1.getDouble("PHSupp"),res1.getDouble("HSupp"), mat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Vendeur findVendeurById(int mat) {
		try {
			Statement st =Connexion.getCn().createStatement();
			Statement st1 =Connexion.getCn().createStatement();

			ResultSet res=st.executeQuery("SELECT * FROM salaire WHERE matricule="+mat);
			ResultSet res1=st1.executeQuery("SELECT * FROM vendeur WHERE matricule="+mat);

			if(res.next() && res1.next()) {
				System.out.println("vendeur trouve");
				return new Vendeur(res.getInt("matricule"),res.getString("nom"),res.getString("email"),res.getString("role"),res.getDouble("anneRecruit"),res.getDouble("salaire")+res1.getDouble("Vente")*res1.getDouble("Pourcentage"),res1.getDouble("Vente"), mat);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Salarié> anciennete() {
		List<Salarié> Lsalarie = new ArrayList<Salarié>();
		try {
			Statement st = Connexion.getCn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM salaire ORDER BY anneRecruit ASC");
			while (res.next()) {
				if (res.getString("role").equals("Employe")) {
					SalService es = new SalService();
					Employes emp = es.findEmployeById(res.getInt("matricule"));
					
					Lsalarie.add(new Salarié(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
							res.getString("role"), res.getDouble("anneRecruit"),
							res.getDouble("salaire") ));
					
				}else
					if(res.getString("role").equals("Vendeur")) {
						SalService vs = new SalService();
						Vendeur vend = vs.findVendeurById(res.getInt("matricule"));
						Lsalarie.add(new Salarié(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
								res.getString("role"), res.getDouble("anneRecruit"),
								res.getDouble("salaire")));
					}
			}
			System.out.println("lister");
			return Lsalarie;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<Salarié> SalariesMinMax(double min, double max) {

		List<Salarié> salaries = new ArrayList<>();
		SalService S = new SalService();
		List<Salarié> a = S.listerEmploye();
		List<Salarié> b = S.listerVendeur();
		for (Salarié i : a) {
			salaries.add(i);
		}

		for (Salarié i : b) {
			salaries.add(i);
		}
		 
		Iterator<Salarié> it = salaries.iterator();
		
		while (it.hasNext()) {
			Salarié s = it.next();
			if(s.getSalaire()> max || s.getSalaire() < min) {
				it.remove();
			}
		}
		
		return salaries;

	}
	public static void main(String Args[]) {
		// SalService S = new SalService();

	}
}