package net.plasmere.Utils;

import net.plasmere.objects.Guild;
import net.plasmere.objects.Player;

import java.util.ArrayList;
import java.util.List;

public class ObjectHelper {

    public static List<Guild> guilds = new ArrayList<>();
    public static List<Player> players = new ArrayList<>();

    public static boolean hasGuild(long id){
        for (Guild guild : guilds){
            if (guild.id == id) return true;
        }
        return false;
    }

    public static boolean hasPlayer(long id){
        for (Player player : players){
            if (player.id == id) return true;
        }
        return false;
    }

    public static Guild getGuild(long id) {
        for (Guild guild : guilds){
            if (guild.id == id) return guild;
        }

        Guild guild = new Guild(id);

        guilds.add(guild);
        return guild;
    }

    public static Player getPlayer(long id) {
        for (Player player : players){
            if (player.id == id) return player;
        }

        Player player = new Player(id);

        players.add(player);
        return player;
    }

    public static void flushGuilds() {
        guilds = new ArrayList<>();
    }

    public static void flushPlayers() {
        players = new ArrayList<>();
    }
}
