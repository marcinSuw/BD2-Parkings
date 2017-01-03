import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by szwarc on 02.01.17.
 */
public class DataBaseView {

    public DataBaseView(){
        prepareGUI();
    }

    private JFrame mainFrame;

    private void prepareGUI(){
        mainFrame = new JFrame();

        mainFrame.setSize(400,400);
        mainFrame.setTitle("Demo");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);

        //mainFrame.setLayout(new GridLayout(3, 1));

    }



}
