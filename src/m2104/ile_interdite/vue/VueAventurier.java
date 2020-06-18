package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
import m2104.ile_interdite.modele.Carte;
import m2104.ile_interdite.modele.CarteTresor;
import m2104.ile_interdite.modele.Messager;
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
        nbActionsLabel.setText("Actions restantes : " + aventurier.getActions() + " /3");
    }

    private void draw(){
        this.removeAll();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel(new BorderLayout());

        nomAventurier = new RichJLabel(aventurier.getNom() + ":" + aventurier.getRole(),0);
        nomAventurier.setFont(new Font(nomAventurier.getFont().getFontName(), Font.PLAIN, 30));

        nomAventurier.setForeground(aventurier.getPion().getCouleur());

        header.add(nomAventurier, BorderLayout.CENTER);

        nbActionsLabel = new RichJLabel("Actions restantes :" + aventurier.getActions() + " /3",0);
        nbActionsLabel.setFont(new Font(nbActionsLabel.getFont().getFontName(), Font.PLAIN, 27));
        nbActionsLabel.setForeground(Color.decode("#e3e3e3"));
        header.add(nbActionsLabel, BorderLayout.EAST);
        header.setBackground(Color.white);



        textField = new JLabel("Choisir une action");
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setVerticalAlignment(JLabel.CENTER);
        textField.setFont(new Font("Roboto",Font.BOLD,20));
        header.add(textField,BorderLayout.SOUTH);
        this.add(header);


        JPanel actionsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        actionsPanel.setBackground(Color.WHITE);
        seDeplacer = new Button("Se Deplacer",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        seDeplacer.setBorder(new RoundedBorder(20));
        seDeplacer.addActionListener(this);
        actionsPanel.add(seDeplacer);

        assecher = new Button("Assecher",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        assecher.setBorder(new RoundedBorder(20));
        assecher.addActionListener(this);
        actionsPanel.add(assecher);

        finirTour = new Button("Finir Tour",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        finirTour.setBorder(new RoundedBorder(20));
        finirTour.addActionListener(this);
        actionsPanel.add(finirTour);

        actionSpecial = new Button(capaciteSpecial,80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        actionSpecial.setBorder(new RoundedBorder(20));
        actionSpecial.addActionListener(this);
        actionsPanel.add(actionSpecial);

        donnerCarte = new Button("Donner Carte",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        donnerCarte.setBorder(new RoundedBorder(20));
        donnerCarte.addActionListener(this);
        actionsPanel.add(donnerCarte);

        prendreTresor = new Button("Prendre Tresor",80,80,Color.decode("#1d87ad"),Color.decode("#32afdb"));
        prendreTresor.setBorder(new RoundedBorder(20));
        prendreTresor.addActionListener(this);
        actionsPanel.add(prendreTresor);

        this.add(actionsPanel);

        vueMain = new VueMain(aventurier.getCartes(),this);
        this.add(vueMain);

        JPanel decks = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));


        defausseTresor = new Button("<html><body>Defausse <br>Carte Tresor</body></html>",90,100);
        defausseTresor.setBorder(new RoundedBorder(20));
        defausseTresor.addActionListener(this);
        decks.setBackground(Color.white);
        decks.add(defausseTresor);

        defausseInondation = new Button("<html><body>Defausse <br>Carte Inondation</body></html>",90,100);
        defausseInondation.setBorder(new RoundedBorder(20));
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
        assecher.setEnabled(false);
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

    //test
    public static void main(String[] args) {
        Aventurier aventurier = new Messager();
        aventurier.setNom("Clement");
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.prendreCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        VueAventurier v = new VueAventurier(null, aventurier, "flex");
        JFrame w = new JFrame();
        w.add(v);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.pack();
        w.setTitle("vueAventurier");
        w.setVisible(true);
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
            ihm.notifierObservateurs(Message.actionSpecial());
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
