package bottom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bottom {
    //En metod som returnerar en 2d array, börjar med en array som innehåller alla nummer i spelet där 0-positionen är den tomma. 
    //Metoden blandar alla nummer och sedan tas ett nummer ut och placeras på en position. 
    private Integer[][] random() {
        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        List<Integer> listOfNumbers = Arrays.asList(numbers);
        Collections.shuffle(listOfNumbers);
        Integer[][] shuffled = new Integer[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                shuffled[i][j] = listOfNumbers.get(i * 4 + j);
            }
        }
        
        return shuffled;
    }
    // Konstruktor startar grunden för GUI:t 
    public Bottom() {
        JFrame f = new JFrame("15-spel");
        f.setBackground(Color.WHITE);
        createGame(random(), f);
        f.setSize(800, 800);
        f.setResizable(false);
        f.setVisible(true);
    }
    
    //Metoden skapar panelen för knappen "New Game" samt dess egenskaper.
    private void createGame(Integer[][] numbers, JFrame f) {
        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setSize(700, 700);
        JButton newGame = new JButton("New Game");
        newGame.setSize(100, 100);
        newGame.setVerticalAlignment(JButton.BOTTOM);
        JPanel p1 = new JPanel(new GridLayout(4, 4));
        p1.setSize(600, 600);
        p1.setBackground(Color.WHITE);
        
        // Här skapar vi spelplanen som består av 4x4 knappar
        JButton[][] buttons = new JButton[4][4];
        // Här skapar vi en generisk lyssnare som sedan placeras på alla knappar i loopen
        ActionListener buttonListener = new ButtonListener(buttons);
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                int number = numbers[i][j];
                JButton btn;
                
                // 0 är den tomma, dvs vit
                if (number == 0) {
                    btn = new JButton(" ");
                    btn.setBackground(Color.white);
                } else {
                    btn = new JButton(Integer.toString(number));
                    btn.setBackground(Color.red);
                }
                btn.setName(Integer.toString(number));
                btn.addActionListener(buttonListener);
                p1.add(btn);
                buttons[i][j] = btn;
            }
        }
        
        bottom.add(p1);
        bottom.add(newGame);
        f.add(bottom);
        
        // Lyssnare för om man klickar på nytt spel
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.getContentPane().removeAll();
                createGame(random(), f);
                f.getContentPane().validate();
                f.getContentPane().repaint();
            }
        });
    }
    
    public static void main(String[] args) {
        Bottom test = new Bottom();
    }
}
