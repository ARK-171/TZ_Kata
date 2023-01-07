import java.io.IOException;
import java.io.StringBufferInputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String[] rim = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "M"};
    public static int[] arb = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

    public static void main(String[] args) throws IOException {

        int x=0;
        int y=0;
        int c=0;
        while(true){
            Scanner scan = new Scanner(System.in);
            String a = scan.nextLine();
            try{
                for (int i = 0; i<a.length();i++) {
                    if (String.valueOf(a.charAt(i)).equals("+")) {
                        String[] s1 = a.split("\\+", 2);
                        x = Integer.parseInt(s1[0]);
                        y = Integer.parseInt(s1[1]);
                        if (x > 0 & x < 11 & y > 0 & y < 11) {
                            System.out.println(x + y);
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    }else if(String.valueOf(a.charAt(i)).equals("-")) {
                        String[] s2 = a.split("-", 2);
                        x = Integer.parseInt(s2[0]);
                        y = Integer.parseInt(s2[1]);
                        if (x > 0 & x < 11 & y > 0 & y < 11) {
                            System.out.println(x - y);
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    }else if(String.valueOf(a.charAt(i)).equals("*")) {
                        String[] s3 = a.split("\\*", 2);
                        x = Integer.parseInt(s3[0]);
                        y = Integer.parseInt(s3[1]);
                        if (x > 0 & x < 11 & y > 0 & y < 11) {
                            System.out.println(x * y);
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    }else if(String.valueOf(a.charAt(i)).equals("/")){
                            String[] s4 = a.split("/",2);
                            x = Integer.parseInt(s4[0]);
                            y = Integer.parseInt(s4[1]);
                            if (x>0 & x<11 & y>0 & y<11){
                                System.out.println(x/y);
                            }else{
                                throw new IOException();
                            }
                            i = a.length();
                    }
                }
            }catch (NumberFormatException | IOException e){
                for (int i = 0; i<a.length();i++) {
                    if (String.valueOf(a.charAt(i)).equals("+")) {
                        String[] s1 = a.split("\\+", 2);
                        x = ConvertToArb(s1[0]);
                        y = ConvertToArb(s1[1]);
                        c = x + y;
                        if (x + y > 0 & x > 0 & y > 0 & x < 11 & y < 11) {
                            System.out.println(ConvertToRim(c));
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    }else if(String.valueOf(a.charAt(i)).equals("-")) {
                        String[] s2 = a.split("-", 2);
                        x = ConvertToArb(s2[0]);
                        y = ConvertToArb(s2[1]);
                        c = x - y;
                        if (x - y > 0 & x > 0 & y > 0 & x < 11 & y < 11) {
                            System.out.println(ConvertToRim(c));
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    }else if(String.valueOf(a.charAt(i)).equals("*")) {
                        String[] s3 = a.split("\\*", 2);
                        x = ConvertToArb(s3[0]);
                        y = ConvertToArb(s3[1]);
                        c = x * y;
                        if (x * y > 0 & x > 0 & y > 0 & x < 11 & y < 11) {
                            System.out.println(ConvertToRim(c));
                        } else {
                            throw new IOException();
                        }
                        i = a.length();
                    } else if (String.valueOf(a.charAt(i)).equals("/")) {
                        String[] s4 = a.split("/",2);
                        x = ConvertToArb(s4[0]);
                        y = ConvertToArb(s4[1]);
                        c = x/y;
                        if (x/y > 0 & x>0 & y>0 & x<11 & y<11){
                            System.out.println(ConvertToRim(c));
                        }else{
                            throw new IOException();
                        }
                        i = a.length();
                    }
                }
            }
        }
    }

    public static String ConvertToRim(int a){
        List<String> fin = new ArrayList<String>();
        StringBuilder s = new StringBuilder();
        int raz = 10;
        String str = String.valueOf(a);
        int ln = str.length();
        for(int i = str.length()-1; i>=0; i--){
            for (int l = 0; l < arb.length; l++){
                if (Integer.parseInt(str)%raz == arb[l]){
                    raz *= 10;
                    fin.add(rim[l]);
                }
            }
            str = str.substring(0,i);
            for(int len = str.length(); len < ln; len++) {
                str = str + "0";
            }
        }
        for(int i = fin.size()-1; i >= 0; i--){
            s.append(fin.get(i));
        }
        return s.toString();
    }

    public static int ConvertToArb(String a){
        int x = 0;
        int c = 0;
        int k = 0 ;
        String str = a;
        for(int i = 0; i< str.length(); i++){
            if(i<str.length()-1){
                for(int l = 0; l< rim.length; l++){
                    if(String.valueOf(str.charAt(i)).equals(rim[l])){
                        c = arb[l];
                    }
                    if(String.valueOf(str.charAt(i + 1)).equals(rim[l])){
                        k = arb[l];
                    }
                }
                if (k <= c) {
                    x += c;
                }else if(k > c){
                    x += -c;
                }
            }else{
                for(int l = 0; l< rim.length; l++){
                    if(String.valueOf(str.charAt(i)).equals(rim[l])){
                        k = arb[l];
                    }
                }
                x += k;
            }
        }
        return x;
    }

}