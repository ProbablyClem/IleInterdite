package m2104.ile_interdite.vue;

import m2104.ile_interdite.modele.Aventurier;
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
    private Aventurier aventurier;
    private JLabel nomAventurier;
    private JLabel nbActionsLabel;
    private JLabel textField;
    private JButton seDeplacer;
    private JButton assecher;
    private JButton finirTour;
    private JButton actionSpecial;
    private JButton donnerCarte;
    private JButton prendreTresor;
    private JButton cartesTresor;
    private JButton deffauseeTresor;
    private JButton deffauseInondation;
    private JButton cartesInondation;

    public VueAventurier(IHM ihm, Aventurier aventurier, String capaciteSpecial){
        this.aventurier = aventurier;
        this.ihm = ihm;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel header = new JPanel(new BorderLayout());

        nomAventurier = new JLabel(aventurier.getNom() + " : " + aventurier.getRole());
        nomAventurier.setFont(new Font(nomAventurier.getFont().getFontName(), Font.PLAIN, 30));
        header.add(nomAventurier, BorderLayout.CENTER);

        nbActionsLabel = new JLabel("Actions restantes : " + aventurier.getActions() + " /3");
        nbActionsLabel.setFont(new Font(nbActionsLabel.getFont().getFontName(), Font.PLAIN, 27));
        header.add(nbActionsLabel, BorderLayout.EAST);

        this.add(header);

        textField = new JLabel("Choisir une action");
        this.add(textField);

        JPanel actionsPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        seDeplacer = new JButton("Se Deplacer");
        seDeplacer.setBorder(new RoundedBorder(20));
        seDeplacer.addActionListener(this);
        actionsPanel.add(seDeplacer);

        assecher = new JButton("Assecher");
        assecher.setBorder(new RoundedBorder(20));
        assecher.addActionListener(this);
        actionsPanel.add(assecher);

        finirTour = new JButton("Finir Tour");
        finirTour.setBorder(new RoundedBorder(20));
        finirTour.addActionListener(this);
        actionsPanel.add(finirTour);

        actionSpecial = new JButton(capaciteSpecial);
        actionSpecial.setBorder(new RoundedBorder(20));
        actionSpecial.addActionListener(this);
        actionsPanel.add(actionSpecial);

        donnerCarte = new JButton("Donner Carte");
        donnerCarte.setBorder(new RoundedBorder(20));
        donnerCarte.addActionListener(this);
        actionsPanel.add(donnerCarte);

        prendreTresor = new JButton("Prendre Tresor");
        prendreTresor.setBorder(new RoundedBorder(20));
        prendreTresor.addActionListener(this);
        actionsPanel.add(prendreTresor);

        this.add(actionsPanel);

        VueMain main = new VueMain(aventurier.getCartes());
        this.add(main);

        JPanel decks = new JPanel(new GridLayout(0, 4, 20, 20));
        cartesTresor = new JButton("Carte Tresor");
        cartesTresor.setBorder(new RoundedBorder(20));
        cartesTresor.addActionListener(this);
        decks.add(cartesTresor);

        deffauseeTresor = new JButton("Deffausse Carte Tresor");
        deffauseeTresor.setBorder(new RoundedBorder(20));
        deffauseeTresor.addActionListener(this);
        decks.add(deffauseeTresor);

        deffauseInondation = new JButton("Deffausse Carte Innondation");
        deffauseInondation.setBorder(new RoundedBorder(20));
        deffauseInondation.addActionListener(this);
        decks.add(deffauseInondation);

        cartesInondation = new JButton("Carte Inondation");
        cartesInondation.setBorder(new RoundedBorder(20));
        cartesInondation.addActionListener(this);
        decks.add(cartesInondation);

        this.add(decks);

        this.setSize(400, 800);
    }

    public void setMessage(String message){
        this.textField.setText(message);
        this.repaint();
    }

    //test
    public static void main(String[] args) {
        Aventurier aventurier = new Messager();
        aventurier.setNom("Clement");
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
        aventurier.donnerCarte(new CarteTresor("src/images/ocean.jpg", ".", Utils.Tresor.CALICE));
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
        if (e.getSource() == cartesTresor){
            Message m = Message.voirDeck(Utils.Deck.DECK_TRESOR);
            ihm.notifierObservateurs(m);
        }
        else if (e.getSource() == deffauseeTresor){
            Message m = Message.voirDeck(Utils.Deck.DEFFAUSSE_TRESOR);
            ihm.notifierObservateurs(m);
        }
        else if (e.getSource() == deffauseInondation){
            Message m = Message.voirDeck(Utils.Deck.DEFFAUSSE_INONDATION);
            ihm.notifierObservateurs(m);
        }
        else if (e.getSource() == cartesInondation){
            Message m = Message.voirDeck(Utils.Deck.DECK_INONDATION);
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

    public void updateActions(){
        nbActionsLabel.setText("Actions restantes : " + aventurier.getActions() + " /3");
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
