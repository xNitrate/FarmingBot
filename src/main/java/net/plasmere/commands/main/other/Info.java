package net.plasmere.commands.main.other;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.Main;
import net.plasmere.objects.Player;

import java.util.Date;

public class Info {


    public static void execute(MessageReceivedEvent event, Player player){
        try {
            event.getChannel().sendMessage(
                    compile(event, player)
                            .build()
            ).queue();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Player player){

        return new EmbedBuilder()
                .addField("Level", String.valueOf(player.lvl), true)
                .addField("EXP", String.valueOf(player.xp), true)
                .addField("Balance", String.valueOf(player.bal), true)
                .addField("Land", String.valueOf(player.land), true)
                .addField("EXP to Level Up", (player.getNeededXp() - player.xp) + " (" + player.getNeededXp() + " total)", true)
                .addBlankField(false)
                .addField("Wheat Planted", String.valueOf(player.wheat_planted), true)
                .addField("Rice Planted", String.valueOf(player.rice_planted), true)
                .addField("Harvest Ready?", player.harvest_ready ? "YES!" : "NO!", true)
                .addBlankField(false)
                .addField("User", event.getAuthor().getName(), true)
                .setAuthor("Info for " + event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl())
        ;


    }
}
