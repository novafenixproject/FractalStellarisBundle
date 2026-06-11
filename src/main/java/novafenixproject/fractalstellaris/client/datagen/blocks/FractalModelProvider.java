package novafenixproject.fractalstellaris.client.datagen.blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import novafenixproject.fractalstellaris.common.Global;
import novafenixproject.fractalstellaris.common.blocks.*;

import java.util.function.Consumer;

public class FractalModelProvider extends FabricModelProvider {
    public FractalModelProvider(FabricDataOutput output) {
        super(output);
    }


    private void forBlocks(FractalBlockInterface blockInterface,BlockModelGenerators blockStateModelGenerator){
        Block block = blockInterface.getBlock();
        if(block == null){
            return;
        }
        ResourceLocation blockResourceLocation = ResourceLocation.fromNamespaceAndPath(
                Global.NAMESPACE,
                "block/"+blockInterface.getId()
        );
        ResourceLocation itemResourceLocation = ResourceLocation.fromNamespaceAndPath(
                Global.NAMESPACE,
                "item/" + blockInterface.getId()
        );

        blockStateModelGenerator.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        blockResourceLocation
                )
        );

        blockStateModelGenerator.modelOutput.accept(
                itemResourceLocation,
                new DelegatedModel(blockResourceLocation)
        );
        blockStateModelGenerator.skipAutoItemBlock(block);
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

        //blocos
        for(FractalBlocks fractalBlock:FractalBlocks.values()){
            FractalBlockInterface blockInterface = fractalBlock.getBlock();
            this.forBlocks(blockInterface,blockStateModelGenerator);
        }
        for(FractalOres fractalOre:FractalOres.values()){
            FractalBlockInterface blockInterface = fractalOre.BLOCK;
            this.forBlocks(blockInterface,blockStateModelGenerator);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        //varrendo todos os items
        /*for(FractalItems fractalItem:FractalItems.values()){
            Item item = fractalItem.getItem();
            //verificando se nao é um blockItem
            if(!(item instanceof BlockItem)){
                //verifica se nao é nulo e se nao é ar
                if(item != null && item != Items.AIR){
                    //gera o flat item
                    itemModelGenerator.generateFlatItem(fractalItem.getItem(), ModelTemplates.FLAT_ITEM);
                }
            }
        }*/
    }
}
