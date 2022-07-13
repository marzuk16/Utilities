import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TakaToWord {
    private final String[] digits = {"zero ", "One ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ",
            "Twelve ", "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ", "Eighteen ", "Nineteen "};
    private final String[] multiply10 = {"", "ten", "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};
 
    private String convertUpToThousand(int number) {
        String soFar;
        if (number % 100 < 20) {
            soFar = digits[number % 100];
            number = number / 100;
        } else {
            soFar = digits[number % 10];
            number = number / 10;
            soFar = multiply10[number % 10] + soFar;
            number = number / 10;
        }
        if (number == 0) return soFar;
        return digits[number] + " Hundred " + soFar;
    }
 
    public String convertToWord(String taka) {
        int takaLength = taka.length();
        int cror = 0, lakh = 0, thousands = 0, belowThousands = 0;
 
        switch (takaLength % 8) {
            case 0:
                cror = Integer.parseInt(taka.substring(0, 1));
                lakh = Integer.parseInt(taka.substring(1, 3));
                thousands = Integer.parseInt(taka.substring(3, 5));
                belowThousands = Integer.parseInt(taka.substring(5));
                break;
            case 1:
            case 2:
            case 3:
                belowThousands = Integer.parseInt(taka.substring(0));
                break;
            case 4:
            case 5:
                thousands = Integer.parseInt(taka);
                break;
            case 6:
                lakh = Integer.parseInt(taka.substring(0, 1));
                thousands = Integer.parseInt(taka.substring(1, 3));
                belowThousands = Integer.parseInt(taka.substring(3));
                break;
            case 7:
                lakh = Integer.parseInt(taka.substring(0, 2));
                thousands = Integer.parseInt(taka.substring(2, 4));
                belowThousands = Integer.parseInt(taka.substring(4));
                break;
        }
 
        String croresText = "";
        switch (cror) {
            case 0:
                croresText = "";
                break;
            case 1:
                croresText = "One crore ";
                break;
            default:
                croresText = convertUpToThousand(cror) + " crore ";
        }
 
        String lakhsText = "";
        switch (lakh) {
            case 0:
                lakhsText = "";
                break;
            case 1:
                lakhsText = "One lakh ";
                break;
            default:
                lakhsText = convertUpToThousand(lakh) + " lakh ";
        }
 
        String thousandsText = "";
        switch (thousands) {
            case 0:
                thousandsText = "";
                break;
            case 1:
                thousandsText = "One Thousand ";
                break;
            default:
                thousandsText = convertUpToThousand(thousands) + " Thousand ";
        }
 
        String belowThoussandText = convertUpToThousand(belowThousands);
 
        return croresText + lakhsText + thousandsText + belowThoussandText;
    }
 
    public String processTaka(String taka) {
        int length = taka.length();
        String result = "";
        for (int i = 1; i <= (length / 8) + 1; i++) {
            length = taka.length();
            if (length % 8 == 0) {
                result += convertToWord(taka.substring(0, 8));
                taka = taka.substring(8);
            } else {
                result += convertToWord(taka.substring(0, (length % 8) ));
                taka = taka.substring(length % 8);
                if( (length/8) + 1 > 1) result += " crore ";
            }
        }
        return result;
    }

    private List<String> sanitizeTaka(String taka){
        String sanitizePattern = "[\\s]+";
        String splitPattern = "[^0-9]+";
        return Arrays.asList(taka.replaceAll(sanitizePattern, "").split(splitPattern));
    }
 
    public String convert(String taka) {
        List<String> splittedTaka = this.sanitizeTaka(taka);

        if (splittedTaka.size() > 2 || splittedTaka.size() == 0) return "Invalid number";

        String result = this.processTaka(splittedTaka.get(0)) + " taka ";
        if(splittedTaka.size() == 2) result += this.processTaka(splittedTaka.get(1)) + " poisa";

        return result;
    }
}