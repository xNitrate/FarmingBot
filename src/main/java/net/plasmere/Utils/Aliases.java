package net.plasmere.Utils;

import java.util.HashMap;

public class Aliases {
    public static String[] help = { "help" };
    public static String[] status = { "status" , "bot" , "botstatus" , "st" };
    public static String[] info = { "info" , "inf" };
    public static String[] inventory = { "inventory" , "inv" , "i" };
    public static String[] buy = { "buy" , "b" , "get" , "take" };
    public static String[] sell = { "sell" , "s" , "give" };
    public static String[] plant = { "plant" , "p" , "put" };
    public static String[] water = { "water" , "w" };
    public static String[] harvest = { "harvest" , "h" };
    public static String[] bind = { "bind" , "bi" , "bin" };
    public static String[] prefix = { "prefix" , "pre" };

    public static HashMap<AliasType, String[]> aliases = new HashMap<>();

    public static void putAliases() {
        aliases.put(AliasType.HELP, help);
        aliases.put(AliasType.STATUS, status);
        aliases.put(AliasType.INFO, info);
        aliases.put(AliasType.INVENTORY, inventory);
        aliases.put(AliasType.BUY, buy);
        aliases.put(AliasType.SELL, sell);
        aliases.put(AliasType.PLANT, plant);
        aliases.put(AliasType.WATER, water);
        aliases.put(AliasType.HARVEST, harvest);
        aliases.put(AliasType.BIND, bind);
        aliases.put(AliasType.PREFIX, prefix);
    }
}
