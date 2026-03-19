package novafenixproject.fractalstellaris.client.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

import novafenixproject.fractalstellaris.client.datagen.blocks.FractalModelProvider;
import novafenixproject.fractalstellaris.client.datagen.language.FractalLanguageProvider_en_us;
import novafenixproject.fractalstellaris.client.datagen.language.FractalLanguageProvider_pt_br;


public class FractalDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(FractalModelProvider::new);
        pack.addProvider(FractalLanguageProvider_pt_br::new);
        pack.addProvider(FractalLanguageProvider_en_us::new);

    }
}
