package io.github.xerooup.frame3d.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.system.MemoryUtil;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Sound {
    private final String path;
    private int bufferId = 0;
    private int sourceId = 0;
    private boolean isLooping = false;
    private float individualVolume = 1.0f;

    public Sound(String path) {
        this.path = path;

        // ensure audio system is initialized
        if (!AudioManager.isInitialized()) {
            AudioManager.init();
        }

        loadWav();
        AudioManager.registerSound(this);
    }

    // loads wav file from resources and creates openal buffer and source
    private void loadWav() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream == null) {
                throw new RuntimeException("sound file not found: " + path);
            }

            // read all bytes into memory first
            byte[] audioBytes = inputStream.readAllBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            AudioFormat format = audioStream.getFormat();

            // check if format is pcm (required for OpenAL)
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED &&
                    format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED) {
                // try to convert to pcm format
                AudioFormat targetFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED,
                        format.getSampleRate(),
                        16,
                        format.getChannels(),
                        format.getChannels() * 2,
                        format.getSampleRate(),
                        false
                );
                AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, audioStream);
                loadConvertedWav(convertedStream);
                convertedStream.close();
            } else {
                loadConvertedWav(audioStream);
            }

            audioStream.close();
            byteArrayInputStream.close();
            inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException("failed to load audio: " + e.getMessage(), e);
        }
    }

    private void loadConvertedWav(AudioInputStream audioStream) throws Exception {
        AudioFormat format = audioStream.getFormat();

        // read all audio data
        byte[] buffer = new byte[4096];
        ByteArrayOutputStream data = new ByteArrayOutputStream();

        int bytesRead;
        while ((bytesRead = audioStream.read(buffer)) != -1) {
            data.write(buffer, 0, bytesRead);
        }

        byte[] audioData = data.toByteArray();
        ByteBuffer byteBuffer = MemoryUtil.memAlloc(audioData.length);
        byteBuffer.put(audioData);
        byteBuffer.flip();

        // determine OpenAL format based on audio properties
        int alFormat;
        if (format.getChannels() == 1 && format.getSampleSizeInBits() == 8) {
            alFormat = AL10.AL_FORMAT_MONO8;
        } else if (format.getChannels() == 1 && format.getSampleSizeInBits() == 16) {
            alFormat = AL10.AL_FORMAT_MONO16;
        } else if (format.getChannels() == 2 && format.getSampleSizeInBits() == 8) {
            alFormat = AL10.AL_FORMAT_STEREO8;
        } else if (format.getChannels() == 2 && format.getSampleSizeInBits() == 16) {
            alFormat = AL10.AL_FORMAT_STEREO16;
        } else {
            throw new RuntimeException("unsupported audio format: " + format.getChannels() + "ch " + format.getSampleSizeInBits() + "bit");
        }

        // create OpenAL buffer and upload data
        bufferId = AL10.alGenBuffers();
        AL10.alBufferData(bufferId, alFormat, byteBuffer, (int) format.getSampleRate());
        MemoryUtil.memFree(byteBuffer);

        // create OpenAL source and attach buffer
        sourceId = AL10.alGenSources();
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, bufferId);
        updateVolume();
    }

    // updates volume based on individual and master volume
    void updateVolume() {
        AL10.alSourcef(sourceId, AL10.AL_GAIN, individualVolume * AudioManager.getVolumeMultiplier());
    }

    // returns openal source id (internal use)
    int getSourceId() {
        return sourceId;
    }

    // plays the sound once
    public void play() {
        if (isLooping) {
            AL10.alSourcei(sourceId, AL10.AL_LOOPING, AL10.AL_FALSE);
            isLooping = false;
        }
        AL10.alSourcePlay(sourceId);
    }

    // plays the sound in loop
    public void loop() {
        AL10.alSourcei(sourceId, AL10.AL_LOOPING, AL10.AL_TRUE);
        isLooping = true;
        AL10.alSourcePlay(sourceId);
    }

    // stops playback
    public void stop() {
        AL10.alSourceStop(sourceId);
    }

    // pauses playback
    public void pause() {
        AL10.alSourcePause(sourceId);
    }

    // resumes paused playback
    public void resume() {
        if (AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PAUSED) {
            AL10.alSourcePlay(sourceId);
        }
    }

    // sets individual volume (0.0 to 1.0)
    public void setVolume(float volume) {
        individualVolume = Math.max(0f, Math.min(1f, volume));
        updateVolume();
    }

    // checks if sound is currently playing
    public boolean isPlaying() {
        return AL10.alGetSourcei(sourceId, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING;
    }

    // cleans up openal resources
    public void dispose() {
        stop();
        AudioManager.unregisterSound(this);
        AL10.alDeleteSources(sourceId);
        AL10.alDeleteBuffers(bufferId);
    }
}