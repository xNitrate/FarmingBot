package net.plasmere.commands.main.farm.planting;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Rice {


    public static EmbedBuilder plant(MessageReceivedEvent event, Player player, int amount) {
        int canAfford = player.rice_seeds;
        int canPlace = player.land - player.wheat_planted - player.rice_planted;

        if (amount <= 0) {
            return new EmbedBuilder().setTitle("Planting ``RICE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You cannot plant **" + amount + "** (less than 1) of that!", false)
            ;
        }

        if (amount <= canAfford){
            if (amount <= canPlace) {
                return new EmbedBuilder().setTitle("Planting ``RICE`` for ``" + event.getAuthor().getName() + "``")
                        .addField("SUCCESS", "You just planted **" + tryToGet(player, amount) + "** of that!", false)
                ;
            } else {
                return new EmbedBuilder().setTitle("Planting ``RICE`` for ``" + event.getAuthor().getName() + "``")
                        .addField("FAILURE", "You do not have enough room for **" + amount + "** of that!", false)
                ;
            }
        } else {
            return new EmbedBuilder().setTitle("Planting ``RICE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You do not have enough for **" + amount + "** of that!", false)
            ;
        }


    }

    private static int tryToGet(Player player, int amount){
        player.updateKey("harvest_ready", false);
        player.updateKey("rice_planted", player.rice_planted + amount);
        player.updateKey("rice_seeds", player.rice_seeds - amount);
        return amount;
    }
}
