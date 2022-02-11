package xyz.pepefab.ultrapixelmon.Config;

import net.minecraftforge.common.config.Configuration;


import java.io.File;

public class Config {

    public static String[] legendaryBlacklist = new String[] {"Moltres"}; // Rappel : Le tableau ne peut pas être null

    public static void load(){
        Configuration config = new Configuration(new File("config/UPLegendariesHistory/config.json"));
        config.load();

        legendaryBlacklist = config.getStringList("legendaryBlacklist", Configuration.CATEGORY_GENERAL, legendaryBlacklist, "Liste des légendaires qui ne seront pas affichés dans l'historique");
        config.save();
    }
}
