package m2104.ile_interdite.vue;

import m2104.ile_interdite.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueInformation extends TitleFrame {

    private JLabel textLabel;
    private String message;
    private Button ok;
    private IHM ihm;

    public VueInformation(String message) {
        super("Information");
        this.message = message;

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        JPanel panelHaut = new JPanel();

        textLabel = new JLabel("<html><body><font color='blue'>"+ message +"</body></html>"); //penser a mettre un <br> dans le message pour un affichage propre
        textLabel.setFont(new Font("Roboto",Font.BOLD,20));
        textLabel.setBackground(Color.red);


        ok = new Button("ok",150, 35, Color.GREEN, new Color(120, 255, 120));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelHaut.add(textLabel);
        p.add(panelHaut, BorderLayout.NORTH);
        p.add(ok, BorderLayout.SOUTH);
        this.add(p);
        this.setSize(300, 150);
        this.setLocation(400, 0);
        this.setVisible(true);
        this.centrer();
        this.setMinimumSize(new Dimension(textLabel.getWidth(), 150));
        this.setPreferredSize(new Dimension(300, 150));
        this.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        Utils.afficherInformation("Vous avez recupere le tresor calice de l'oublie" );
    }
}
