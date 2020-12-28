package net.plasmere.objects;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Player {
    private HashMap<String, String> info = new HashMap<>();
    private final String filePrePath = System.getProperty("user.dir") + File.separator + "players" + File.separator;

    public File file;
    public long id;
    public int lvl;
    public int xp;
    public int bal;
    public int land;
    public int wheat_seeds;
    public int rice_seeds;
    public int cans;
    public int wheat_crop;
    public int rice_crop;
    public int wheat_planted;
    public int rice_planted;
    public boolean harvest_ready;

    public Player(long id){
        this.id = id;
        this.file = new File(filePrePath + this.id + ".properties");

        System.out.println("Player file: " + file.getAbsolutePath());

        try {
            getFromConfigFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getInfo() {
        return info;
    }
    public void remKey(String key){
        info.remove(key);
    }
    public String getFromKey(String key){
        return info.get(key);
    }
    public void updateKey(String key, Object value) {
        info.put(key, String.valueOf(value));
        loadVars();
    }
    public File getFile() { return file; }

    public boolean hasProperty(String property) {
        for (String info : getInfoAsPropertyList()) {
            if (info.startsWith(property)) return true;
        }

        return false;
    }

    public List<String> getInfoAsPropertyList() {
        List<String> infoList = new ArrayList<>();
        for (String key : info.keySet()){
            infoList.add(key + "=" + getFromKey(key));
        }

        return infoList;
    }

    public String getFullProperty(String key) throws Exception {
        if (hasProperty(key)) {
            return key + "=" + getFromKey(key);
        } else {
            throw new Exception("No property saved!");
        }
    }

    public void flushInfo(){
        this.info = new HashMap<>();
    }

    public void addKeyValuePair(String key, String value){
        info.put(key, value);
    }

    public void getFromConfigFile() throws IOException {
        if (file.exists()){
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                while (data.startsWith("#")) {
                    data = reader.nextLine();
                }
                String[] dataSplit = data.split("=", 2);
                addKeyValuePair(dataSplit[0], dataSplit[1]);
            }

            reader.close();

            if (needUpdate()) {
                updateWithNewDefaults();
            }

            loadVars();
        } else {
            updateWithNewDefaults();
        }
    }

    public boolean needUpdate() {
        if (info.size() != propertiesDefaults().size()) return true;

        int i = 0;
        for (String p : getInfoAsPropertyList()) {
            if (! p.startsWith(propertiesDefaults().get(i).split("=", 2)[0])) return true;
            i++;
        }

        return false;
    }

    public void updateWithNewDefaults() throws IOException {
        file.delete();
        FileWriter writer = new FileWriter(file);

        for (String p : propertiesDefaults()) {
            String[] propSplit = p.split("=", 2);

            String property = propSplit[0];

            String write = "";
            try {
                write = getFullProperty(property);
            } catch (Exception e) {
                write = p;
            }

            writer.write(write + "\n");
        }

        writer.close();

        flushInfo();

        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            while (data.startsWith("#")) {
                data = reader.nextLine();
            }
            String[] dataSplit = data.split("=", 2);
            addKeyValuePair(dataSplit[0], dataSplit[1]);
        }

        reader.close();

        loadVars();
    }

    public void loadVars() {
        this.lvl = Integer.parseInt(getFromKey("lvl"));
        this.xp = Integer.parseInt(getFromKey("xp"));
        this.bal = Integer.parseInt(getFromKey("bal"));
        this.land = Integer.parseInt(getFromKey("land"));
        this.wheat_seeds = Integer.parseInt(getFromKey("wheat_seeds"));
        this.rice_seeds = Integer.parseInt(getFromKey("rice_seeds"));
        this.cans = Integer.parseInt(getFromKey("cans"));
        this.wheat_crop = Integer.parseInt(getFromKey("wheat_crop"));
        this.rice_crop = Integer.parseInt(getFromKey("rice_crop"));
        this.wheat_planted = Integer.parseInt(getFromKey("wheat_planted"));
        this.rice_planted = Integer.parseInt(getFromKey("rice_planted"));
        this.harvest_ready = Boolean.parseBoolean(getFromKey("harvest_ready"));
    }

    public List<String> propertiesDefaults() {
        List<String> defaults = new ArrayList<>();
        defaults.add("lvl=1");
        defaults.add("xp=0");
        defaults.add("bal=1300");
        defaults.add("land=1");
        defaults.add("wheat_seeds=0");
        defaults.add("rice_seeds=0");
        defaults.add("cans=0");
        defaults.add("wheat_crop=0");
        defaults.add("rice_crop=0");
        defaults.add("wheat_planted=0");
        defaults.add("rice_planted=0");
        defaults.add("harvest_ready=false");
        //defaults.add("");
        return defaults;
    }

    public void saveInfo() throws IOException {
        file.delete();

        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for (String s : getInfoAsPropertyList()){
            writer.write(s + "\n");
        }
        writer.close();
    }

    /*
    Experience required =
    2 × current_level + 7 (for levels 0–15)
    5 × current_level – 38 (for levels 16–30)
    9 × current_level – 158 (for levels 31+)
     */
    public int getNeededXp(){
        int needed = 0;
        if (this.lvl <= 15){
            needed = 2 * this.lvl + 7;
        } else if (this.lvl >= 16 && this.lvl <= 30){
            needed = 5 * this.lvl - 38;
        } else if (this.lvl > 30) {
            needed = 9 * this.lvl - 158;
        } else {
            needed = 100;
        }

        return needed;
    }

    public void addXp(int amount){
        int needed = getNeededXp();
        int setAmount = this.xp + amount;

        if (setAmount >= needed) {
            setAmount -= needed;
            int setLevel = this.lvl + 1;
            updateKey("lvl", setLevel);
        }

        updateKey("xp", setAmount);
    }

    public void setXp(int amount){
        int needed = getNeededXp();
        int setAmount = amount;

        if (setAmount >= needed) {
            setAmount -= needed;
            int setLevel = this.lvl + 1;
            updateKey("lvl", setLevel);
        }

        updateKey("xp", setAmount);
    }
}
