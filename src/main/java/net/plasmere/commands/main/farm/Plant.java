package net.plasmere.commands.main.farm;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.commands.main.farm.planting.Info;
import net.plasmere.commands.main.farm.planting.Rice;
import net.plasmere.commands.main.farm.planting.Wheat;
import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;

public class Plant {
    private static EmbedBuilder eb = new EmbedBuilder();

    public static void execute(MessageReceivedEvent event, Guild guild, Player player){
        event.getChannel().sendMessage(
                compile(event, guild, player)
                        .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Guild guild, Player player){
        String[] args = event.getMessage().getContentRaw().split(" ");

        switch (args[1]){
            case "wheat":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Wheat.plant(event, player, Integer.parseInt(args[2]));
                }

            case "rice":
                if (args.length < 3){
                    return tooLess(event, player);
                } else if (args.length > 3){
                    return tooMany(event, player);
                } else {
                    return Rice.plant(event, player, Integer.parseInt(args[2]));
                }

            case "help":
            case "info":
            default:
                return Info.plant(event, guild);
        }


    }

    private static EmbedBuilder tooLess(MessageReceivedEvent event, Player player) {
        return new EmbedBuilder().setTitle("Buying for ``" + event.getAuthor().getName() + "``")
                .addField("FAILURE", "You put too few arguments!", false)
        ;

    }

    private static EmbedBuilder tooMany(MessageReceivedEvent event, Player player) {
        return new EmbedBuilder().setTitle("Buying for ``" + event.getAuthor().getName() + "``")
                .addField("FAILURE", "You put too many arguments!", false)
        ;

    }
}
