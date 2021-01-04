package net.plasmere.commands.main.farm;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.plasmere.objects.Player;

import java.util.Random;

public class Harvest {

    private static Random RNG = new Random();

    public static void execute(MessageReceivedEvent event, Player player){
        event.getChannel().sendMessage(
                compile(event, player)
                        .build()
        ).queue();
    }

    private static EmbedBuilder compile(MessageReceivedEvent event, Player player){
        if (player.harvest_ready) {
            int[] amount = tryToGet(player);

            return new EmbedBuilder().setTitle("Harvesting for ``" + event.getAuthor().getName() + "``")
                    .addField("SUCCESS", "You just harvested **" + amount[0] + "** crops!\n" +
                            "You received **" + amount[1] + "** harvested wheat (**" + player.wheat_crop + "** total)!\n" +
                            "You received **" + amount[2] + "** harvested rice (**" + player.rice_crop + "** total)!\n" +
                            "You received **" + amount[3] + "** wheat seeds (**" + player.wheat_seeds + "** total)!\n" +
                            "You received **" + amount[4] + "** rice seeds (**" + player.rice_seeds + "** total)!", false)
            ;
        } else {
            return new EmbedBuilder().setTitle("Harvesting for ``" + event.getAuthor().getName() + "``")
                    .addField("FAILURE", "Could not harvest **any** crops!", false)
            ;
        }


    }

    private static int[] tryToGet(Player player){
        int[] amount = new int[5];
        int wheatc = wheatGet(player);
        int wheats = wheatSeedGet(player);
        int ricec = riceGet(player);
        int rices = riceSeedGet(player);
        amount[0] = player.wheat_planted + player.rice_planted;
        amount[1] = wheatc;
        amount[2] = ricec;
        amount[3] = wheats;
        amount[4] = rices;

        player.updateKey("harvest_ready", false);
        player.updateKey("wheat_crop", player.wheat_crop + wheatc);
        player.updateKey("rice_crop", player.rice_crop + ricec);
        player.updateKey("wheat_seeds", player.wheat_seeds + wheats);
        player.updateKey("rice_seeds", player.rice_seeds + rices);
        player.updateKey("wheat_planted", 0);
        player.updateKey("rice_planted", 0);

        return amount;
    }

    private static int wheatGet(Player player){
        int amount = 0;
        for (int i = 0; i < player.wheat_planted; i++){
            int r = RNG.nextInt(7);
            switch (r){
                case 0:
                case 1:
                case 2:
                    continue;
                case 3:
                    amount++;
                    break;
                case 4:
                    amount += 2;
                    break;
                case 5:
                    amount += 3;
                    break;
            }
        }

        return amount;
    }

    private static int riceGet(Player player){
        int amount = 0;
        for (int i = 0; i < player.rice_planted; i++){
            int r = RNG.nextInt(6);
            switch (r){
                case 0:
                case 1:
                case 2:
                    continue;
                case 3:
                    amount++;
                    break;
                case 4:
                    amount += 2;
                    break;
                case 5:
                    amount += 3;
                    break;
            }
        }

        return amount;
    }

    private static int wheatSeedGet(Player player){
        int amount = 0;
        for (int i = 0; i < player.wheat_planted; i++){
            int r = RNG.nextInt(3);
            switch (r){
                case 0:
                case 1:
                    continue;
                case 2:
                    amount++;
                    break;
            }
        }

        return amount;
    }

    private static int riceSeedGet(Player player){
        int amount = 0;
        for (int i = 0; i < player.wheat_planted; i++){
            int r = RNG.nextInt(3);
            switch (r){
                case 0:
                case 1:
                    continue;
                case 2:
                    amount++;
                    break;
            }
        }

        return amount;
    }
}
