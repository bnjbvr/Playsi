package models;

import com.avaje.ebean.validation.Length;
import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Benjamin
 * Date: 12/03/13
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Entreprise extends Model {

    @Id
    private Long id;

    @Length(max=35)
    private String nom;

    private String description;

    @Length(max=50)
    private String secteur;

    private String commentaire;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ContactEntreprise> contacts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CommentaireEntreprise> commentaires;

    // généré par l'IDE

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public List<ContactEntreprise> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactEntreprise> contacts) {
        this.contacts = contacts;
    }

    public List<CommentaireEntreprise> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<CommentaireEntreprise> commentaires) {
        this.commentaires = commentaires;
    }
}