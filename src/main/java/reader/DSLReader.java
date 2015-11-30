/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tegarnization
 */
public class DSLReader {

    String input;

    public DSLReader(String inputFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            input = new String(everything);
//            System.out.println("input = "+input);

        } finally {
            br.close();
        }
    }

    public String readProperty(String keyword) throws Exception {
        String pattern = keyword + " (?:\"(.*)\"|(\\d))";
        Pattern regex = Pattern.compile(pattern);

//        System.out.println("input ="+input);
//        System.out.println("pattern = "+pattern);
        Matcher matcher = regex.matcher(input);

        if (matcher.find()) {
            if (matcher.group(1) == null) {
                return matcher.group(2);
            } else {
                return matcher.group(1);
            }
        } else {
            throw new Exception("Not complete argument exception or syntax error");
        }
    }

}
