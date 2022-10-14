package number_format;

public class NumberFormat {
    public static String commaSeperate(String number){
        StringBuilder formattedNumber = new StringBuilder();
        int count = 0;
        for(int i=number.length()-1; i>=0; i--){
            formattedNumber.append(number.charAt(i));
            if(++count == 3){
                formattedNumber.append(",");
                count = 0;
            }
        }

        formattedNumber = formattedNumber.reverse();
        return formattedNumber.charAt(0) == ',' ? formattedNumber.substring(1): formattedNumber.toString();
    }

    public static String commaSeperate(int number, boolean bangla){
        String banglaNumbers = "০১২৩৪৫৬৭৮৯";

        int count = 0;
        String formattedNumber = "";

        while (number > 0) {
            char ch = bangla ? banglaNumbers.charAt(number%10) : (char)((number % 10)+'0');
            formattedNumber = ch + formattedNumber;
            number /= 10;
            if(++count == 3){
                count = 0;
                formattedNumber = ',' + formattedNumber;
            } 
        }
        return formattedNumber.charAt(0) == ',' ? formattedNumber.substring(1) : formattedNumber;
    }
}
