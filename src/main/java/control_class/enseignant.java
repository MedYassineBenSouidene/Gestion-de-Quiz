/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control_class;

import Interfaces.Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class enseignant extends personne{
    
    private ArrayList<Quiz> quizs;
    
    
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;

    public enseignant() {
        super();
        quizs = new ArrayList<Quiz>();
    }

    public enseignant(int id, String username, String password, int cox) {
        super(id, username, password, cox);
        this.quizs = new ArrayList<Quiz>();
    }
    
    public enseignant(String username, String password) {
        super(username, password);
        this.quizs = new ArrayList<Quiz>();
    }

    public ArrayList<Quiz> getQuizs() {
        return quizs;
    }

    public void setQuizs(ArrayList<Quiz> quizs) {
        this.quizs = quizs;
    }
    
    public Boolean connxion(){
        Boolean existe=false;
        try {
            String url="jdbc:mysql://localhost:3306/projet_poo";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
            rs = st.executeQuery("select * from enseignant where username='"+getUsername()+"' and password='"+getPassword()+"'");
            if (rs.next()){
                existe=true;
            }else{
                JOptionPane.showMessageDialog(null,"connection invalide");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur connexion enseig!");
        }
        return existe;
    }
    
    public void connecter(){
        try {
                String url="jdbc:mysql://localhost:3306/projet_poo";
                con = DriverManager.getConnection(url,"root","");
                st = con.createStatement();
                st.executeUpdate("update enseignant set connexion=1 where username='"+getUsername()+"' and password='"+getPassword()+"'");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erreur cn1 enseig2 !");
            }
    }
    
    public void ajouter(){
        try {
            String url="jdbc:mysql://localhost:3306/projet_poo";
            con = DriverManager.getConnection(url,"root","");
            pst = con.prepareStatement("insert into enseignant (`username`, `password`) values(?,?)");
            pst.setString(1, getUsername());
            pst.setString(2, getPassword());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "ajout fait avec success");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erreur enseig3!");
        }
    }
    
    public int quiConnecter(){
        int id=-1;
        try {
            String url="jdbc:mysql://localhost:3306/projet_poo";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
            rs = st.executeQuery("select id_en from enseignant where connexion = 1");
            rs.next();
            id = rs.getInt(1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur  enseig4!");
        }
        return id;
    }
    
    public Boolean existe(){
        Boolean test=false;
        try {
            String url="jdbc:mysql://localhost:3306/projet_poo";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
            rs = st.executeQuery("select * from enseignant where username='"+getUsername()+"'");
            if (rs.next()){
                test=true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"erreur enseig5!");
        }
        return test;
    }
    
    public void deconnexion(){
        try {
                String url="jdbc:mysql://localhost:3306/projet_poo";
                con = DriverManager.getConnection(url,"root","");
                st = con.createStatement();
                st.executeUpdate("update enseignant set connexion=0 where id_en="+getId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "erreur enseig6!");
            }
    }
}
