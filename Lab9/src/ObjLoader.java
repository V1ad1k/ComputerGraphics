import java.io.*;
import java.util.*;

public class ObjLoader {

    private static ArrayList<Vector3> vertices = new ArrayList<>();
    private static Material currentMaterial = new Material();


    public static Vector3 parseVertex(String[] data) {
        return new Vector3(Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }

    public static Vector3 parseNormal(String[] data) {
        return new Vector3(Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }

    public static Vector3 parseTextureCoordinate(String[] data) {
        return new Vector3(Double.parseDouble(data[1]), Double.parseDouble(data[2]), 0);
    }

    public static Vertex parseTriangleVertex(String[] data, ArrayList<Vector3> vertices, ArrayList<Vector3> textureCoordinates, ArrayList<Vector3> normals) {
        Vector3 vertex = null;
        if (!data[0].isEmpty()) {
            int vertexIndex = Integer.parseInt(data[0]) - 1;
            vertex = vertices.get(vertexIndex);
        }

        Vector3 textureCoordinate = null;
        if (!data[1].isEmpty()) {
            int textureCoordinateIndex = Integer.parseInt(data[1]) - 1;
            textureCoordinate = textureCoordinates.get(textureCoordinateIndex);
        }

        Vector3 normal = null;
        if (!data[2].isEmpty()) {
            int normalIndex = Integer.parseInt(data[2]) - 1;
            normal = normals.get(normalIndex);
        }

        return new Vertex(vertex, textureCoordinate, normal);
    }

    public static Triangle parseFace(String[] data, ArrayList<Vector3> vertices, ArrayList<Vector3> textureCoordinates, ArrayList<Vector3> normals, Material material) {
        return new Triangle(
                parseTriangleVertex(data[1].split("/"), vertices, textureCoordinates, normals),
                parseTriangleVertex(data[2].split("/"), vertices, textureCoordinates, normals),
                parseTriangleVertex(data[3].split("/"), vertices, textureCoordinates, normals),
                material
        );
    }

    public static Triangle[] parseFile(File file) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(file));

        ArrayList<Vector3> textureCoordinates = new ArrayList<>();
        ArrayList<Vector3> normals = new ArrayList<>();
        ArrayList<Triangle> faces = new ArrayList<>();

        HashMap<String, Material> materials = new HashMap<>();
        Material currentMaterial = new Material();

        String line;
        while ((line = input.readLine()) != null) {
            String[] data = line.split(" ");
            switch (data[0]) {
                case "mtllib":
                    // Read material file from disk and parse it
                    File parentDirectory = file.getParentFile();
                    File[] files = parentDirectory.listFiles(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String name) {
                            return name.equals(data[1]);
                        }
                    });
                    if (files.length >= 1) {
                        File materialFile = files[0];
                        HashMap<String, Material> newMaterials = parseMaterialFile(materialFile);
                        materials.putAll(newMaterials);
                    }
                    break;
                case "usemtl":
                    currentMaterial = materials.get(data[1]);
                    break;
                case "v":
                    vertices.add(parseVertex(data));
                    break;
                case "vn":
                    normals.add(parseNormal(data));
                    break;
                case "vt":
                    textureCoordinates.add(parseTextureCoordinate(data));
                    break;
                case "f":
                    faces.add(parseFace(data, vertices, textureCoordinates, normals, currentMaterial));
                    break;
            }
        }
        input.close();

        Triangle[] facesArray = new Triangle[faces.size()];
        facesArray = faces.toArray(facesArray);
        return facesArray;
    }

    public static Vector3 parseColor(String[] data) {
        return new Vector3(Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]));
    }
    public static ArrayList<Vector3> getVertices(){
        return vertices;
    }

    public static HashMap<String, Material> parseMaterialFile(File file) throws Exception {
        HashMap<String, Material> materials = new HashMap<>();


        BufferedReader input = new BufferedReader(new FileReader(file));
        String line;
        while ((line = input.readLine()) != null) {
            String[] data = line.split(" ");
            switch (data[0]) {
                case "newmtl":
                    currentMaterial = new Material();
                    materials.put(data[1], currentMaterial);
                    break;
                case "Ka":
                    currentMaterial.ambientColor = parseColor(data);
                    break;
                case "Kd":
                    currentMaterial.diffuseColor = parseColor(data);
                    break;
                case "Ks":
                    currentMaterial.specularColor = parseColor(data);
                    break;
                case "Ns":
                    currentMaterial.specularExponent = Double.parseDouble(data[1]);
                    break;
                case "d":
                    currentMaterial.transparency = Double.parseDouble(data[1]);
                    break;
                case "illum":
                    currentMaterial.illuminationModel = Integer.parseInt(data[1]);
                    break;
            }
        }
        input.close();

        return materials;
    }

    public static Sphere vertices() {
        return vertices();
    }
    public Color getPointColor(Scene scene, Intersection intersection) {

        //unit surface normal vector
        Vector n = intersection.getNormal();

        //observer vector
        Vector o = new Vector(scene.getObserver().getViewPoint());
        o.subtract(intersection.getLocation());
        o.normalize();

        Color result = new Color();
        for (Light light : scene.getLights()) {

            //unit vector to i-th light
            Vector i = new Vector(light.getPosition());
            i.subtract(intersection.getLocation());
            //distance from light position to sphere point.
            double r = i.length();
            i.normalize();

            //(OS)
            Vector os = new Vector(i);
            os.add(o);
            os.normalize();

            double dotni = Math.max(0, n.dot(i));
            double dotos = Math.pow(Math.max(0, n.dot(os)), g);

            Color li = light.getIntensity();

            double attenuation = attenuation(r);
            result.addR(attenuation * li.getR() * (kdr * dotni + ksr * dotos));
            result.addG(attenuation * li.getG() * (kdg * dotni + ksg * dotos));
            result.addB(attenuation * li.getB() * (kdb * dotni + ksb * dotos));

        }

        //append ambient
        result.addR(a.getR() * kac);
        result.addG(a.getG() * kac);
        result.addB(a.getB() * kac);
        return result;
    }
    public static Phong getPhong(){
        return currentMaterial;
    }
}