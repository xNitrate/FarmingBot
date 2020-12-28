package net.plasmere.commands.main.store.selling;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Rice {


    public static final int price = Math.round(10 * 0.8f);

    public static EmbedBuilder execute(MessageReceivedEvent event, Player player, int amount) {
        int canAfford = player.rice_seeds;

        if (amount <= 0) {
            return new EmbedBuilder().setTitle("Selling ``RICE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You cannot sell **" + amount + "** (less than 1) of that!", false)
            ;
        }

        if (amount <= canAfford){
            return new EmbedBuilder().setTitle("Selling ``RICE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just sold **" + tryToGet(player, amount) + "** of that for $**" + (price * amount) + "**!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Selling ``RICE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You do not have enough for selling **" + amount + "** of that!", false)
            ;
        }


    }

    private static int tryToGet(Player player, int amount){
        player.updateKey("bal", player.bal + amount * price);
        player.updateKey("rice_seeds", player.rice_seeds - amount);
        return amount;
    }
}
