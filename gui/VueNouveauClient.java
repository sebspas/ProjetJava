package parking.gui;

import parking.business.Client;
import parking.business.facture.CalculerTarifHeure;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class VueNouveauClient, qui herite de la classe Vue,
 * qui cree une vue permettant de creer un nouveau client.
 *
 * @author Chergui, Coadalen, Corfa, Corral
 */
public class VueNouveauClient extends Vue {
    /***************************************************************/
	/*						Debut Donnees Membres 				   */
    /***************************************************************/
    private JFrame fenetre = new JFrame("Nouveau Client");

    private JPanel top, topClientLeft, topClientRight,
            center,
            bottom,
            main;

    private JLabel labelNom, labelPrenom,
                   labelAdresse;

    private JTextField Nom, Prenom,
                       Adresse;

    private final JButton Valider = new JButton();
    private final JButton Annuler = new JButton();


    /***************************************************************/
	/*						Constructeur						   */
    /***************************************************************/
    /**
     * Constructeur de la classe VueAjouterVehicule(), permettant de
     */
    public VueNouveauClient() {
        fenetre.setLocation(0, 0);
        fenetre.setPreferredSize(new Dimension(320,200));
        fenetre.setDefaultCloseOperation(fenetre.DISPOSE_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        main = new JPanel();
        main.add(Top(), BorderLayout.NORTH);
        main.add(Center(), BorderLayout.CENTER);
        main.add(Bottom(), BorderLayout.SOUTH);

        fenetre.setContentPane(main);
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    } // VueNouveauClient()


    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/

    /**
     * Methode Top() permet de ...
     *
     * @return Le panneau en haut de la fenetre.
     */
    private JPanel Top() {

        // Top
        top = new JPanel();
        top.setLayout(new BorderLayout());

        labelNom = new JLabel("Nom");
        Nom = new JTextField();
        Nom.setPreferredSize(new Dimension(150, 20));


        topClientLeft = new JPanel();
        topClientLeft.setLayout(new BorderLayout());
        topClientLeft.add(labelNom, BorderLayout.NORTH);
        topClientLeft.add(Nom, BorderLayout.CENTER);


        labelPrenom = new JLabel("Prénom");
        Prenom = new JTextField();
        Prenom.setPreferredSize(new Dimension(150, 20));


        topClientRight = new JPanel();
        topClientRight.setLayout(new BorderLayout());
        topClientRight.add(labelPrenom, BorderLayout.NORTH);
        topClientRight.add(Prenom, BorderLayout.CENTER);


        top.add(topClientLeft, BorderLayout.WEST);
        top.add(topClientRight, BorderLayout.EAST);

        return top;
    } // Top()


    /**
     * Methode Center() permet de ...
     *
     * @return Le panneau au centre de la fenetre.
     */
    private JPanel Center() {

        // Top
        center = new JPanel();
        center.setLayout(new BorderLayout());

        labelAdresse = new JLabel("Adresse");
        Adresse = new JTextField();
        Adresse.setPreferredSize(new Dimension(300, 50));


        center.add(labelAdresse, BorderLayout.NORTH);
        center.add(Adresse, BorderLayout.SOUTH);

        return center;
    } // Center()


    /**
     * Methode Bottom() permet de
     *
     * @return Le panneau en bas de la fenetre.
     */
    private JPanel Bottom() {
        // Bottom
        bottom = new JPanel();
        bottom.setLayout(new BorderLayout());


        Valider.setText("Valider");
        Valider.setPreferredSize(new Dimension(140, 40));
        Valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateData()) {

                    new Client(
                            Nom.getText(),
                            Prenom.getText(),
                            Adresse.getText(),
                            new CalculerTarifHeure()
                    );

                    JOptionPane.showMessageDialog(fenetre,
                            "Client " + Nom.getText() + " " + Prenom.getText() + " ajouté avec succès !",
                            "Success",
                            JOptionPane.PLAIN_MESSAGE);
                    fenetre.dispose();
                } else {
                    JOptionPane.showMessageDialog(fenetre,
                            "Vous devez remplir tous les champs !",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        Annuler.setText("Annuler");
        Annuler.setPreferredSize(new Dimension(140,40));
        Annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == Annuler) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(fenetre, "Voulez-vous vraiment annuler ?"))
                        fenetre.dispose();
                }

            }
        });

        bottom.add(Valider, BorderLayout.WEST);
        bottom.add(Annuler, BorderLayout.EAST);

        return bottom;
    } // Bottom()


    public boolean validateData() {
        if (!Nom.getText().isEmpty() && !Prenom.getText().isEmpty() && !Adresse.getText().isEmpty())
            return true;

        return false;
    } // validateData()



    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {

    } // mettreAJour()

} // VueNouveauClient
