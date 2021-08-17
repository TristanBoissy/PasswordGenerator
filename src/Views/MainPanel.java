package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Generator.PasswordGenerator;

public class MainPanel extends JFrame implements Runnable {

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    PasswordGenerator newPassword;

    final private int SCREEN_SIZE_X = 400;
    final private int SCREEN_SIZE_Y = 200;
    final private int NUMBER_OF_PANELS = 2;
    final private String PROGRAM_TITLE = "Password Generator";

    /**
     * Runs the interface
     */
    public void run(){
        initFrame();
        initComponents();
        setVisible(true);
    }



    private void initFrame(){

        this.setTitle(PROGRAM_TITLE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setLayout(null);
        this.setSize(SCREEN_SIZE_X, SCREEN_SIZE_Y);
        this.setLocation(dim.width/2-this.getSize().width/2,
                dim.height/2-this.getSize().height/2);

        // ajoute une gestion du EXIT par confirmation pop-up
        this.addWindowListener(new WindowAdapter() {

            // gestionnaire d'événement pour la fermeture
            public void windowClosing(WindowEvent we) {

                // ajoute une demande de confirmation
                int result = JOptionPane.showConfirmDialog(null,
                        "Do you want to quit?",
                        "Exit confirmation: ",
                        JOptionPane.YES_NO_OPTION);

                // si la réponse est oui
                if (result == JOptionPane.YES_OPTION){
                    //ferme la fenêtre en activant la gestion de l'événement
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION){
                    // sinon, désactive la gestion de l'événement
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void initComponents(){
        initTopPanel();
        initBottomPanel();
    }

    private void initTopPanel(){
        Rectangle panelRectangle = new Rectangle(0,0,SCREEN_SIZE_X,
                SCREEN_SIZE_Y/NUMBER_OF_PANELS);
        JPanel topPanel = createNewJPanel(panelRectangle);

        Dimension genPassButtonDim = new Dimension(100,30);
        JButton genPassButton = createJButton(genPassButtonDim,"Generate");
        genPassButton.setBounds(topPanel.getWidth()/5 - (genPassButton.getWidth()/2),
                topPanel.getHeight()/2 - (genPassButton.getHeight()/2),
                genPassButton.getWidth(),genPassButton.getHeight());


        Dimension passwordTextFieldDim = new Dimension(200,30);
        JTextField passwordTextField = createJTextField(passwordTextFieldDim);
        passwordTextField.setEditable(false);
        passwordTextField.setForeground(Color.GRAY);
        passwordTextField.setBounds(topPanel.getWidth()/5*3 - (passwordTextField.getWidth()/2),
                topPanel.getHeight()/2 - (passwordTextField.getHeight()/2),
                passwordTextField.getWidth(),passwordTextField.getHeight());

        topPanel.add(genPassButton);
        topPanel.add(passwordTextField);

        genPassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newPassword = new PasswordGenerator();
                passwordTextField.setText(newPassword.getPassword());
            }
        });

        this.getContentPane().add(topPanel);
    }

    private void initBottomPanel(){
        Rectangle panelRectangle =
                new Rectangle(0,SCREEN_SIZE_Y/NUMBER_OF_PANELS,
                SCREEN_SIZE_X, SCREEN_SIZE_Y/NUMBER_OF_PANELS);
        JPanel bottomPanel = createNewJPanel(panelRectangle);


        Dimension copyToClipboardButtonDim = new Dimension(150,30);
        JButton copyToClipboardButton =
                createJButton(copyToClipboardButtonDim,"Copy to Clipboard");
        copyToClipboardButton.setBounds(bottomPanel.getWidth()/2 - (copyToClipboardButton.getWidth()/2),
                bottomPanel.getHeight()/3 - (copyToClipboardButton.getHeight()),
                copyToClipboardButton.getWidth(),copyToClipboardButton.getHeight());

        copyToClipboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(newPassword.getPassword()),null);
            }
        });

        bottomPanel.add(copyToClipboardButton);

        this.getContentPane().add(bottomPanel);
    }

    private JTextField createJTextField(Dimension dimension){
        JTextField newTextField = new JTextField();
        newTextField.setSize(dimension);
        newTextField.setHorizontalAlignment(JTextField.CENTER);
        return newTextField;
    }

    private JButton createJButton(Dimension dimension, String title){
        JButton newButton = new JButton();
        newButton.setText(title);
        newButton.setSize(dimension);
        newButton.setBackground(Color.GRAY);
        newButton.setFocusPainted(false);
        return newButton;
    }

    private JPanel createNewJPanel(Rectangle rectangle){
        JPanel newPanel = new JPanel();
        newPanel.setLayout(null);
        newPanel.setBackground(Color.DARK_GRAY);
        newPanel.setBounds(rectangle);
        return newPanel;
    }

}
