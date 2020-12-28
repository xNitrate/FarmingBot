package net.plasmere.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.plasmere.Main;
import net.plasmere.Utils.AliasType;
import net.plasmere.Utils.Aliases;
import net.plasmere.Utils.ObjectHelper;
import net.plasmere.commands.Bind;
import net.plasmere.commands.Help;
import net.plasmere.commands.Prefix;
import net.plasmere.commands.Status;
import net.plasmere.commands.main.farm.Harvest;
import net.plasmere.commands.main.farm.Plant;
import net.plasmere.commands.main.farm.Water;
import net.plasmere.commands.main.other.Info;
import net.plasmere.commands.main.other.Inventory;
import net.plasmere.commands.main.store.Buy;
import net.plasmere.commands.main.store.Sell;
import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Date;

public class MessageListener extends ListenerAdapter {
    private final EmbedBuilder eb = new EmbedBuilder();
    private final Config config;

    public MessageListener(Config config){
        this.config = config;
    }

    private Guild guild;
    private Player player;

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event){
        if (event.getAuthor().isBot()) return;

        long milisUp = (new Date()).getTime() - Main.startedAt.getTime();
        double minsUp = milisUp / 60000f;
        double minsTillFlush = minsUp - ( Main.timesFlushed * 30 );

        if (minsTillFlush >= 30) {
            try {
                flushGuilds();
                flushPlayers();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String[] args = event.getMessage().getContentRaw().split(" ");
        String command = args[0].toLowerCase();

        long guildId = event.getGuild().getIdLong();
        long playerId = event.getAuthor().getIdLong();

//        if (! ObjectHelper.hasGuild(guildName)){
//            ObjectHelper.guilds.add(new Guild(guildName));
//        }
//
//        if (! ObjectHelper.hasPlayer(username)){
//            ObjectHelper.players.add(new Player(username));
//        }

        guild = ObjectHelper.getGuild(guildId);
        player = ObjectHelper.getPlayer(playerId);

        guild.addXp(1);
        player.addXp(1);
        
        if (! command.startsWith(guild.prefix)) return;
        command = command.substring(guild.prefix.length());

        if (isThisCommand(command, AliasType.BIND)){
            Bind.execute(event, guild);
        } else if (isThisCommand(command, AliasType.PREFIX)){
            Prefix.execute(event, guild);
        }

        if (guild.bound_to != 0L) { if (event.getChannel().getIdLong() != guild.bound_to) return; }

        if (isThisCommand(command, AliasType.STATUS)) {
            Status.execute(event, config, guild);
        } else if (isThisCommand(command, AliasType.INFO)){
            Info.execute(event, player);
        } else if (isThisCommand(command, AliasType.INVENTORY)){
            Inventory.execute(event, player);
        } else if (isThisCommand(command, AliasType.BUY)){
            Buy.execute(event, guild, player);
        } else if (isThisCommand(command, AliasType.SELL)){
            Sell.execute(event, guild, player);
        } else if (isThisCommand(command, AliasType.PLANT)){
            Plant.execute(event, guild, player);
        } else if (isThisCommand(command, AliasType.WATER)){
            Water.execute(event, player);
        } else if (isThisCommand(command, AliasType.HARVEST)){
            Harvest.execute(event, player);
        } else if (isThisCommand(command, AliasType.HELP)){
            Help.execute(event, guild);
        } else {
            Help.execute(event, guild);
        }

        try {
            player.saveInfo();
            guild.saveInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flushGuilds() throws IOException {
        int amount = 0;
        for (Guild guild : ObjectHelper.guilds) {
            guild.saveInfo();
        }

        ObjectHelper.flushGuilds();

        System.out.println("Flushed " + amount + " Guilds from memory!");
    }

    public void flushPlayers() throws IOException {
        int amount = 0;
        for (Player player : ObjectHelper.players) {
            player.saveInfo();
        }

        ObjectHelper.flushPlayers();

        System.out.println("Flushed " + amount + " Players from memory!");
    }

    public boolean isThisCommand(String command, AliasType check){
        try {
            for (String a : Aliases.aliases.get(check)) {
                if (command.equals(a)) return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }
}
