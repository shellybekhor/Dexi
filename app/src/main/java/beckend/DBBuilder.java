package beckend;

import java.util.HashMap;
import java.util.Map;

/**
 * this class build the database of the vowels (nikkud) and consonants (itsurim)
 */
public class DBBuilder {
    private static Map<String, String> vowels;
    private static Map<String, String> consonant;
    //vowels that doesn't need a letter to concat to
    private static Map<String, String> standaloneVowels;
    //end of word letters
    private static Map<String, String> endConsonant;
    //letters that have a end of word version
    private static Map<String, String> hasEndConsonant;

    private static Map<String, String> buildConsonantMap() {
        Map<String, String> map = new HashMap<>();

        map.put("ALEF", "א");
        map.put("VET", "ב");
        map.put("BET", "בּ");
        map.put("GIMEL", "ג");
        map.put("DALET", "ד");
        map.put("HEY", "ה");
        map.put("VAV", "ו");
        map.put("ZAIN", "ז");
        map.put("HET", "ח");
        map.put("TET", "ט");
        map.put("YUD", "י");
        map.put("CHAF_SOFIT", "ך");
        map.put("CHAF", "כ");
        map.put("KAF", "כּ");
        map.put("LAMED", "ל");
        map.put("MEM_SOFIT", "ם");
        map.put("MEM", "מ");
        map.put("NUN_SOFIT", "ן");
        map.put("NUN", "נ");
        map.put("SAMECH", "ס");
        map.put("AIN", "ע");
        map.put("FEY_SOFIT", "ף");
        map.put("FEY", "פ");
        map.put("PEY", "פּ");
        map.put("TSADI_SOFIT", "ץ");
        map.put("TSADI", "צ");
        map.put("KUF", "ק");
        map.put("REISH", "ר");
        map.put("SHIN", "שׁ");
        map.put("SIN", "שׂ");
        map.put("TAF", "ת");
        return map;
    }

    private static Map<String, String> buildEndConsonantMap() {
        Map<String, String> map = new HashMap<>();

        map.put("CHAF_SOFIT", "ך");
        map.put("MEM_SOFIT", "ם");
        map.put("NUN_SOFIT", "ן");
        map.put("FEY_SOFIT", "ף");
        map.put("TSADI_SOFIT", "ץ");
        return map;
    }

    private static Map<String, String> buildHasEndConsonantMap() {
        Map<String, String> map = new HashMap<>();

        map.put("CHAF", "ך");
        map.put("KAF","ך");
        map.put("MEM", "ם");
        map.put("NUN", "ן");
        map.put("FEY", "ף");
        map.put("PEY", "ף");
        map.put("TSADI", "ץ");
        return map;
    }

    private static Map<String, String> buildVowelsMap() {
        Map<String, String> map = new HashMap<>();

        //map.put("SHVA", "ְ");
        map.put("HIRIQ", "ִ");
        map.put("HIRIQ_MALE", "ִי");
        map.put("TSERE", "ֵ");
        map.put("SEGOL", "ֶ");
        map.put("PATAH", "ַ");
        map.put("QAMATS", "ָ");
        map.put("HOLAM", "ֹ");
        map.put("HOLAM_HASER_FOR_VAV", "וֺ");
        map.put("QUBUTS", "ֻ");
        map.put("SHURUQ", "וּ");
        return map;
    }

    /**
     * building a stand alone vowels map
     * @return
     */
    private static Map<String, String> buildStandaloneVowelsMap() {
        Map<String, String> map = new HashMap<>();

        map.put("HOLAM_HASER_FOR_VAV", "וֺ");
        map.put("SHURUQ", "וּ");
        return map;
    }

    public static Map<String, String> getVowels() {
        if (vowels == null)
            vowels = buildVowelsMap();

        return vowels;
    }

    public static Map<String, String> getStandaloneVowels() {
        if (standaloneVowels == null)
            standaloneVowels = buildStandaloneVowelsMap();

        return standaloneVowels;
    }

    public static Map<String, String> getConsonant() {
        if (consonant == null)
            consonant = buildConsonantMap();
        return consonant;
    }

    public static Map<String, String> getEndConsonant() {
        if (endConsonant == null)
            endConsonant = buildEndConsonantMap();
        return endConsonant;
    }

    public static Map<String, String> getHasEndConsonant() {
        if (hasEndConsonant == null)
            hasEndConsonant = buildHasEndConsonantMap();
        return hasEndConsonant;
    }
}
