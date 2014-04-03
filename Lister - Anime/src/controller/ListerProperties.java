package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;


public class ListerProperties
{

    // the defaultProps is a Propertie Object that has all Properties of the user
    // if the file with the specifies Properties isn't found, the default properties are loaded
    private static Properties defaultProps = null;
    // the lookAndFeelProps has all LookAndFeels which are installed into the MP3-Player
    private static Properties lookAndFeelProps = null;
    // the languageProps has all Language which are supported into the MP3-Player
    private static Properties languageProps = null;
    private static Locale locale = null;


    private ListerProperties()
    {
        defaultProps = new Properties();
        lookAndFeelProps = new Properties();
        languageProps = new Properties();
        try
        {
            loadPreferences();
        }
        catch (Exception e)
        {
            setDefaultProperties();
        }
        setLocale();
    }


    /**
     * Sets the Localeinformation to load the language. If there is a failure while loading from the properties, there
     * will be loadet the german language
     */
    private void setLocale()
    {
        try
        {
            locale = new Locale(defaultProps.getProperty("language"), defaultProps.getProperty("country"));
        }
        catch (NullPointerException npe)
        {
            locale = new Locale("de", "CH");
        }
    }


    private void loadPreferences() throws Exception
    {
        InputStream in = null;
        in = new FileInputStream("listerA.properties");
        defaultProps.load(in);
        try
        {
            in = this.getClass().getClassLoader().getResourceAsStream("data/language.properties");
            languageProps.load(in);
            in = this.getClass().getClassLoader().getResourceAsStream("data/lookandfeel.properties");
            lookAndFeelProps.load(in);
        }
        catch (FileNotFoundException e)
        {
            in = this.getClass().getResource("..\\data\\language.properties").openStream();
            languageProps.load(in);
            in = this.getClass().getResource("..\\data\\lookandfeel.properties").openStream();
            lookAndFeelProps.load(in);
        }
        in.close();
    }


    private void setDefaultProperties()
    {
        defaultProps.put("design", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        defaultProps.put("language", "0");
        lookAndFeelProps.put("Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        lookAndFeelProps.put("Metal", "javax.swing.plaf.metal.MetalLookAndFeel");
        languageProps.put("de", "CH");
    }


    public static Locale getLocale()
    {
        if (locale == null)
        {
            new ListerProperties();
        }
        return locale;
    }


    public static Properties getLanguageProperties()
    {
        if (languageProps == null)
        {
            new ListerProperties();
        }
        return languageProps;
    }


    public static Properties getDefaultProperties()
    {
        if (defaultProps == null)
        {
            new ListerProperties();
        }
        return defaultProps;
    }


    public static Properties getLAFProperties()
    {
        if (lookAndFeelProps == null)
        {
            new ListerProperties();
        }
        return lookAndFeelProps;
    }


    public static void saveProperties(Properties properties)
    {
        FileOutputStream out;
        defaultProps = properties;
        try
        {
            // out = new FileOutputStream("src\\data\\lister.properties");
            out = new FileOutputStream("listerA.properties");
            defaultProps.store(out, "save");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void setCurrentLocale(Locale currentLocale)
    {
        locale = currentLocale;
    }
}
