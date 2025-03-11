import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demonstrates streaming operations and NIO.
 *
 * @author cole campbell
 * @author kyle cheon
 * @version 1.0
 */
public class CountryLab
{
    public static void main(final String[] args)
    {
        final Path inputFile;
        inputFile = Paths.get("src", "resources", "week8countries.txt");
        final Path matchesDir;
        matchesDir = Paths.get("src", "resource", "matches");
        final Path dataFile;
        dataFile = matchesDir.resolve("data.txt");

        try
        {
            if(!Files.exists(matchesDir))
            {
                Files.createDirectory(matchesDir);
            }

            final List<String> countries;
            countries = Files.readAllLines(inputFile);

            final List<String> longNames;
            longNames = countries.stream()
                                    .filter(c -> c.length() > 10)
                                    .toList();

            final List<String> shortNames;
            shortNames = countries.stream()
                                    .filter(c -> c.length() < 5)
                                    .toList();

            final List<String> startsWithA;
            startsWithA = countries.stream()
                                    .filter(c -> c.startsWith("A"))
                                    .toList();

            final List<String> endsWithLand;
            endsWithLand = countries.stream()
                                        .filter(c -> c.endsWith("land"))
                                        .toList();

            final List<String> containsUnited;
            containsUnited = countries.stream()
                                        .filter(c -> c.contains("United"))
                                        .toList();

            final List<String> sortedAscending;
            sortedAscending = countries.stream()
                                        .sorted()
                                        .toList();

            final List<String> sortedDescending;
            sortedDescending = countries.stream()
                                        .sorted(Comparator.reverseOrder())
                                        .toList();

            final Set<Character> uniqueFirstLetters;
            uniqueFirstLetters = countries.stream()
                                            .map(c -> c.charAt(0))
                                            .collect(Collectors.toSet());

            final long countCountries;
            countCountries = countries.size();

            final String longestCountry;
            longestCountry = countries.stream()
                                        .max(Comparator.comparingInt(String::length))
                                        .orElse("None");

            final String shortestCountry;
            shortestCountry = countries.stream()
                                        .min(Comparator.comparingInt(String::length))
                                        .orElse("None");

            final List<String> uppercaseCountries;
            uppercaseCountries = countries.stream()
                                            .map(String::toUpperCase)
                                            .toList();

            final List<String> multipleWordCountries;
            multipleWordCountries = countries.stream()
                                                .filter(c -> c.contains(" "))
                                                .toList();

            final List<String> nameToCharCount;
            nameToCharCount = countries.stream()
                                        .map(c -> c + ": " + c.length() + " characters")
                                        .toList();

            final boolean anyStartsWithZ;
            anyStartsWithZ = countries.stream()
                                        .anyMatch(c -> c.startsWith("Z"));

            final boolean allLongerThanThree;
            allLongerThanThree = countries.stream()
                                            .allMatch(c -> c.length() > 3);

            final String output;

            output = "Country names longer than 10 characters:\n" + String.join("\n", longNames) + "\n\n" +
                     "Country names shorter than 5 characters:\n" + String.join("\n", shortNames) + "\n\n" +
                     "Country names starting with 'A':\n" + String.join("\n", startsWithA) + "\n\n" +
                     "Country names ending with 'land':\n" + String.join("\n", endsWithLand) + "\n\n" +
                     "Country names containing 'United':\n" + String.join("\n", containsUnited) + "\n\n" +
                     "Sorted country names (Ascending):\n" + String.join("\n", sortedAscending) + "\n\n" +
                     "Sorted country names (Descending):\n" + String.join("\n", sortedDescending) + "\n\n" +
                     "Unique first letters:\n" + uniqueFirstLetters + "\n\n" +
                     "Total count of countries: " + countCountries + "\n\n" +
                     "Longest country name: " + longestCountry + "\n\n" +
                     "Shortest country name: " + shortestCountry + "\n\n" +
                     "Countries in uppercase:\n" + String.join("\n", uppercaseCountries) + "\n\n" +
                     "Countries with more than one word:\n" + String.join("\n", multipleWordCountries) + "\n\n" +
                     "Country names mapped to character count:\n" + String.join("\n", nameToCharCount) + "\n\n" +
                     "Any country name starts with 'Z': " + anyStartsWithZ + "\n\n" +
                     "All country names longer than 3 characters: " + allLongerThanThree + "\n\n";

            Files.write(dataFile, output.getBytes());

        }
        catch(final IOException e)
        {
            e.printStackTrace();
        }
    }
}

