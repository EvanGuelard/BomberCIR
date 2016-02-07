import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class InterComListener implements ChangeListener,DocumentListener,ActionListener
{
	private GraphPanel gp;
	private CodePanel cp;
	private boolean listeningSlider;
	private boolean listeningDocument;

	public InterComListener(GraphPanel graphPanel,CodePanel codePanel)
	{
		gp = graphPanel;
		cp = codePanel;
		listeningSlider = true;
		listeningDocument = true;

		gp.getGravity().addChangeListener(this);
		gp.getJumpHeight().addChangeListener(this);
		gp.getVitesse().addChangeListener(this);
		gp.getReset().addActionListener(this);
		gp.getSave().addActionListener(this);
		gp.getBrowser().addActionListener(this);
		cp.getInternalCodePanel().getDocument().addDocumentListener(this);
	}

	public void updateSlider()
	{
		int tab[] = cp.getVarValues();
		if(tab[0] <= gp.getGravity().getMaximum() && tab[0] >= gp.getGravity().getMinimum()){
			gp.getGravity().setValue(tab[0]);
		}
		if(tab[1] <= gp.getJumpHeight().getMaximum() && tab[1] >= gp.getJumpHeight().getMinimum()){
			gp.getJumpHeight().setValue(tab[1]);
		}
		if(tab[2] <= gp.getVitesse().getMaximum() && tab[2] >= gp.getVitesse().getMinimum()){
			gp.getVitesse().setValue(tab[2]);
		}
	}

	public void stateChanged(ChangeEvent e) {
		if(listeningSlider)
		{
			listeningDocument = false;
			cp.generateCode(gp.getGravity().getValue(),
					gp.getJumpHeight().getValue(),
					gp.getVitesse().getValue(),
					null
			);
		}
		listeningDocument = true;
	}

	public void insertUpdate(DocumentEvent e) {
		if(listeningDocument)
		{
			listeningSlider = false;
			updateSlider();
		}
		listeningSlider = true;
	}

	public void removeUpdate(DocumentEvent e) {
		if(listeningDocument)
		{
			listeningSlider = false;
			updateSlider();
		}
		listeningSlider = true;
	}

	public void changedUpdate(DocumentEvent e) {
		if(listeningDocument)
		{
			listeningSlider = false;
			System.out.println("changed");
			updateSlider();
		}
		listeningSlider = true;
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==gp.getReset())
		{
			cp.generateCode(1200, -720, 300,"#000000");
		}
		else if(e.getSource()==gp.getSave())
		{
			File file = new File("assets/variables.js");
			FileWriter fw;

			try {
				fw = new FileWriter(file);
				cp.generateCode(gp.getGravity().getValue(),
						gp.getJumpHeight().getValue(),
						gp.getVitesse().getValue(),
						null
				);
				fw.write(cp.getInternalCodePanel().getText());
				fw.close();
				JOptionPane msg = new JOptionPane();
				msg.showMessageDialog(null,
						"Enregistrement terminé",
						"Enregistrement terminé", JOptionPane.INFORMATION_MESSAGE
				);
			}
			catch (IOException ex)
			{
				JOptionPane msg = new JOptionPane();
				msg.showMessageDialog(null,
						"Une erreur est survenu lors de l'enregistrement.\n\nDétails:\n" + ex.getMessage(),
						"Erreur d'enregistrement", JOptionPane.ERROR_MESSAGE
				);
			}
		}
		else if (e.getSource()==gp.getBrowser())
		{
			if(Desktop.isDesktopSupported())
			{
				try {
					Desktop.getDesktop().browse(new URI("index.html"));
				}
				catch (java.net.URISyntaxException ex)
				{
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(null,
							"Une erreur est survenue lors de l'ouverture.\n\nDétails:\n" + ex.getMessage(),
							"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE
					);
				}
				catch (IOException ex)
				{
					JOptionPane msg = new JOptionPane();
					msg.showMessageDialog(null,
							"Une erreur est survenue lors de l'ouverture.\n\nDétails:\n" + ex.getMessage(),
							"Erreur d'ouverture", JOptionPane.ERROR_MESSAGE
					);
				}
			}
			else
			{
				JOptionPane msg = new JOptionPane();
				msg.showMessageDialog(null,
						"Désolé, votre ordinateur ne supporte pas cette fonctionnalité.",
						"Erreur", JOptionPane.INFORMATION_MESSAGE
				);
			}
		}
	}
}