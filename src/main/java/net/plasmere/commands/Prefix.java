package net.plasmere.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;

public class Prefix {
    public static void execute(MessageReceivedEvent event, Guild guild){
        event.getChannel().sendMessage(compile(event, guild).build()).queue();
    }

    public static EmbedBuilder compile(MessageReceivedEvent event, Guild guild) {
        if (event.getAuthor().getIdLong() != event.getGuild().getOwnerIdLong()) {
            return new EmbedBuilder().setTitle("Prefix for ``" + event.getGuild().getName() + "``")
                    .addField("FAILURE", "You are not the owner of this guild!", false)
                    ;
        }

        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args.length > 2) {
            return new EmbedBuilder().setTitle("Prefix for ``" + event.getGuild().getName() + "``")
                    .addField("FAILURE", "Too many arguments!", false)
                    ;
        }

        if (args.length < 2) {
            return new EmbedBuilder().setTitle("Prefix for ``" + event.getGuild().getName() + "``")
                    .addField("FAILURE", "Too few arguments!", false)
                    ;
        }

        return new EmbedBuilder().setTitle("Prefix for ``" + event.getGuild().getName() + "``")
                .addField("SUCCESS", "You just set your server prefix for this bot to **" + tryToGet(guild, args[1]) + "**!", false)
        ;



    }

    private static String tryToGet(Guild guild, String prefix){
        guild.updateKey("prefix", prefix);
        return guild.prefix;
    }
}
