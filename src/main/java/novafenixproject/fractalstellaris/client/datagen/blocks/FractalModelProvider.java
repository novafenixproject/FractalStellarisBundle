package novafenixproject.fractalstellaris.client.datagen.blocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;

import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.VariantProperty;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;

import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.level.block.Block;
import novafenixproject.fractalstellaris.api.Global;
import novafenixproject.fractalstellaris.api.blocks.FractalBlocks;
import novafenixproject.fractalstellaris.api.blocks.MappingBlockModelInterface;
import novafenixproject.fractalstellaris.api.blocks.sapling.FractalSaplingType;
import novafenixproject.fractalstellaris.api.blocks.wood.FractalWoodType;

public class FractalModelProvider extends FabricModelProvider{
    public FractalModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void putMapping(TextureMapping textureMapping,TextureSlot textureSlot,String textureName) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(Global.NAMESPACE,"block/" + textureName);
        textureMapping.put(textureSlot, resourceLocation);
    }
    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for(FractalBlocks fractalBlocks: FractalBlocks.values()) {
            Block block = fractalBlocks.BLOCK.getBlock();
            MappingBlockModelInterface model = fractalBlocks.BLOCK.getModel();
            if(model == null)continue;
            String textureAll    = model.getTexture(TextureSlot.ALL);
            String textureSide   = model.getTexture(TextureSlot.SIDE);
            String textureTop    = model.getTexture(TextureSlot.TOP);
            String textureBottom = model.getTexture(TextureSlot.BOTTOM);
            String textureEnd    = model.getTexture(TextureSlot.END);

            TextureMapping textureMapping = new TextureMapping();
            //modelo do bloco
            ResourceLocation blockId = null;

            boolean delegatedItem = true;

            if(block instanceof FractalSaplingType){
                textureMapping = TextureMapping.cross(block);
                blockId = ModelTemplates.CROSS.create(block,textureMapping,blockStateModelGenerator.modelOutput);
                blockStateModelGenerator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block,blockId));
                delegatedItem = false;

            }else if(block instanceof FractalWoodType){
                putMapping(textureMapping,TextureSlot.END,textureEnd);
                putMapping(textureMapping,TextureSlot.SIDE,textureSide);
                blockId = ModelTemplates.CUBE_COLUMN.create(block,textureMapping,blockStateModelGenerator.modelOutput);
                blockStateModelGenerator.blockStateOutput.accept(
                        MultiVariantGenerator.multiVariant(block, Variant.variant().with(VariantProperties.MODEL,blockId))
                                .with(BlockModelGenerators.createRotatedPillar())
                );
            }else if(textureAll != null){
                blockStateModelGenerator.createTrivialCube(block);
                putMapping(textureMapping,TextureSlot.ALL, textureAll);
                blockId = ModelLocationUtils.getModelLocation(block);

            }else if(textureSide != null && textureTop != null && textureBottom != null){
                textureMapping = new TextureMapping();
                putMapping(textureMapping,TextureSlot.TOP,textureTop);
                putMapping(textureMapping,TextureSlot.SIDE,textureSide);
                putMapping(textureMapping,TextureSlot.BOTTOM,textureBottom);
                blockId = ModelTemplates.CUBE_BOTTOM_TOP.create(
                        block,textureMapping,blockStateModelGenerator.modelOutput
                );
                blockStateModelGenerator.blockStateOutput.accept(
                        BlockModelGenerators.createSimpleBlock(block,blockId)
                );
            }
            if(delegatedItem){
                blockStateModelGenerator.delegateItemModel(block, blockId);
            }else{
                ModelTemplates.FLAT_ITEM.create(
                        ModelLocationUtils.getModelLocation(block.asItem()),
                        TextureMapping.layer0(block),
                        blockStateModelGenerator.modelOutput
                );
            }
        }
    }
    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {

    }
}
