package io.github.xerooup.frame3d.graphics;

import io.github.xerooup.frame3d.graphics.mesh.Mesh;
import io.github.xerooup.frame3d.graphics.shader.Shader;
import org.joml.Vector3f;

public interface RenderContext {
    void cube(Vector3f position, float size);
    void mesh(Mesh mesh, Vector3f position);
    void mesh(Mesh mesh, Vector3f position, Vector3f rotation);
    void mesh(Mesh mesh, Vector3f position, Vector3f rotation, Vector3f scale);

    void setShader(Shader shader);
    void resetShader();
    Shader getShader();
}