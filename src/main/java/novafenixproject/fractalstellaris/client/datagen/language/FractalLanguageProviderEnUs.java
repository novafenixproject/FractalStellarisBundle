package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import novafenixproject.fractalstellaris.common.blocks.FractalBlocks;
import novafenixproject.fractalstellaris.common.blocks.FractalCreativeTabs;
import novafenixproject.fractalstellaris.common.blocks.FractalItems;
import novafenixproject.fractalstellaris.common.blocks.FractalOres;

import java.util.concurrent.CompletableFuture;

public class FractalLanguageProviderEnUs extends FractalLanguageProvider{
    public FractalLanguageProviderEnUs(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput,"en_us", registryLookup);

        //BLOCOS
        this.BLOCK.register(FractalBlocks.STELLARITE.getId(),"Stellarite Block");
        this.BLOCK.register(FractalBlocks.STELLARITE_POLISHED.getId(), "Stellarite Polished Block");
        this.BLOCK.register(FractalBlocks.GALVANIZED_ROCK.getId(), "Galvanized Rock");

        //MINERIOS
        this.BLOCK.register(FractalOres.STELLARITE.getId(),"Stellarite Ore");

        //ITEMS
        this.ITEM.register(FractalItems.STELLARITE_BLOCK.getId(),"Stellarite Block");
        this.ITEM.register(FractalItems.STELLARITE_POLISHED_BLOCK.getId(), "Stellarite Polished Block");
        this.ITEM.register(FractalItems.GALVANIZED_ROCK_BLOCK.getId(), "Galvanized Rock");

        //MINERIOS
        this.ITEM.register(FractalItems.STELLARITE_ORE.getId(), "Stellarite Ore");
        this.ITEM.register(FractalItems.RAW_STELLARITE.getId(), "Raw Stellarite");

        //ABA CRIATIVO
        this.CREATIVE_MODETAB.register(FractalCreativeTabs.NATURE_TAB.getId(),"Nature FractalStellaris");
    }
}