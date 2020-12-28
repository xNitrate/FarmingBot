package net.plasmere.commands.main.store.buying;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;

public class Info {


    public static EmbedBuilder execute(MessageReceivedEvent event, Guild guild) {
        return new EmbedBuilder().setTitle("Buying ``INFO`` for ``" + event.getAuthor().getName() + "``")
                .addField("Info", "This is how you buy things from the shop!", false)
                .addField("Sub-Commands", getSubCommands(guild), false)
        ;


    }

    private static String getSubCommands(Guild guild){
        String prefix = guild.prefix;

        StringBuilder subcommands = new StringBuilder();

        subcommands.append("**This**");
        subcommands.append("\n``").append(prefix).append("buy info`` : *Shows you this.*");
        subcommands.append("\n**Crops**");
        subcommands.append("\n*Buy <amount> of wheat seeds for* $**").append(Wheat.price).append("** *each.* : ").append("``").append(prefix).append("buy wheat <amount>``");
        subcommands.append("\n*Buy <amount> of rice seeds for* $**").append(Rice.price).append("** *each.* : ").append("``").append(prefix).append("buy rice <amount>``");
        subcommands.append("\n**Utilities**");
        subcommands.append("\n*Buy <amount> of land (in acres) for* $**").append(Land.price).append("** *each.* : ").append("``").append(prefix).append("buy land <amount>``");
        subcommands.append("\n*Buy <amount> of watering cans for* $**").append(Cans.price).append("** *each.* : ").append("``").append(prefix).append("buy can <amount>``");

        return subcommands.toString();
    }
}
