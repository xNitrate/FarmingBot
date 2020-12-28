package net.plasmere.commands.main.other;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;

public class Inventory {


    public static void execute(MessageReceivedEvent event, Player player){
        event.getChannel().sendMessage(
                compile(event, player)
                .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Player player){

        return new EmbedBuilder()
                .addField("Level", String.valueOf(player.lvl), true)
                .addField("EXP", String.valueOf(player.xp), true)
                .addField("Balance", String.valueOf(player.bal), true)
                .addField("Land", String.valueOf(player.land), true)
                .addBlankField(false)
                .addField("Wheat Seeds", String.valueOf(player.wheat_seeds), true)
                .addField("Rice Seeds", String.valueOf(player.rice_seeds), true)
                .addBlankField(false)
                .addField("Watering Cans", String.valueOf(player.cans), true)
                .addField("Wheat Harvested", String.valueOf(player.wheat_crop), true)
                .addField("Rice Harvested", String.valueOf(player.rice_crop), true)
                .addBlankField(false)
                .addField("User", event.getAuthor().getName(), true)
                .setAuthor("Inventory for " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl())
                ;


    }
}
