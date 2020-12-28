package net.plasmere.commands.main.store.buying;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Wheat {


    public static final int price = 5;

    public static EmbedBuilder execute(MessageReceivedEvent event, Player player, int amount) {
        int canAfford = player.bal / price;

        if (amount <= 0) {
            return new EmbedBuilder().setTitle("Buying ``WHEAT`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You cannot buy **" + amount + "** (less than 1) of that!", false)
            ;
        }

        if (amount <= canAfford){
            return new EmbedBuilder().setTitle("Buying ``WHEAT`` for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just bought **" + tryToGet(player, amount) + "** of that for $**" + (price * amount) + "**!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Buying ``WHEAT`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You do not have enough for **" + amount + "** of that!", false)
            ;
        }


    }

    private static int tryToGet(Player player, int amount){
        player.updateKey("bal", player.bal - amount * price);
        player.updateKey("wheat_seeds", player.wheat_seeds + amount);
        return amount;
    }
}
