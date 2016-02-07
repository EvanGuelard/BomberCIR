import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CodePanel extends JScrollPane
{
	private JTextArea internalCodePanel;
	private String color;

	public CodePanel()
	{
		super(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		internalCodePanel = new JTextArea();
		setViewportView(internalCodePanel);

		Font font;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("font/NotCourierSans.otf"));
			internalCodePanel.setFont(font.deriveFont(14.0f));
		}
		catch(FontFormatException e)
		{}
		catch(IOException e)
		{}

		generateCode(1200, -720, 300, "#000000");
	}

	public JTextArea getInternalCodePanel() {
		return internalCodePanel;
	}

	public void generateCode(int gra,int jump,int vit, String bgColor)
	{
		if(bgColor!=null){
			color=bgColor;
		}
		internalCodePanel.setText(
				"//intensité de la gravité" + "\n" +
				"var gravity = " + gra + ";\n\n" +
				"//hauteur de saut (plus la valeur est négative, plus le saut est haut)" + "\n" +
				"var heightJump = " + jump + ";\n\n" +
				"//vitesse de déplacement du personnage" + "\n" +
				"var speed = " + vit +  ";" + "\n\n" +
				"//couleur de fond" + "\n" +
				"var bgColor = \"" + color + "\";"
		);
	}

	public int[] getVarValues()
	{
		int tabVar[] = {0,0,0};

		String code[] = internalCodePanel.getText().split("\n");
		for(int i=0;i<code.length;i++)
		{
			String codeLine = code[i];
			try{
				codeLine = codeLine.substring(0,codeLine.indexOf("//"));
			}
			catch (IndexOutOfBoundsException e){}

			if(!codeLine.isEmpty())
			{
				if(codeLine.contains("var gravity = "))	{
					codeLine = codeLine.substring("var gravity = ".length(), codeLine.indexOf(";"));
					tabVar[0] = Integer.parseInt(codeLine.trim());
				}
				else if(codeLine.contains("var heightJump = ")) {
					codeLine = codeLine.substring("var heightJump = ".length(), codeLine.indexOf(";"));
					tabVar[1] = Integer.parseInt(codeLine.trim());
				}
				else if(codeLine.contains("var speed = ")) {
					codeLine = codeLine.substring("var speed = ".length(), codeLine.indexOf(";"));
					tabVar[2] = Integer.parseInt(codeLine.trim());
				}
				else if(codeLine.contains("var bgColor = \"")) {
					codeLine = codeLine.substring("var bgColor = \"".length(), codeLine.indexOf("\";"));
					color = codeLine;
				}
			}
		}

		return tabVar;
	}
}