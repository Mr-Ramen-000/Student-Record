package Assignment;

import java.awt.*;

public class ListOfStudentPanel extends Panel{
    static List listOfStudentsName = new List();
   
    public ListOfStudentPanel() {
        Color fontColor = new Color(41, 50, 80);
        
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        setPreferredSize(new Dimension(545,700));
        setBackground(new Color(247, 207, 89));
        
        Panel listpPanel = new Panel();
        listpPanel.setPreferredSize(new Dimension(380, 400));
        listpPanel.setLayout(new BorderLayout());
        
        listpPanel.add(listOfStudentsName, BorderLayout.CENTER);
        
        Label listStudentLabel = new Label("List of Students");
        listStudentLabel.setFont(new Font("Garamond", Font.BOLD, 25));
        listStudentLabel.setForeground(fontColor);
        add(listStudentLabel);
        add(listpPanel);
    }
}
