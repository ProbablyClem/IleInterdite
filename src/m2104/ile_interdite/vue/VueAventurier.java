package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.*;
import m2104.ile_interdite.util.Message;
import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueAventurier extends JPanel implements ActionListener {
    private IHM ihm;
    private String capaciteSpecial;
    private Aventurier aventurier;
    private RichJLabel nomAventurier;
    private RichJLabel nbActionsLabel;
    private JLabel textField;
    private VueMain vueMain;
    private Button seDeplacer;
    private Button assecher;
    private Button finirTour;
    private Button actionSpecial;
    private Button donnerCarte;
    private Button prendreTresor;

    private Button defausseTresor;
    private Button defausseInondation;



    public VueAventurier(IHM ihm, Aventurier aventurier, String capaciteSpecial){
        this.aventurier = aventurier;
        this.ihm = ihm;
        this.capaciteSpecial = capaciteSpecial;

        draw();
    }

    public void setMessage(String message){
        this.textField.setText(message);
        this.repaint();
    }

    public IHM getIhm() {
        return ihm;
    }

    public void updateActions(){
        nbActionsLabel.setText("Actions restantes : " + aventurier.getActions() + "/3 ");
    }

    private void draw(){
        this.removeAll();
        this.setOpaque(false);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel(new BorderLayout());

        nomAventurier = new RichJLabel(aventurier.getNom() + ":" + aventurier.getRole(),0);
        nomAventurier.setFont(new Font("Roboto",Font.BOLD,20));

        nomAventurier.setForeground(aventurier.getPion().getCouleur());

        header.add(nomAventurier, BorderLayout.CENTER);

        nbActionsLabel = new RichJLabel("Actions restantes : " + aventurier.getActions() + "/3 ",0);
        nbActionsLabel.setFont(new Font("Roboto",Font.BOLD,20));
        nbActionsLabel.setForeground(aventurier.getPion().getCouleur());

        header.add(nbActionsLabel, BorderLayout.EAST);
        header.setOpaque(false);



        textField = new JLabel("Choisir une action");
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setVerticalAlignment(JLabel.CENTER);
        textField.setFont(new Font("Roboto",Font.BOLD,20));
        header.add(textField,BorderLayout.SOUTH);
        this.add(header);


        JPanel actionsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        actionsPanel.setOpaque(false);
        seDeplacer = new Button("Se Deplacer",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        seDeplacer.setForeground(Color.WHITE);
        seDeplacer.setFont(new Font("Roboto", Font.BOLD, 19));

        seDeplacer.setBorder(new RoundedBorder(20));
        seDeplacer.addActionListener(this);
        actionsPanel.add(seDeplacer);

        assecher = new Button("Assecher",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        assecher.setForeground(Color.WHITE);
        assecher.setFont(new Font("Roboto", Font.BOLD, 19));
        assecher.addActionListener(this);
        actionsPanel.add(assecher);

        finirTour = new Button("Finir Tour",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        finirTour.setForeground(Color.WHITE);
        finirTour.setFont(new Font("Roboto", Font.BOLD, 19));
        finirTour.addActionListener(this);
        actionsPanel.add(finirTour);

        actionSpecial = new Button("Déplacer un joueur",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        actionSpecial.setForeground(Color.WHITE);
        actionSpecial.setFont(new Font("Roboto", Font.BOLD, 19));

        actionSpecial.addActionListener(this);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);
        actionsPanel.add(aventurier instanceof Navigateur ? actionSpecial : emptyPanel);

        donnerCarte = new Button("Donner Carte",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        donnerCarte.setForeground(Color.WHITE);
        donnerCarte.setFont(new Font("Roboto", Font.BOLD, 19));

        donnerCarte.addActionListener(this);
        actionsPanel.add(donnerCarte);

        prendreTresor = new Button("Prendre Tresor",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        prendreTresor.setForeground(Color.WHITE);
        prendreTresor.setFont(new Font("Roboto", Font.BOLD, 19));

        prendreTresor.addActionListener(this);
        actionsPanel.add(prendreTresor);

        this.add(actionsPanel);

        vueMain = new VueMain(aventurier.getCartes(),this);
        vueMain.setOpaque(false);
        this.add(vueMain);

        JPanel decks = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));


        defausseTresor = new Button("<html><body>Defausse <br>Carte <br>   Tresor</body></html>",110,90,Color.decode("#db7a12"),Color.decode("#ff9d33"));
        defausseTresor.setForeground(Color.WHITE);
        defausseTresor.setFont(new Font("Roboto", Font.BOLD, 15));
        defausseTresor.addActionListener(this);
        decks.setOpaque(false);
        decks.add(defausseTresor);


        defausseInondation = new Button("<html><body>Defausse<br>Carte<br>Inondation</body></html>",110,90,Color.decode("#1d79bf"),Color.decode("#2b92e0"));
        defausseInondation.setForeground(Color.WHITE);
        defausseInondation.setFont(new Font("Roboto", Font.BOLD, 15));
        defausseInondation.addActionListener(this);
        decks.add(defausseInondation);




        this.add(decks);

        this.setPreferredSize(new Dimension(700,700));
    }

    public void update(Aventurier a){
        this.aventurier = a;
        removeAll();
        draw();
        validate();
        repaint();
    }

    public Aventurier getAventurier() {
        return aventurier;
    }

    public void DesactiverBoutons(){
        seDeplacer.setEnabled(false);
        if (!(aventurier instanceof Ingenieur && !((Ingenieur) aventurier).isEtatAS())) {
            assecher.setEnabled(false);
        }
        actionSpecial.setEnabled(false);
        donnerCarte.setEnabled(false);
        prendreTresor.setEnabled(false);
    }

    public void ActiverBoutons(){
        seDeplacer.setEnabled(true);
        assecher.setEnabled(true);
        actionSpecial.setEnabled(true);
        donnerCarte.setEnabled(true);
        prendreTresor.setEnabled(true);
    }

    public Carte getCarteSelectionne(){
        return vueMain.getCarteSelectionnée();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == defausseTresor){
            Message m = Message.voirDeck(Utils.Deck.DEFFAUSSE_TRESOR);
            ihm.notifierObservateurs(m);
        }
        else if (e.getSource() == defausseInondation){
            Message m = Message.voirDeck(Utils.Deck.DEFFAUSSE_INONDATION);
            ihm.notifierObservateurs(m);
        }
        else if(e.getSource() == seDeplacer){
            ihm.notifierObservateurs(Message.deplacer(aventurier));
        }
        else if(e.getSource() == assecher){
            ihm.notifierObservateurs(Message.assecher(aventurier));
        }
        else if(e.getSource() == finirTour){
            ihm.notifierObservateurs(Message.terminer(aventurier));
        }
        else if(e.getSource() == actionSpecial){
            if (aventurier instanceof Navigateur) {
                ihm.notifierObservateurs(Message.AS_Navigateur());
            } else {
                ihm.notifierObservateurs(Message.actionSpecial());
            }
        }
        else if(e.getSource() == donnerCarte){
            ihm.notifierObservateurs(Message.donner(aventurier));
        }
        else if(e.getSource() == prendreTresor){
            ihm.notifierObservateurs(Message.recupererTresor(aventurier));
        }
    }


    private static class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
