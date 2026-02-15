package io.github.xerooup.frame3d.graphics.mesh;

public interface Mesh {
    float[] getVertices();
    int[] getIndices();
    void bind();
    void unbind();
    void render();
    void cleanup();
}