package net.plasmere;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.plasmere.Utils.Aliases;
import net.plasmere.listeners.MessageListener;
import net.plasmere.objects.config.Config;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.util.Date;

public class Main {
    private static final String guildsPath = System.getProperty("user.dir") + File.separator + "guilds" + File.separator;
    private static final String playersPath = System.getProperty("user.dir") + File.separator + "players" + File.separator;

    private static Config config;
    private static JDA jda;

    public static Config getConfig(){ return config; }
    public static JDA getJda(){ return jda; }

    public static Date startedAt;
    public static int timesFlushed;
    public static File gDir;
    public static File pDir;

    public static void main(String[] args) throws LoginException, InterruptedException {
        config = new Config();
        System.out.println("Default Prefix: " + config.default_prefix);

        JDABuilder builder = JDABuilder.createDefault(config.token)
                .setActivity(Activity.playing("a Farming Game"));
        builder.addEventListeners(new MessageListener(config));
        jda = builder.build().awaitReady();

        start();
    }

    private static void start(){
        startedAt = new Date();
        timesFlushed = 0;

        Aliases.putAliases();

        gDir = new File(guildsPath);
        pDir = new File(playersPath);
        if (gDir.mkdir()){
            System.out.println("Made Guilds folder!");
        }
        if (pDir.mkdir()){
            System.out.println("Made Players folder!");
        }
    }
}
