package net.plasmere.commands.main.farm;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;

public class Water {


    public static void execute(MessageReceivedEvent event, Player player){
        event.getChannel().sendMessage(
                compile(event, player)
                        .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Player player){
        if (player.cans > 0) {
            return new EmbedBuilder().setTitle("Watering for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just watered **" + tryToGet(player) + "** crops!\n", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Watering for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You don't have enough watering cans!", false)
            ;
        }


    }

    private static int tryToGet(Player player){
        int amount = player.wheat_planted + player.rice_planted;

        player.updateKey("harvest_ready", true);
        player.updateKey("cans", player.cans - 1);

        return amount;
    }
}
