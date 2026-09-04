# Raytracer (Java Grund, Laboration 2)

En ganska enkel raytracer i Java som skickar strålar från en fast kamera in i en 3D-scen, beräknar träffar mot
geometriska objekt, och renderar resultatet till en bildfil (PNG-format).

Byggd för att uppfylla Open/Closed Principle: nya former och material kan läggas till utan att ändra befintlig logik i
`Scene`, `Renderer` eller `Camera`.

## Arkitektur

| Klass                      | Ansvar                                                     |
|----------------------------|------------------------------------------------------------|
| `Vector3D`                 | 3D-vektormatematik (add, subtract, dot, cross, normalize)  |
| `Color`                    | RGB-färgvärden och färgblandning                           |
| `Ray`                      | En stråle: startpunkt + riktning                           |
| `Shape` (interface)        | Kontrakt: `Optional<HitRecord> hit(Ray ray)`               |
| `Sphere`, `Triangle`       | Konkreta former som implementerar `Shape`                  |
| `HitRecord`                | Träffinformation: avstånd (t), punkt, normal, material     |
| `Material` (interface)     | Kontrakt: `Color colorAt(HitRecord hit, PointLight light)` |
| `SolidColor`, `Lambertian` | Konkreta material                                          |
| `PointLight`               | Punktljuskälla: position, färg, intensitet                 |
| `Scene`                    | Håller `List<Shape>`, hittar närmaste träff för en stråle  |
| `Camera`                   | Mappar pixelkoordinater till strålar                       |
| `Renderer`                 | Kör render-loopen och skriver PNG-fil                      |

## Hur man lägger till en ny Shape

En ny 'Shape' kräver **ingen ändring** i `Scene`, `Renderer` eller `Camera` — bara en ny klass:

1. Skapa en klass som implementerar `Shape`:
   ```java
   public class EnNyShape implements Shape {
       @Override
       public Optional<HitRecord> hit(Ray ray) {
           // beräkna om/var strålen träffar formen
           // returnera Optional.empty() vid miss
           // returnera Optional.of(new HitRecord(t, point, normal, material)) vid träff
       }
   }
   ```
2. Lägg till som en instans i scenen (i Main):
   ```java
   scene.add(new EnNyShape(...));
   ```

`Scene.trace()` loopar polymorft över alla `Shape`-objekt och anropar `hit()` utan att veta konkret typ — det är det som
gör tillägget möjligt utan att röra befintlig kod.

## Köra projektet

Projektet kan köras i terminalen med:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```

Resultatet sparas som `output.png` i mappen 'renders'. Öppna filen med GIMP eller en bildvisare

## Köra tester

Enkla unit-tester har implementerats för `Sphere` och `Triangle`. För att köra:

```bash
mvn test
```

## Material & Belysning

Implementerat via `Material`-interfacet med två konkreta klasser:

- **`SolidColor`** — returnerar en fast färg oavsett ljusförhållanden.
- **`Lambertian`** — beräknar diffus belysning enligt Lamberts lag:
  den belysta ytans reflekterade ljusstyrka är proportionell mot `max(0, dot(normal, riktning mot ljuskälla))`. En yta
  som pekar rakt mot ljuskällan blir ljusast, en yta i 90° vinkel eller mer blir mörk.

`HitRecord` bär ett `Material`-objekt istället för en fast färg, vilket gör att nya material (t.ex. reflektion eller
genomskinlighet) kan läggas till senare utan att ändra `Sphere`,
`Triangle` eller render-loopen.