package novafenixproject.fractalstellaris.client.datagen.blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import novafenixproject.fractalstellaris.common.Global;
import novafenixproject.fractalstellaris.common.blocks.*;

public class FractalModelProvider extends FabricModelProvider {
    public FractalModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        //blocos
        for(FractalBlocks fractalBlock:FractalBlocks.values()){
            FractalBlockInterface block = fractalBlock.getBlock();
            ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(Global.NAMESPACE,"block/"+block.getBlockName());
            blockStateModelGenerator.blockStateOutput.accept(
                    BlockModelGenerators.createSimpleBlock(
                            block.getBlock(),resourceLocation
                    )
            );
            blockStateModelGenerator.delegateItemModel(block.getBlock(),resourceLocation);

        }
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
