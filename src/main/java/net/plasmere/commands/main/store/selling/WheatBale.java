package net.plasmere.commands.main.store.selling;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;

public class WheatBale {


    public static final int price = 10;

    public static EmbedBuilder execute(MessageReceivedEvent event, Player player, int amount) {
        int canAfford = player.wheat_crop;

        if (amount <= 0) {
            return new EmbedBuilder().setTitle("Selling ``WHEAT BALE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You cannot sell **" + amount + "** (less than 1) of that!", false)
            ;
        }

        if (amount <= canAfford){
            return new EmbedBuilder().setTitle("Selling ``WHEAT BALE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just sold **" + tryToGet(player, amount) + "** of that for $**" + (price * amount) + "**!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Selling ``WHEAT BALE`` for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "You do not have enough for selling **" + amount + "** of that!", false)
            ;
        }


    }

    private static int tryToGet(Player player, int amount){
        player.updateKey("bal", player.bal + amount * price);
        player.updateKey("wheat_crop", player.wheat_crop - amount);
        return amount;
    }
}
