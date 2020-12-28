package net.plasmere.commands.main.store;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.commands.main.store.selling.*;
import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Sell {
    private static EmbedBuilder eb = new EmbedBuilder();

    public static void execute(MessageReceivedEvent event, Guild guild, Player player){
        event.getChannel().sendMessage(
                compile(event, guild, player)
                        .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Guild guild, Player player){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args.length <= 1) {
            return Info.execute(event, guild);
        }

        switch (args[1]){
            case "w":
            case "wheat":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Wheat.execute(event, player, Integer.parseInt(args[2]));
                }
            case "r":
            case "rice":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Rice.execute(event, player, Integer.parseInt(args[2]));
                }
            case "l":
            case "land":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Land.execute(event, player, Integer.parseInt(args[2]));
                }
            case "cans":
            case "can":
            case "c":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Cans.execute(event, player, Integer.parseInt(args[2]));
                }
            case "wb":
            case "wheatbale":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return WheatBale.execute(event, player, Integer.parseInt(args[2]));
                }
            case "rb":
            case "ricebale":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return RiceBale.execute(event, player, Integer.parseInt(args[2]));
                }
            case "help":
            case "info":
            default:
                return Info.execute(event, guild);
        }


    }

    private static EmbedBuilder tooLess(MessageReceivedEvent event, Player player) {
        return new EmbedBuilder().setTitle("Selling for ``" + event.getAuthor().getName() + "``")
                .addField("FAILURE", "You put too few arguments!", false)
        ;

    }

    private static EmbedBuilder tooMany(MessageReceivedEvent event, Player player) {
        return new EmbedBuilder().setTitle("Selling for ``" + event.getAuthor().getName() + "``")
                .addField("FAILURE", "You put too many arguments!", false)
        ;

    }
}
