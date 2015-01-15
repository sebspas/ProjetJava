package parking.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import parking.business.Timer;

/**
 * Created by Administrateur on 14/01/2015.
 */
public class VueTimer extends Vue{
    private JFrame fenetre;
    private JTextField textField1;
    private Timer timer;
    private JButton incremente;
    private JPanel panel;
    private JTextField textField2;

    public VueTimer(final Timer timer) {
        this.timer = timer;

        fenetre = new JFrame("Timer");
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(300,200));
        fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        incremente = new JButton("Ajoute 1 Heure");
        
        incremente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.setHeures(timer.getHeures() + 1);
            }
        });
        
        panel.add(textField1, BorderLayout.NORTH);
        panel.add(textField2, BorderLayout.CENTER);
        panel.add(incremente, BorderLayout.SOUTH);
        
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setVisible(true);
    }

    @Override
    public void mettreAJour() {
        textField1.setText("Jour : " + this.timer.getDay() + " jour(s)");
        textField2.setText("Heure : " + this.timer.getHeures() + " : " + this.timer.getMinutes() + " : "  + this.timer.getSecondes() +" sec");
    }

    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    }
}
