package xyz.pepefab.ultrapixelmon.listeners;

import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.pepefab.ultrapixelmon.Config.Config;
import xyz.pepefab.ultrapixelmon.Database.DatabaseHandler;
import xyz.pepefab.ultrapixelmon.Main;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LegendaryCapture {

    private final boolean logSQL = false;

    @SubscribeEvent
    public void onLegendaryCapture(CaptureEvent.SuccessfulCapture event){
        EntityPlayer player = (EntityPlayer) event.player;
        Pokemon legendary = (Pokemon) event.getPokemon().getPokemonData();

        if(!legendary.isLegendary()) return;
        if(Arrays.toString(Config.legendaryBlacklist).contains(legendary.getDisplayName())) return;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        Date date = new Date();

        final String req = String.format("INSERT INTO Legendary (Player,Pokemon,HeureSpawnPokemon,Talent,IVS,Nature) VALUES ('%s', '%s', '%s', '%s', %s, '%s');",
                        player.getName(),
                        legendary.getDisplayName(),
                        simpleDateFormat.format(date),
                        legendary.getAbilityName(),
                        legendary.getIVs().getPercentage(0),
                        legendary.getNature().name());
        if(logSQL) Main.log.error(req);
        Thread sqlThread = new Thread(() -> {
            DatabaseHandler.query(req);
        });
        sqlThread.start();
    }

}
