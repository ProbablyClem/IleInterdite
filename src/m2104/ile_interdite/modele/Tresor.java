package m2104.ile_interdite.modele;

import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.vue.ImagePanel;

public class Tresor {
    private Utils.Tresor tresor;

    public Tresor(Utils.Tresor tresor) {
        this.tresor = tresor;
    }

    public ImagePanel getImage(){
        return new ImagePanel(tresor.getPathPicture());
    }

    public Utils.Tresor getTresor() {
        return tresor;
    }
}
