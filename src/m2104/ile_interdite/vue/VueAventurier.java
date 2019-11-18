package m2104.ile_interdite.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import m2104.ile_interdite.util.Parameters;
import m2104.ile_interdite.util.Utils;
import m2104.ile_interdite.util.Message;


public class VueAventurier {
    private final IHM ihm;
    protected final Integer idAventurier ;
    protected final String power ;
    protected final String nomAventurier ;
    protected final String nomJoueur ;
    protected Color couleurActive ;
    protected Color couleurInactive ;
    private JButton btnAller = null ;
    private JPanel panelBoutons = null;
    private final JFrame window;
    private final JEditorPane labelTitre;
    private final JPanel mainPanel;
    private final JButton btnAssecher;
    private final JButton btnDonner;
    private final JButton btnPrendre;
    private final JButton btnDeplacer;
    private final JButton btnTerminer;
    private Boolean titreCliquable ;
    private boolean cartesActivees;

    public VueAventurier(IHM ihm, Integer id, String nomJoueur, String nomAventurier, String power, Integer num, Integer nbAventuriers, Color couleurActive, Color couleurInactive){
        this.ihm = ihm;
        this.idAventurier = id ;
        this.nomJoueur = nomJoueur ;
        this.nomAventurier = nomAventurier ;
        this.couleurActive = couleurActive ;
        this.couleurInactive = couleurInactive ;
        this.power = power ;
        this.couleurActive = couleurActive ;
        this.couleurInactive = couleurInactive ;
        this.titreCliquable = false ;
        this.cartesActivees = false;

        this.window = new JFrame();
        window.setSize(180, Parameters.HAUTEUR_VUE_AVENTURIER);
        this.window.setUndecorated(Parameters.UNDECORATED);
        this.window.setResizable(Parameters.RESIZABLE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int marginLeft = (dim.width - nbAventuriers * 190) / 2 ;
        window.setLocation(marginLeft + num * 190, Parameters.TOP_VUE_AVENTURIER);

        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(this.couleurActive, 2, false)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.labelTitre = new JEditorPane();
        this.labelTitre.setContentType("text/html");
        this.labelTitre.setText(getTitle(Color.WHITE, this.couleurInactive));
        this.labelTitre.setOpaque(false);
        this.labelTitre.setEditable(false);
        this.labelTitre.setBorder(new MatteBorder(0, 0, 2, 0, this.couleurActive));
        mainPanel.add(this.labelTitre, BorderLayout.NORTH);
        labelTitre.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (titreCliquable) {
                    Message message = new Message(Utils.Commandes.RECEVOIR, idAventurier, null, null, null);
                    System.out.println(message);
                    ihm.notifierObservateurs(message);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,1));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        JPanel panelBoutons_ligne1 = new JPanel(new GridLayout(1, 3));
        panelBoutons_ligne1.setOpaque(false);
        panelBoutons.add(panelBoutons_ligne1);

        this.btnAller = creerBouton(1, "Aller", Utils.Commandes.BOUGER, "Se déplacer vers une tuile") ;
        panelBoutons_ligne1.add(btnAller);

        this.btnAssecher = creerBouton(2, "Sécher", Utils.Commandes.ASSECHER, "Assécher une tuile") ;
        panelBoutons_ligne1.add(btnAssecher);

        this.btnDonner = creerBouton(3, "Donner", Utils.Commandes.DONNER, "Donner une carte à un autre joueur") ;
        panelBoutons_ligne1.add(btnDonner);

        JPanel panelBoutons_ligne2 = new JPanel(new GridLayout(1, 3));
        panelBoutons_ligne2.setOpaque(false);
        panelBoutons.add(panelBoutons_ligne2);

        this.btnPrendre = creerBouton(4, "Prendre", Utils.Commandes.RECUPERER_TRESOR, "Récupérer le trésor de la tuile courante") ;
        panelBoutons_ligne2.add(btnPrendre);

        this.btnDeplacer = creerBouton(5, "Bouger", Utils.Commandes.DEPLACER, "Déplacer un autre joueur vers une tuile adjacente") ;
        panelBoutons_ligne2.add(btnDeplacer);

        this.btnTerminer = creerBouton(6, "Finir", Utils.Commandes.TERMINER, "Terminer son tour") ;
        panelBoutons_ligne2.add(btnTerminer);

        this.window.setVisible(true);
        mainPanel.repaint();
    }

    public void activerBoutons(Boolean activerMove, Boolean activerDry, Boolean activerDonner, Boolean activerRecuperer, Boolean activerRecevoir, Boolean activerDeplacer, Boolean activerTerminer) {
        if (Parameters.LOGS) {
            System.out.println(this.nomAventurier + " : VueAventurier.activerBoutons(activerMove=" + activerMove + ", activerDry=" + activerDry + ", activerDonner=" + activerDonner + ", activerRecuperer=" + activerRecuperer + ", activerRecevoir=" + activerRecevoir + ", activerTerminer=" + activerTerminer + ")");
        }
        if (activerMove != null) {
            btnAller.setEnabled(activerMove);
            if (!activerMove) {
                btnAller.setForeground(Color.BLACK);
            }
        }

        if (activerDry != null) {
            btnAssecher.setEnabled(activerDry);
            if (!activerDry) {
                btnAssecher.setForeground(Color.BLACK);
            }
        }

        if (activerDonner != null) {
            btnDonner.setEnabled(activerDonner);
            if (!activerDonner) {
                btnDonner.setForeground(Color.BLACK);
            }
        }

        if (activerRecuperer != null) {
            btnPrendre.setEnabled(activerRecuperer);
            if (!activerRecuperer) {
                btnPrendre.setForeground(Color.BLACK);
            }
        }

        if (activerRecevoir != null) {
            if (Parameters.LOGS) {
                System.out.println("VueAventurier_nopic.activerBoutons.activerRecevoir = " + activerRecevoir);
            }
            this.titreCliquable = activerRecevoir ;
            labelTitre.setCursor(new Cursor((activerRecevoir ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR)));
            labelTitre.setToolTipText(activerRecevoir ? "Recevoir la carte d'un autre joueur" : "");
        }

        if (activerDeplacer != null) {
            btnDeplacer.setEnabled(activerDeplacer);
            if (!activerDeplacer) {
                btnDeplacer.setForeground(Color.BLACK);
            }
        }

        if (activerTerminer != null) {
            btnTerminer.setEnabled(activerTerminer);
            if (!activerTerminer) {
                btnTerminer.setForeground(Color.BLACK);
            }
        }
    }

    // ====================================================== Activation de la carte à son tour
    public void activer() {
        if (Parameters.LOGS) {
            System.out.println("VueAventurier.activer() de " + this.nomAventurier);
        }
        this.labelTitre.setText(getTitle(Color.WHITE, this.couleurActive));
        this.mainPanel.repaint();
    }

    public void desactiver() {
        if (Parameters.LOGS) {
            System.out.println(this.nomAventurier + " : VueAventurier.desactiver()");
        }
        this.labelTitre.setText(getTitle(Color.WHITE, this.couleurInactive));
        this.mainPanel.repaint();
    }

    // ====================================================== Getters et Setters

    private JButton creerBouton(Integer numBouton, String libelle, Utils.Commandes commande, String tooltip) {
        JButton bouton = new JButton();
        bouton.setOpaque(false);
        bouton.setEnabled(false);
        bouton.setToolTipText(tooltip);
        bouton.setText(libelle);
        bouton.setBorder(new MatteBorder( 0, 0, (numBouton <= 3 ? 1 : 0), (numBouton%3!=0 ? 1 : 0), Color.GRAY));

        bouton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JButton btnClique = (JButton) e.getSource();
                btnAller.setForeground(Color.BLACK);
                btnAssecher.setForeground(Color.BLACK);
                btnDonner.setForeground(Color.BLACK);
                btnPrendre.setForeground(Color.BLACK);

                if (btnClique == btnAller || btnClique == btnAssecher || btnClique == btnDonner || btnClique == btnPrendre) {
                    btnClique.setForeground(couleurActive);
                }
                ihm.notifierObservateurs(new Message(commande, idAventurier, null, null, null));
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setFont(btn.getFont().deriveFont(Font.BOLD));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getSource();
                btn.setFont(btn.getFont().deriveFont(Font.PLAIN));
            }
        });
        return bouton;
    }


    // ====================================================== Utils
    public String getTitle(Color color, Color backgroundColor) {
        return "<html>" +
                    "<h1 style=\"text-align:center; margin : 0 ; color:" + Utils.toRGB(color) + "; " +
                            (backgroundColor == null ? "" :  "background-color:" + Utils.toRGB(backgroundColor) + "; ") +
                            "font-size:120%;\">" +
                        this.nomAventurier +
                    "</h1>" +
                    "<h2 style=\"text-align:center; margin : 0 ; color:" + Utils.toRGB(color) + "; " +
                            (backgroundColor == null ? "" :  "background-color:" + Utils.toRGB(backgroundColor) + "; ") +
                            "font-size:90%;\">" +
                        this.nomJoueur +
                    "</h2>" +
                "</html>" ;
    }

    // ====================================================== Getters et Setters
    public Integer getIdAventurier() {
        return this.idAventurier ;
    }
}
