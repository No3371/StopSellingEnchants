package com.BAStudio.StopSellingEnchants;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;

import static net.minecraftforge.fml.loading.LogMarkers.FORGEMOD;

public class Config {

    public static class Server {
        public final ForgeConfigSpec.BooleanValue removeEnchantBooks;

        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Server configuration settings")
                    .push("server");

            removeEnchantBooks = builder
                    .comment("")
                    .worldRestart()
                    .define("removeEnchantBooks", true);

            builder.pop();
        }
    }

    static final ForgeConfigSpec serverSpec;
    public static final Server SERVER;
    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
        LogManager.getLogger().debug(FORGEMOD, "Loaded config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfig.Reloading configEvent) {
        LogManager.getLogger().debug(FORGEMOD, "Config just got changed on the file system!");
    }

    //General
    //public static boolean disableVersionCheck = false;
    //public static boolean logCascadingWorldGeneration = true; // see Chunk#logCascadingWorldGeneration()
    //public static boolean fixVanillaCascading = false;
}