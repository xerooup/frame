package io.github.xerooup.frame3d.graphics.shader;

public class DefaultShader {
    public static final String VERTEX_SHADER = """
            #version 330 core
            
            layout(location = 0) in vec3 position;
            layout(location = 1) in vec3 color;
            layout(location = 2) in vec3 normalIn;
            layout(location = 3) in vec2 texCoord;
            
            out vec3 fragColor;
            out vec3 fragPos;
            out vec3 normal;
            out vec2 fragTexCoord;
            
            uniform mat4 model;
            uniform mat4 view;
            uniform mat4 projection;
            
            void main() {
                fragPos = vec3(model * vec4(position, 1.0));
                normal = mat3(transpose(inverse(model))) * normalIn;
                fragColor = color;
                fragTexCoord = texCoord;
                gl_Position = projection * view * vec4(fragPos, 1.0);
            }
            """;

    public static final String FRAGMENT_SHADER = """
            #version 330 core
            
            in vec3 fragColor;
            in vec3 fragPos;
            in vec3 normal;
            in vec2 fragTexCoord;
            
            out vec4 outColor;
            
            uniform vec3 lightDir = vec3(-0.3, -0.8, -0.5);
            uniform vec3 lightColor = vec3(1.0, 1.0, 1.0);
            uniform float ambientStrength = 0.4;
            uniform vec3 viewPos = vec3(0.0, 0.0, 5.0);
            
            uniform bool useTexture = false;
            uniform sampler2D textureSampler;
            
            void main() {
                // Base color
                vec3 baseColor;
                if (useTexture) {
                    baseColor = texture(textureSampler, fragTexCoord).rgb;
                } else {
                    baseColor = fragColor;
                }
                
                // Ambient lighting (softer)
                vec3 ambient = ambientStrength * lightColor * baseColor;
                
                // Diffuse lighting (smoother)
                vec3 norm = normalize(normal);
                vec3 lightDirection = normalize(-lightDir);
                float diff = max(dot(norm, lightDirection), 0.0);
                
                // Softer diffuse falloff
                diff = pow(diff, 0.8);
                vec3 diffuse = diff * lightColor * baseColor;
                
                // Specular highlights (like Blender)
                vec3 viewDir = normalize(viewPos - fragPos);
                vec3 reflectDir = reflect(-lightDirection, norm);
                float spec = pow(max(dot(viewDir, reflectDir), 0.0), 32.0);
                vec3 specular = spec * lightColor * 0.3;
                
                // Rim lighting (subtle edge glow)
                float rimIntensity = 1.0 - max(dot(viewDir, norm), 0.0);
                rimIntensity = pow(rimIntensity, 3.0);
                vec3 rim = rimIntensity * lightColor * baseColor * 0.2;
                
                // Combine all lighting
                vec3 result = ambient + diffuse + specular + rim;
                
                // Slight color grading (warmer tones)
                result = pow(result, vec3(0.95));
                
                outColor = vec4(result, 1.0);
            }
            """;
}