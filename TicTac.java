//Nick Soetaert
//10-30-2017

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTac
{  
  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable(){
      public void run()
      {
        TicTacFrame aFrame = new TicTacFrame();
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.setVisible(true);
      }
    });
  }
}

class TicTacFrame extends JFrame
{
  public static final int DEFAULT_WIDTH = 700;
  public static final int DEFAULT_HEIGHT = 850;
  
  public TicTacFrame()
  {
    this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    this.setTitle("Tic Tac Toe by Nick Soetaert");
    TicTacPanel panel = new TicTacPanel();
    this.add(panel);
  }
}

class TicTacPanel extends JPanel
{
  int numColumns;
  int numRows;
  
  Font mySmallFont = (new Font("Bookman Old Style", Font.BOLD, 20));
  Font myLargeFont = (new Font("Bookman Old Style", Font.BOLD, 45));
  
  JPanel northPanel = new JPanel(); //FlowLayout
  JPanel centerPanel = new JPanel(); //GridLayout
  JPanel southPanel = new JPanel(); //FlowLayout
  
  JLabel nameLabel = new JLabel("tic-tac-toe by Nick Soetaert");
  JButton[] buttonArray; //make an array of buttons (game tiles)
  JButton clearButton = new JButton("reset");
  
  boolean isXTurn = true; //if true, place an X. If false, place an O.
  
  public TicTacPanel()
  {
    try
    {
    numColumns = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter desired number of columns: "));
    numRows = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter desired number of rows: "));
    if((numColumns < 1) || (numRows < 1))
    {
      System.out.println("Positive integers only please.");
      System.exit(1);
    }
    }
    catch(NumberFormatException e)
    {
      System.out.println("Positive integers only please.");
      System.exit(1);
    }
    
    //General~~~~~~~~~~~~~~~~~~~~~    
    this.setLayout(new BorderLayout());
    this.add(northPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(southPanel, BorderLayout.SOUTH);
    
    //North Panel~~~~~~~~~~~~~~~~~
    nameLabel.setFont(mySmallFont);
    northPanel.add(nameLabel);
    
    //Center Panel~~~~~~~~~~~~~~~~
    centerPanel.setBorder(BorderFactory.createRaisedBevelBorder()); //make a fancy border
    centerPanel.setLayout(new GridLayout(numRows,numColumns));
    buttonArray = new JButton[(numColumns*numRows)];
    for(int i = 0; i < (numColumns*numRows); i++) //create game buttons
    {
      buttonArray[i] = new JButton(" "); 
      buttonArray[i].setFont(myLargeFont);
      
      centerPanel.add(buttonArray[i]); //add buttons to center panel display
      buttonArray[i].addActionListener(new tileActionListener()); //associate each button w/ the action listener
    }
    
    //South Panel~~~~~~~~~~~~~~~~
    clearButton.setFont(myLargeFont);
    clearButton.addActionListener(new tileActionListener());
    southPanel.add(clearButton);
    
  }
  
  class tileActionListener implements ActionListener
  {

   public void actionPerformed(ActionEvent event)
   {
     JButton clickedButton = (JButton) event.getSource(); //find out what button was clicked

     if(clickedButton == clearButton)
     {
       for(int i = 0; i < (numColumns*numRows); i++)
       {
         buttonArray[i].setText(" ");
         isXTurn = true;
       }
     }
     else if((isXTurn) && (clickedButton.getText() == " "))
     {
       clickedButton.setText("X");
       clickedButton.setForeground(Color.RED);
       isXTurn = false;
     }
     else if((!isXTurn) && (clickedButton.getText() == " "))
     {
       clickedButton.setText("O");
       clickedButton.setForeground(Color.BLUE);
       isXTurn = true;
     }
     else
     {
       System.out.println("May only click on blank squares!");
     }
   }
  }
}
