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

    public DSLReader() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.dsl"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);
        } finally {
            br.close();
        }
    }

    public String readProperty(String input, String keyword) throws Exception {
        String pattern = keyword + " \"(.*)\"";
        Pattern regex = Pattern.compile(pattern);

        Matcher matcher = regex.matcher(pattern);

        if (matcher.find()) {
            return matcher.group(0);
        } else {
            throw new Exception("Not complete argument exception");
        }
    }

}
