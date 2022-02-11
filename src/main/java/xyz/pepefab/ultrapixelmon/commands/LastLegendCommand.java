package xyz.pepefab.ultrapixelmon.commands;

import com.google.common.collect.Lists;
import com.sun.org.apache.xerces.internal.xs.StringList;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.HoverEvent;
import xyz.pepefab.ultrapixelmon.Config.Config;
import xyz.pepefab.ultrapixelmon.Database.DatabaseHandler;
import xyz.pepefab.ultrapixelmon.utils.ChatUtils;
import xyz.pepefab.ultrapixelmon.utils.PermissionsUtils;

import javax.annotation.Nullable;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LastLegendCommand extends CommandBase {

    private final List<String> aliases = Lists.newArrayList(new String[]{"ll"});
    private long elapsedDays;
    private long elapsedHours;
    private long elapsedMinutes;

    @Override
    public String getName() {
        return "lastlegend";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "&cUsage: /lastlegend";
    }

    @Override
    public List<String> getAliases(){
        return this.aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String request3 = "SELECT * FROM Legendary "
                + "ORDER BY ID DESC LIMIT 2,1";
        String request2 = "SELECT * FROM Legendary "
                + "ORDER BY ID DESC LIMIT 1,1";
        String request1 = "SELECT * FROM Legendary "
                + "ORDER BY ID DESC LIMIT 1";
        Thread sqlThread = new Thread(() -> {
            try{
                ResultSet resultSet3 = DatabaseHandler.queryWithResult(request3);
                ResultSet resultSet2 = DatabaseHandler.queryWithResult(request2);
                ResultSet resultSet1 = DatabaseHandler.queryWithResult(request1);

                while(resultSet1.next()){

                    sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&e&m-------------&a DERNIERS LEGENDAIRES &e&m------------+")));
                    TextComponentString text3 = new TextComponentString(ChatUtils.replaceTextFormating(" &a[&eDate&a]"));
                    text3.setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new TextComponentString(ChatUtils.replaceTextFormating("&e" + resultSet3.getString("HeureSpawnPokemon"))))));
                    TextComponentString message3 = new TextComponentString(ChatUtils.replaceTextFormating("&a3° &e-"
                            + " &a[&d&l&n" + resultSet3.getString("Pokemon") + "&a]"
                            + " &e" + resultSet3.getString("Player")
                            + " &aTalent:&e " + resultSet3.getString("Talent")
                            + " &aIVS: &e" + resultSet3.getInt("IVS") + "%"
                            + " &aNature: &e" + resultSet3.getString("Nature")));
                    message3.appendSibling(text3);
                    sender.sendMessage(message3);

                    TextComponentString text2 = new TextComponentString(ChatUtils.replaceTextFormating(" &a[&eDate&a]"));
                    text2.setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new TextComponentString(ChatUtils.replaceTextFormating("&e" + resultSet2.getString("HeureSpawnPokemon"))))));
                    TextComponentString message2 = new TextComponentString(ChatUtils.replaceTextFormating("&a2° &e-"
                            + " &a[&d&l&n" + resultSet2.getString("Pokemon") + "&a]"
                            + " &e" + resultSet2.getString("Player")
                            + " &aTalent:&e " + resultSet2.getString("Talent")
                            + " &aIVS: &e" + resultSet2.getInt("IVS") + "%"
                            + " &aNature: &e" + resultSet2.getString("Nature")));
                    message2.appendSibling(text2);
                    sender.sendMessage(message2);

                    TextComponentString text = new TextComponentString(ChatUtils.replaceTextFormating(" &a[&eDate&a]"));
                    text.setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new TextComponentString(ChatUtils.replaceTextFormating("&e" + resultSet1.getString("HeureSpawnPokemon"))))));
                    TextComponentString message = new TextComponentString(ChatUtils.replaceTextFormating("&a1° &e-"
                            + " &a[&d&l&n" + resultSet1.getString("Pokemon") + "&a]"
                            + " &e" + resultSet1.getString("Player")
                            + " &aTalent:&e " + resultSet1.getString("Talent")
                            + " &aIVS: &e" + resultSet1.getInt("IVS") + "%"
                            + " &aNature: &e" + resultSet1.getString("Nature")));
                    message.appendSibling(text);
                    sender.sendMessage(message);
                    sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&e&m---------------------------------------------+")));
                    if(resultSet1 == null){
                        sender.sendMessage(new TextComponentString(ChatUtils.replaceTextFormating("&cIl n'y a pas encore eu de légendaire capturé ! (3)")));
                    }
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        });
        sqlThread.start();

    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionsUtils.canUse("upworldeventforge.lastlegend", sender);
    }
}

