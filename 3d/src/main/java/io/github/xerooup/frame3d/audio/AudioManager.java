package io.github.xerooup.frame3d.audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.AL10;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class AudioManager {
    private static long device = 0;
    private static long context = 0;
    private static boolean initialized = false;
    private static final List<Sound> sounds = new ArrayList<>();

    private static float masterVolume = 1.0f;

    private AudioManager() {} // prevent instantiation

    // initializes openal audio system
    public static void init() {
        if (initialized) return;

        // open default audio device
        device = ALC10.alcOpenDevice((ByteBuffer) null);
        if (device == 0L) {
            throw new IllegalStateException("failed to open OpenAL device");
        }

        // create audio context
        context = ALC10.alcCreateContext(device, (IntBuffer) null);
        if (context == 0L) {
            ALC10.alcCloseDevice(device);
            throw new IllegalStateException("failed to create OpenAL context");
        }

        if (!ALC10.alcMakeContextCurrent(context)) {
            ALC10.alcDestroyContext(context);
            ALC10.alcCloseDevice(device);
            throw new IllegalStateException("failed to make OpenAL context current");
        }

        // create openal capabilities
        AL.createCapabilities(ALC.createCapabilities(device));
        initialized = true;
    }

    // gets master volume (0.0 to 1.0)
    public static float getMasterVolume() {
        return masterVolume;
    }

    // sets master volume (0.0 to 1.0)
    public static void setMasterVolume(float volume) {
        masterVolume = Math.max(0f, Math.min(1f, volume));
        // update volume for all registered sounds
        for (Sound sound : sounds) {
            sound.updateVolume();
        }
    }

    // registers a sound for global management
    static void registerSound(Sound sound) {
        sounds.add(sound);
    }

    // unregisters a sound
    static void unregisterSound(Sound sound) {
        sounds.remove(sound);
    }

    // stops all playing sounds
    public static void stopAll() {
        for (Sound sound : sounds) {
            sound.stop();
        }
    }

    // pauses all playing sounds
    public static void pauseAll() {
        for (Sound sound : sounds) {
            if (sound.isPlaying()) {
                AL10.alSourcePause(sound.getSourceId());
            }
        }
    }

    // resumes all paused sounds
    public static void resumeAll() {
        for (Sound sound : sounds) {
            int state = AL10.alGetSourcei(sound.getSourceId(), AL10.AL_SOURCE_STATE);
            if (state == AL10.AL_PAUSED) {
                AL10.alSourcePlay(sound.getSourceId());
            }
        }
    }

    // cleans up all audio resources
    public static void dispose() {
        if (!initialized) return;

        stopAll();
        sounds.clear();

        if (context != 0L) {
            ALC10.alcMakeContextCurrent(0L);
            ALC10.alcDestroyContext(context);
            context = 0L;
        }

        if (device != 0L) {
            ALC10.alcCloseDevice(device);
            device = 0L;
        }

        initialized = false;
    }

    // returns current master volume multiplier
    static float getVolumeMultiplier() {
        return masterVolume;
    }

    // checks if audio system is initialized
    public static boolean isInitialized() {
        return initialized;
    }
}