package net.plasmere.commands.main.store.selling;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;
import net.plasmere.objects.config.Config;

public class Info {


    public static EmbedBuilder execute(MessageReceivedEvent event, Guild guild) {
        return new EmbedBuilder().setTitle("Selling ``INFO`` for ``" + event.getAuthor().getName() + "``")
                .addField("Info", "This is how you sell things to the shop!", false)
                .addField("Sub-Commands", getSubCommands(guild), false)
        ;


    }

    private static String getSubCommands(Guild guild){
        String prefix = guild.prefix;

        StringBuilder subcommands = new StringBuilder();

        subcommands.append("**This**");
        subcommands.append("\n``").append(prefix).append("sell info`` : *Shows you this.*");
        subcommands.append("\n**Crops**");
        subcommands.append("\n*Sells <amount> of wheat seeds for* $**").append(Wheat.price).append("** *each.* : ").append("``").append(prefix).append("sell wheat <amount>``");
        subcommands.append("\n*Sells <amount> of rice seeds for* $**").append(Rice.price).append("** *each.* : ").append("``").append(prefix).append("sell rice <amount>``");
        subcommands.append("\n*Sells <amount> of wheat harvested for* $**").append(WheatBale.price).append("** *each.* : ").append("``").append(prefix).append("sell wheatbale <amount>``");
        subcommands.append("\n*Sells <amount> of rice harvested for* $**").append(RiceBale.price).append("** *each.* : ").append("``").append(prefix).append("sell ricebale <amount>``");
        subcommands.append("\n**Utilities**");
        subcommands.append("\n*Sells <amount> of land (in acres) for* $**").append(Land.price).append("** *each.* : ").append("``").append(prefix).append("sell land <amount>``");
        subcommands.append("\n*Sells <amount> of watering cans for* $**").append(Cans.price).append("** *each.* : ").append("``").append(prefix).append("sell cans <amount>``");

        return subcommands.toString();
    }
}
