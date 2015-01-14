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
    JFrame fenetre;
    private JTextField textField1;
    private Timer timer;
    private JButton incremente;
    private JPanel panel;

    public VueTimer(final Timer timer) {
        this.timer = timer;

        fenetre = new JFrame("Timer");
        fenetre.setResizable(false);
        fenetre.setPreferredSize(new Dimension(350,100));
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

        panel.add(textField1, BorderLayout.CENTER);
        panel.add(incremente, BorderLayout.SOUTH);
        
        fenetre.setContentPane(panel);
        fenetre.pack();
        fenetre.setVisible(true);
    }

    @Override
    public void mettreAJour() {
        textField1.setText(this.timer.getTimer());
    }
}
