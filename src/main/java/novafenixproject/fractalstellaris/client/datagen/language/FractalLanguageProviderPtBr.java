package novafenixproject.fractalstellaris.client.datagen.language;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import novafenixproject.fractalstellaris.common.blocks.FractalBlocks;
import novafenixproject.fractalstellaris.common.blocks.FractalCreativeTabs;
import novafenixproject.fractalstellaris.common.blocks.FractalItems;
import novafenixproject.fractalstellaris.common.blocks.FractalOres;

import java.util.concurrent.CompletableFuture;

public class FractalLanguageProviderPtBr extends FractalLanguageProvider{
    public FractalLanguageProviderPtBr(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput,"pt_br", registryLookup);

        //BLOCOS
        this.BLOCK.register(FractalBlocks.STELLARITE.getId(),"Bloco de Stellarite");
        this.BLOCK.register(FractalBlocks.STELLARITE_POLISHED.getId(), "Bloco de Stellarite Polido");
        this.BLOCK.register(FractalBlocks.GALVANIZED_ROCK.getId(), "Rocha Galvanizada");

        //MINERIOS
        this.BLOCK.register(FractalOres.STELLARITE.getId(),"Minério de Stellarite");

        //ITEMS
        this.ITEM.register(FractalItems.STELLARITE_BLOCK.getId(),"Bloco de Stellarite");
        this.ITEM.register(FractalItems.STELLARITE_POLISHED_BLOCK.getId(), "Bloco de Stellarite Polido");
        this.ITEM.register(FractalItems.GALVANIZED_ROCK_BLOCK.getId(), "Rocha Galvanizada");

        //MINERIOS
        this.ITEM.register(FractalItems.STELLARITE_ORE.getId(), "Minério de Stellarite");
        this.ITEM.register(FractalItems.RAW_STELLARITE.getId(), "Stellarite Bruto");

        //ABAS DO CRIATIVO
        this.CREATIVE_MODETAB.register(FractalCreativeTabs.NATURE_TAB.getId(),"Natureza FractalStellaris");
    }
}
