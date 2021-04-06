package com.BAStudio.StopSellingThat;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EventHandlers {

    @SubscribeEvent
    public void RemoveTrades(VillagerTradesEvent event) {
        Logger logger = LogManager.getLogger(Consts.MODID);
        int sumTrade = 0;
        boolean modified = false;
        if (Config.SERVER.removeEnchantBooks.get() && event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> tradeLevels = event.getTrades();
            for (int i = 1; i < 6; i++) {
                List<VillagerTrades.ITrade> trades = tradeLevels.get(i);
                for (int t = trades.size() - 1; t > 0; t--) {
                    if (trades.get(t) instanceof VillagerTrades.EnchantedBookForEmeraldsTrade) {
                        modified = true;
                        trades.remove(t);
                        sumTrade++;
                    }
                }
            }
        }

        if (modified)
        {
            logger.log(Level.INFO, String.format("Removed trades: %d", sumTrade));
            System.out.println(String.format("Removed trades: %d", sumTrade));
        }
    }
}
