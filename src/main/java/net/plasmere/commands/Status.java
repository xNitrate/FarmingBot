package net.plasmere.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.Main;
import net.plasmere.objects.Guild;
import net.plasmere.objects.config.Config;

import java.util.Date;

public class Status {


    public static void execute(MessageReceivedEvent event, Config config, Guild guild){
        event.getChannel().sendMessage(
                compile(event, config, guild)
                .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Config config, Guild guild){
        long milisUp = (new Date()).getTime() - Main.startedAt.getTime();
        double minsUp = milisUp / 60000f;

        return new EmbedBuilder()
                .addField("Ping", String.valueOf(event.getJDA().getGatewayPing()), true)
                .addField("Author", "Nitrate", true)
                .addField("Prefix", "``" + guild.prefix + "`` (Default: ``" + config.default_prefix + "``)", true)
                .addBlankField(false)
                .addField("Link", "https://youtube.com/nitrateorigins", true)
                .addField("Uptime", Math.round(minsUp) + " minutes", true)
                .addField("GEXP", String.valueOf(guild.xp), true)
                .addBlankField(false)
                .addField("Guild", event.getGuild().getName(), true)
                .addField("GLVL", String.valueOf(guild.lvl), true)
                .setAuthor("Status", event.getGuild().getIconUrl(), event.getGuild().getIconUrl())
                ;


    }
}
