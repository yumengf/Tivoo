import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import model.Model;



public class Main {
	
	public static void main(String[] args) throws IOException {
        JFileChooser fc = new JFileChooser();
        
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            Model foo = new Model(file.toURI().toString());
            
        } 
	}
}