import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class javaButon extends JFrame {
    private JButton[][] buttons;
    private JButton activeButton;
    private String graphqlSchema;
    private Color activeColor;
    private Color passiveColor;
    private Icon activeIcon;
    private Icon passiveIcon;

    public javaButon() {
        // Initialize frame
        super("Button Control Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new GridLayout(4, 4));

        // Initialize button properties
        graphqlSchema = "X";
        activeColor = Color.BLUE;
        passiveColor = Color.RED;
        activeIcon = new ImageIcon("active_icon.png"); // Replace with your active icon path
        passiveIcon = new ImageIcon("passive_icon.png"); // Replace with your passive icon path

        // Initialize buttons
        buttons = new JButton[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = createButton(i, j);
                add(buttons[i][j]);
            }
        }

        // Set initial active button
        activeButton = buttons[0][0];
        updateButtonStatus(activeButton);

        // Display the frame
        setVisible(true);
    }

    private JButton createButton(final int row, final int col) {
        JButton button = new JButton();
        button.setBackground(passiveColor);
        button.setIcon(passiveIcon);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (activeButton != null) {
                    updateButtonStatus(activeButton);
                }

                activeButton = (JButton) e.getSource();
                updateButtonStatus(activeButton);
            }
        });

        return button;
    }

    private void updateButtonStatus(JButton button) {
        button.setBackground(activeColor);
        button.setIcon(activeIcon);
        button.setText(graphqlSchema);

        // Reset other buttons to passive state
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (buttons[i][j] != button) {
                    buttons[i][j].setBackground(passiveColor);
                    buttons[i][j].setIcon(passiveIcon);
                    buttons[i][j].setText("");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new javaButon();
            }
        });
    }
}

