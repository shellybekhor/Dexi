package beckend;

import java.util.HashMap;
import java.util.Map;

public class DBBuilder {
    private static Map<String,String> vowels;
    private static Map<String,String> consonant;
    private static Map<String, String> buildVowelsMap() {
        HashMap<String, String> map = new HashMap<>();

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
        map.put("EMPTY", "⬚");
        return map;
    }
    private static Map<String, String> buildConsonantMap() {return null;}
    public static Map<String, String> getVowels(){return null;}
    public static Map<String, String> getConsonant(){return null;}
}
