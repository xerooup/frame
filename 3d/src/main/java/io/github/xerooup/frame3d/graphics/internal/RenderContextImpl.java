package io.github.xerooup.frame3d.graphics.internal;

import io.github.xerooup.frame3d.graphics.RenderContext;
import io.github.xerooup.frame3d.graphics.material.Material;
import io.github.xerooup.frame3d.graphics.material.MaterialType;
import io.github.xerooup.frame3d.graphics.mesh.Mesh;
import io.github.xerooup.frame3d.graphics.shader.DefaultShader;
import io.github.xerooup.frame3d.graphics.shader.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class RenderContextImpl implements RenderContext {
    private final int width;
    private final int height;

    private final Matrix4f projection;
    private final Matrix4f view;
    private final Matrix4f model;

    private final Shader defaultShader;
    private Shader currentShader;


    public RenderContextImpl(int width, int height) {
        this.width = width;
        this.height = height;

        this.projection = new Matrix4f().perspective(
                (float)Math.toRadians(45.0),
                (float)width / height,
                0.1f,
                100f
        );

        this.view = new Matrix4f().identity()
                .translate(0, 0, -5);

        this.model = new Matrix4f().identity();

        this.defaultShader = new Shader(DefaultShader.VERTEX_SHADER, DefaultShader.FRAGMENT_SHADER);
        this.currentShader = defaultShader;
    }

    @Override
    public void setShader(Shader shader) {
        this.currentShader = shader;
    }

    @Override
    public void resetShader() {
        this.currentShader = defaultShader;
    }

    @Override
    public Shader getShader() {
        return currentShader;
    }

    @Override
    public void cube(Vector3f position, float size) {
        // TODO
    }

    @Override
    public void mesh(Mesh mesh, Vector3f position) {
        mesh(mesh, position, new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));
    }

    @Override
    public void mesh(Mesh mesh, Vector3f position, Vector3f rotation) {
        mesh(mesh, position, rotation, new Vector3f(1, 1, 1));
    }

    @Override
    public void mesh(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale) {
        currentShader.bind();

        model.identity()
                .translate(position)
                .rotateY((float)Math.toRadians(rotation.y))
                .rotateX((float)Math.toRadians(rotation.x))
                .rotateZ((float)Math.toRadians(rotation.z))
                .scale(scale);

        currentShader.setUniform("model", model);
        currentShader.setUniform("view", view);
        currentShader.setUniform("projection", projection);

        mesh.render();

        currentShader.unbind();
    }

    @Override
    public void mesh(Mesh mesh, Material material, Vector3f position, Vector3f rotation, Vector3f scale) {
        currentShader.bind();

        model.identity()
                .translate(position)
                .rotateY((float)Math.toRadians(rotation.y))
                .rotateX((float)Math.toRadians(rotation.x))
                .rotateZ((float)Math.toRadians(rotation.z))
                .scale(scale);

        currentShader.setUniform("model", model);
        currentShader.setUniform("view", view);
        currentShader.setUniform("projection", projection);

        // Texture or color
        if (material.type == MaterialType.TEXTURE) {
            currentShader.setUniform("useTexture", true);
            currentShader.setUniform("textureSampler", 0);
            material.getTexture().bind();
        } else {
            currentShader.setUniform("useTexture", false);
        }

        mesh.render();

        if (material.type == MaterialType.TEXTURE) {
            material.getTexture().unbind();
        }

        currentShader.unbind();
    }

    public void cleanup() {
        currentShader.cleanup();
    }
}