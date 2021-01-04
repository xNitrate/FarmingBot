package net.plasmere.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;

public class Bind {

    public static void execute(MessageReceivedEvent event, Guild guild){
        event.getChannel().sendMessage(compile(event, guild).build()).queue();
    }

    public static EmbedBuilder compile(MessageReceivedEvent event, Guild guild) {
        if (event.getAuthor().getIdLong() != event.getGuild().getOwnerIdLong()) {
            return new EmbedBuilder().setTitle("Binding for ``" + event.getGuild().getName() + "``")
                    .addField("FAILURE", "You are not the owner of this guild!", false)
                    ;
        }

        return new EmbedBuilder().setTitle("Binding for ``" + event.getGuild().getName() + "``")
                .addField("SUCCESS", "You just bound this bot to channel **" + tryToGet(event, guild) + "**!", false)
        ;
    }

    private static long tryToGet(MessageReceivedEvent event, Guild guild){
        guild.updateKey("bound_to", event.getChannel().getIdLong());
        return guild.bound_to;
    }
}
