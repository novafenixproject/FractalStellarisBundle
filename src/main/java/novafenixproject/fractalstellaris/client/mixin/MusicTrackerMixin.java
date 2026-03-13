package novafenixproject.fractalstellaris.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import novafenixproject.fractalstellaris.api.FractalBiomes;
import novafenixproject.fractalstellaris.api.FractalMusics;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MusicManager.class)
public abstract class MusicTrackerMixin {
    @Final @Shadow private Minecraft minecraft;
    @Final @Shadow private RandomSource random;
    @Shadow private SoundInstance currentMusic;
    @Shadow private int nextSongDelay;

    @Unique private float customVolume = 1.0F;
    @Unique private long lastTime = 0;

    @Unique
    public Music playMusic(FractalMusics fractalMusics, int minDelay, int maxDelay, boolean replaceCurrentMusic) {
        Holder<SoundEvent> sound = Holder.direct(fractalMusics.getHolder().value());
        return new Music(sound,minDelay,maxDelay,replaceCurrentMusic);
    }
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void onTick(CallbackInfo info) {
        //verificando se o jogador nao esta na dimensao
        if (!isCorrectBiome()) {
            //deixa que o vanila pega o controle
            currentMusic = null;
            return;
        }
        //o jogador esta na dimensao fractalstellaris
        //se o jogador acabou de entrar na dimensao o valor é nulo ainda
        if(currentMusic == null) {
            //musica de entrada na dimensao
            Holder<Biome> currentBiome = getCurrentBiome();
            if(currentBiome != null) {
                playMusic(FractalMusics.BIOME_FRACTAL_CENTRAL_ZONE,1,2,true);
            }
        }

        info.cancel();
    }
    @Unique
    private Holder<Biome> getCurrentBiome() {
        LocalPlayer player = minecraft.player;
        if(player == null) {
            return null;
        }
        if(minecraft.level == null) {
            return null;
        }
        return minecraft.level.getBiome(player.blockPosition());
    }
    @Unique
    private boolean isCorrectBiome() {
        if (minecraft.level == null || minecraft.player == null) return false;
        Holder<Biome> biome = getCurrentBiome();
        return FractalBiomes.contains(biome);
    }

    @Shadow public abstract void startPlaying(Music type);
}