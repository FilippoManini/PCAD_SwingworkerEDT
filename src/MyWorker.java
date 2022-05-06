import javax.swing.*;
import java.util.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ExecutionException;
//https://docs.oracle.com/javase/7/docs/api/javax/swing/SwingWorker.html
public class MyWorker extends SwingWorker<String, Integer> {

	private GUI gui;
	
    public MyWorker(GUI gui) {
        this.gui = gui;
    }


    //quando faccio execute() lancia questo
    //NON FARE MODIFICHE perche senno va in conflitto con EDT = gestione degli eventi coi thread
    @Override
    protected String doInBackground() throws Exception {
        Thread.sleep(1000); 
        return "Done!";
        
    }
    
    @Override
    protected void process(List<Integer> chunks) {
    }

    //modifico widget nell'interfaccia grafica
    //viene eseguito nell'EDT quindi qua è permesso 
    //viene eseguita dopo doInBackground()
    @Override
    protected void done() {
    	gui.counter++;
    	gui.statusLabel.setText(Integer.toString(gui.counter));
    	gui.step.setEnabled(true);
    }

}
