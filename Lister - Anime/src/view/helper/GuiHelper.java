package view.helper;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class GuiHelper
{

    public static JPanel createPanelAligned(JComponent... components)
    {
        JPanel returnPanel = new JPanel(new FlowLayout());
        for(JComponent component : components)
        {
            returnPanel.add(component);
        }
        return returnPanel;
    }
    
    public static JPanel createPanelVertical(int anchor, JComponent... components)
    {
        JPanel returnPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.anchor = anchor;
        gridBag.insets.set(3, 0, 0, 0);
        for(int i = 0; i < components.length; i++)
        {
            gridBag.gridy = i;
            returnPanel.add(components[i], gridBag);
        }
        return returnPanel;
    }

    public static String makeInternetParam(String name)
    {
        String returnString;
        returnString = name.replace("`", "'");
        returnString = returnString.replace("%", "%25");
        returnString = returnString.replace("+", "%2B");
        returnString = returnString.replace("&", "%26");
        returnString = returnString.replace("/", "%2F");
        returnString = returnString.replace(" ", "+");
        return returnString;
    }
    
    public static void openBrower(String link)
    {
        try
        {
            java.awt.Desktop.getDesktop().browse(new URI(link));
        }
        catch (IOException e2)
        {
            e2.printStackTrace();
        }
        catch (URISyntaxException e2)
        {
            e2.printStackTrace();
        }
    }
}
