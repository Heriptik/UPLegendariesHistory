package xyz.pepefab.ultrapixelmon;

import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.pepefab.ultrapixelmon.Config.Config;
import xyz.pepefab.ultrapixelmon.Database.DatabaseHandler;
import xyz.pepefab.ultrapixelmon.commands.ReloadCommand;
import xyz.pepefab.ultrapixelmon.commands.LastLegendCommand;
import xyz.pepefab.ultrapixelmon.listeners.LegendaryCapture;

import java.io.File;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class Main
{
    public static final String MODID = "uplegendarieshistory";
    public static final String NAME = "UPLegendariesHistory";
    public static final String VERSION = "0.2";

    private static Logger logger;
    public static File configDir;
    public static String dblocation;
    public static Logger log = LogManager.getLogger(Main.NAME);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        configDir = new File(event.getModConfigurationDirectory() + "/" + NAME);
        configDir.mkdirs();
        Config.load();

        dblocation = event.getModConfigurationDirectory() + "/" + Main.NAME + "/data.db";
        DatabaseHandler.connect();
        DatabaseHandler.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new LegendaryCapture());
        Pixelmon.EVENT_BUS.register(new LegendaryCapture());
        logger.info("[UPLegendariesHistory] Initialisation effectue avec succes. Version: " + Main.VERSION);
    }

    @EventHandler
    public void onServerStrating(FMLServerStartingEvent event){
        event.registerServerCommand(new ReloadCommand());
        event.registerServerCommand(new LastLegendCommand());
    }
}
