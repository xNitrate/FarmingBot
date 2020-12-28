package net.plasmere.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;

public class Help {


    public static void execute(MessageReceivedEvent event, Guild guild){
        event.getChannel().sendMessage(new EmbedBuilder()
                .setTitle("Commands")
                .setDescription(compile(guild))
                .build()
        ).queue();
    }

    private static String compile(Guild guild){
        String prefix = guild.prefix;

        StringBuilder commands = new StringBuilder();
        commands.append("``").append(prefix).append("help`` : *Shows you this helpful message.*");
        commands.append("\n``").append(prefix).append("inventory`` : *Binds the bot to the channel you use it in. (Only the server owner can do this.)*");
        commands.append("\n``").append(prefix).append("status`` : *Shows you the bot's status.*");
        commands.append("\n``").append(prefix).append("info`` : *Shows you your farm information.*");
        commands.append("\n``").append(prefix).append("inventory`` : *Shows you your inventory.*");
        commands.append("\n``").append(prefix).append("buy <item> <amount>`` : *Buys a specified amount of items.*");
        commands.append("\n``").append(prefix).append("sell <item> <amount>`` : *Sells a specified amount of items.*");
        commands.append("\n``").append(prefix).append("plant <item> <amount>`` : *Plants a specified amount of seeds.*");
        commands.append("\n``").append(prefix).append("water`` : *Waters your crops.*");
        commands.append("\n``").append(prefix).append("harvest`` : *Harvests your crops.*");

        return commands.toString();
    }
}
