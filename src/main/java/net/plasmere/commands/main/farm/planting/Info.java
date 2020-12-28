package net.plasmere.commands.main.farm.planting;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;

public class Info {


    public static EmbedBuilder plant(MessageReceivedEvent event, Guild guild) {
        return new EmbedBuilder().setTitle("Planting ``INFO`` for ``" + event.getAuthor().getName() + "``")
                .addField("Info", "This is how you plant things!", false)
                .addField("Sub-Commands", getSubCommands(guild), false)
        ;


    }

    private static String getSubCommands(Guild guild){
        String prefix = guild.prefix;

        StringBuilder subcommands = new StringBuilder();

        subcommands.append("**This**");
        subcommands.append("\n``").append(prefix).append("plant info`` : *Shows you this.*");
        subcommands.append("\n**Crops**");
        subcommands.append("\n``").append(prefix).append("plant wheat <amount>`` : *Plants the specified amount of wheat seeds.*");
        subcommands.append("\n``").append(prefix).append("plant rice <amount>`` : *Plants the specified amount of rice seeds.*");

        return subcommands.toString();
    }
}
