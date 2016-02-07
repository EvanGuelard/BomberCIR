import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Fenetre extends JFrame
{
	private CodePanel codePanel;
	private GraphPanel graphPanel;

	public Fenetre()
	{
		super("BomberCIR - Editor");
		setSize(1000, 500);
		setMinimumSize(new Dimension(480, 350));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createInterface();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void createInterface()
	{
		graphPanel = new GraphPanel();
		codePanel = new CodePanel();

		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(2,10,10,10));
		this.getContentPane().setLayout(new BorderLayout(10,0));

		JTabbedPane jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jTabbedPane.add("variables.js", codePanel);

		add(graphPanel, BorderLayout.WEST);
		add(jTabbedPane, BorderLayout.CENTER);

		add(new JLabel(new ImageIcon("img/bomberCIREditor.png")), BorderLayout.NORTH);

		InterComListener icl = new InterComListener(graphPanel,codePanel);
	}

	public static void setSystemLookAndFeel()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (InstantiationException e) {}
		catch (ClassNotFoundException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		catch (IllegalAccessException e) {}
	}
}