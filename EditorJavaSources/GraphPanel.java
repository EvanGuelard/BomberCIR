import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel
{
	private JSlider gravity;
	private JSlider jumpHeight;
	private JSlider vitesse;
	private JButton reset;
	private JButton save;
	private JButton browser;

	public GraphPanel()
	{
		gravity = new JSlider(800,1800,1200);
		jumpHeight = new JSlider(-920,-520,-720);
		vitesse = new JSlider(100,700,300);

		JPanel sl1 = new JPanel();
		JPanel sl2 = new JPanel();
		JPanel sl3 = new JPanel();

		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		sl1.setLayout(new BorderLayout());
		sl2.setLayout(new BorderLayout());
		sl3.setLayout(new BorderLayout());

		sl1.add(new JLabel("Gravité:"), BorderLayout.WEST);
		sl1.add(gravity, BorderLayout.EAST);
		sl2.add(new JLabel("Hauteur de saut:"), BorderLayout.WEST);
		sl2.add(jumpHeight, BorderLayout.EAST);
		sl3.add(new JLabel("Vitesse:"), BorderLayout.WEST);
		sl3.add(vitesse, BorderLayout.EAST);

		JPanel ps = new JPanel();
		ps.setLayout(new BoxLayout(ps, BoxLayout.PAGE_AXIS));
		ps.add(sl1);
		ps.add(sl2);
		ps.add(sl3);
		add(ps, BorderLayout.NORTH);


		JPanel pb = new JPanel();
		pb.setLayout(new BorderLayout(0,5));
		reset = new JButton("Restaurer les valeurs par défaut");
		save = new JButton("Enregistrer les modifications");
		browser = new JButton("Tester le jeu dans le navigateur");
		pb.add(reset,BorderLayout.NORTH);
		pb.add(save,BorderLayout.CENTER);
		pb.add(browser,BorderLayout.SOUTH);
		add(pb, BorderLayout.SOUTH);
	}

	public JSlider getGravity() {
		return gravity;
	}

	public JSlider getJumpHeight() {
		return jumpHeight;
	}

	public JSlider getVitesse() {
		return vitesse;
	}

	public JButton getReset() {
		return reset;
	}

	public JButton getSave() {
		return save;
	}

	public JButton getBrowser() {
		return browser;
	}
}