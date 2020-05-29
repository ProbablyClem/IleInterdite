package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;

/**
 *
 * @author IUT2-Dept Info
 */
public abstract class Aventurier {
    
    int id;
    private String role;
    private Utils.Pion pion;

    Aventurier (int id, String role, Utils.Pion pion) {
        this.id = id;
        this.role = role;
        this.pion = pion;
    }

    public String getRole() {
        return this.role;
    }

    public void seDeplacer() {
        // TODO: function core
    }

    public void passerTour() {
        // TODO: function core
    }

    public void assecher() {
        // TODO: function core
    }

    public void donnerCarte() {
        // TODO: function core
    }

    public void prendreTresor() {
        // TODO: function core
    }

    public void tirerCarte() {
        // TODO: function core
    }

    public abstract void actionSpeciale();
}
