package net.plasmere.commands.main.store.selling;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Cans {


    public static final int price = Math.round(50 * 0.8f);

    public static EmbedBuilder execute(MessageReceivedEvent event, Player player, int amount) {
        int canAfford = player.cans;

        if (amount <= 0) {
            return new EmbedBuilder().setTitle("Selling ``CANS`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You cannot sell **" + amount + "** (less than 1) of that!", false)
            ;
        }

        if (amount <= canAfford){
            return new EmbedBuilder().setTitle("Selling ``CANS`` for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just sold **" + tryToGet(player, amount) + "** of that for $**" + (price * amount) + "**!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Selling ``CANS`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You do not have enough for selling **" + amount + "** of that!", false)
            ;
        }


    }

    private static int tryToGet(Player player, int amount){
        player.updateKey("bal", player.bal + amount * price);
        player.updateKey("cans", player.cans - amount);
        return amount;
    }
}
