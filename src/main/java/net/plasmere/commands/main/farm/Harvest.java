package net.plasmere.commands.main.farm;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;

public class Harvest {


    public static void execute(MessageReceivedEvent event, Player player){
        event.getChannel().sendMessage(
                compile(event, player)
                        .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Player player){
        if (player.harvest_ready) {
            return new EmbedBuilder().setTitle("Harvesting for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just harvested **" + tryToGet(player) + "** crops!\n" +
                            "You now have **" + player.wheat_crop + "** harvested wheat!\n" +
                            "You now have **" + player.rice_crop + "** harvested rice!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Harvesting for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "Could not harvest **any** crops!", false)
            ;
        }


    }

    private static int tryToGet(Player player){
        int amount = player.wheat_planted + player.rice_planted;

        player.updateKey("harvest_ready", false);
        player.updateKey("wheat_crop", player.wheat_crop + player.wheat_planted);
        player.updateKey("rice_crop", player.rice_crop + player.rice_planted);
        player.updateKey("wheat_planted", 0);
        player.updateKey("rice_planted", 0);

        return amount;
    }
}
