
package bottom;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

// lyssnare för alla knappar
public class ButtonListener implements ActionListener {
    // spelplanen
    private JButton[][] buttons;
    
    // konstruktor som tar in spelplanen
    public ButtonListener(JButton[][] buttons) {
        this.buttons = buttons;
    }
    //Metod som kollar om knappen man trycker på ligger bredvid det tomma utrymmet 
    //eller inte och agerar därefter samt när spelet ligger i ordning så anger den att man har vunnit.
    @Override
    public void actionPerformed(ActionEvent e) {
        String btnName = ((JButton) e.getSource()).getName();
        if (!("0".equals(btnName))) {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    // Letar upp rätt knapp
                    if (buttons[i][j].getName().equals(btnName)) {
                        JButton temp = null;
                        
                        // kollar så att vi inte får minus index och om att j-1 är en tom ruta 
                        if (j - 1 >= 0 && buttons[i][j - 1].getName().equals("0")) {
                            temp = buttons[i][j - 1];
                        
                        // kollar så vi inte får index större än arrayens längd och om j+1 är en tom ruta
                        } else if (j + 1 < buttons[i].length && buttons[i][j + 1].getName().equals("0")) {
                            temp = buttons[i][j + 1];
                        
                        // kollar så att i-1 inte är minus index och om i - 1 är tom
                        } else if (i - 1 >= 0 && buttons[i - 1][j].getName().equals("0")) {
                            temp = buttons[i - 1][j];
                        
                        // kollar så att vi inte får större index än arrayens längd och om i+1 är tom
                        } else if (i + 1 < buttons.length && buttons[i + 1][j].getName().equals("0")) {
                            temp = buttons[i + 1][j];
                        }

                        // Här anropas den faktiska flytten
                        if (temp != null) {
                            swap(temp, i, j);
                        }
                        
                        // Om man har vunnit så skriver vi ut "you win!"
                        if (isCompleted()) {
                            JOptionPane.showMessageDialog(null, "Grattis, du vann!");
                        }
                        return; 
                    }
                }
            }
        }
    }
    
    // byter plats
    private void swap(JButton temp, int i, int j) {
        temp.setText(buttons[i][j].getText());
        temp.setName(buttons[i][j].getName());
        temp.setBackground(Color.red);
        buttons[i][j].setText(" ");
        buttons[i][j].setName("0");
        buttons[i][j].setBackground(Color.white);
    }
    
    // kollar om personen har vunnit
    private boolean isCompleted(){
        // om sista rutan inte är tom så kan inte spelet vara avslutat
        if (!("0".equals(buttons[buttons.length - 1][buttons[buttons.length - 1].length - 1].getName()))) {
            return false;
        } else {
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length - 1; j++) {
                    // kollar så att allt är i ordning
                    if(Integer.parseInt(buttons[i][j].getName()) > Integer.parseInt(buttons[i][j + 1].getName())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
