package parking.gui.ajouter;

/***************************************************************/
/*						Import						   		   */
/***************************************************************/
import parking.business.Client;
import parking.business.facture.Fabrique.FabriqueCalculerTarif;
import parking.business.facture.Fabrique.IFabriqueCalculerTarif;
import parking.exception.business.MethodeNonGeree;
import parking.gui.Vue;

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
    /**
     *
     */
    private JFrame fenetre = new JFrame("Nouveau Client");

    /**
     *
     */
    private JPanel top, topClientLeft, topClientRight,
            center,
            bottom,
            main;

    /**
     *
     */
    private JLabel labelNom, labelPrenom,
                   labelAdresse;

    /**
     *
     */
    private JTextField Nom, Prenom,
                       Adresse;

    private JComboBox typeCalculTarif;
    /**
     * Le bouton permettant de valider.
     */
    private final JButton Valider = new JButton();

    /**
     * Le bouton permettant d'annuler.
     */
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
    } // Constructeur


    /***************************************************************/
	/*						Methodes							   */
    /***************************************************************/

    /**
     * Methode Top() permet de creer un panneau afin de ne
     * s'occuper que de la partie superieure de la fenetre.
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

        typeCalculTarif = new JComboBox();
        typeCalculTarif.addItem("CalculerTarifHeure");
        typeCalculTarif.addItem("CalculerTarifPointsFidelite");
        typeCalculTarif.addItem("CalculerTarifPreferentiel");
        typeCalculTarif.setPreferredSize(new Dimension(300,20));
        
        top.add(topClientLeft, BorderLayout.WEST);
        top.add(topClientRight, BorderLayout.EAST);
        top.add(typeCalculTarif, BorderLayout.SOUTH);

        return top;
    } // Top()


    /**
     * Methode Center() permet de creer un panneau afin de ne
     * s'occuper que de la partie centrale de la fenetre.
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
        
        center.add(labelAdresse, BorderLayout.CENTER);
        center.add(Adresse, BorderLayout.SOUTH);
        
        
        return center;
    } // Center()


    /**
     * Methode Bottom() permet de creer un panneau afin de ne
     * s'occuper que de la partie inferieure de la fenetre.
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
                    IFabriqueCalculerTarif fabriqueCalculerTarif = new FabriqueCalculerTarif();
                    try {
                        new Client(
                                Nom.getText(),
                                Prenom.getText(),
                                Adresse.getText(),
                                fabriqueCalculerTarif.creer(typeCalculTarif.getSelectedItem().toString())
                        );
                    } catch (MethodeNonGeree methodeNonGeree) {
                        methodeNonGeree.printStackTrace();
                    }

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

    /**
     * Methode validateData() permet de
     *
     * @return Booleen indiquant si les donnees sont validees (true) ou non (false).
     */
    public boolean validateData() {
        if (Nom.getText().isEmpty() && Prenom.getText().isEmpty() && Adresse.getText().isEmpty())
            return false;
        if (!Nom.getText().matches("[a-zA-Z-_]{2,35}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Nom incorect !",
                    "Erreur nom",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Prenom.getText().matches("[a-zA-Z-_]{2,35}")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Prenom incorect !",
                    "Erreur prenom",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!Adresse.getText().matches("[a-zA-Z-_.,\\s]*")) {
            JOptionPane.showMessageDialog(fenetre,
                    "Adresse incorecte !",
                    "Erreur adresse",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
            
        return true;
    } // validateData()



    /**
     * Methode mettreAJour() permet de mettre a jour la vue.
     */
    @Override
    public void mettreAJour() {

    } // mettreAJour()

    @Override
    public void setVisible(boolean visible) {
        fenetre.setVisible(visible);
    }

} // VueNouveauClient
