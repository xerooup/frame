package io.github.xerooup.frame3d.graphics.shader;

public class DefaultShader {
    public static final String VERTEX_SHADER = """
        #version 330 core
        
        layout(location = 0) in vec3 position;
        layout(location = 1) in vec3 color;
        layout(location = 2) in vec3 normalIn;
        
        out vec3 fragColor; 
        out vec3 fragPos;
        out vec3 normal;     
        
        uniform mat4 model;
        uniform mat4 view;
        uniform mat4 projection;
        
        void main() {
            fragPos = vec3(model * vec4(position, 1.0));
            normal = mat3(transpose(inverse(model))) * normalIn;
            fragColor = color;
            gl_Position = projection * view * vec4(fragPos, 1.0);
        }
        """;

    public static final String FRAGMENT_SHADER = """
        #version 330 core
        
        in vec3 fragColor; 
        in vec3 fragPos;
        in vec3 normal;    
        
        out vec4 outColor;
        
        uniform vec3 lightDir = vec3(-0.3, -0.5, -0.8);
        uniform vec3 lightColor = vec3(1.0, 1.0, 1.0);
        uniform float ambientStrength = 0.3;
        
        void main() {
            vec3 ambient = ambientStrength * lightColor;
            
            vec3 norm = normalize(normal);
            vec3 lightDirection = normalize(-lightDir);
            float diff = max(dot(norm, lightDirection), 0.0);
            vec3 diffuse = diff * lightColor;
            
            vec3 result = (ambient + diffuse) * fragColor;
            outColor = vec4(result, 1.0);
        }
        """;
}