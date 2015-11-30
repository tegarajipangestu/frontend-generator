/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package driver;

import engine.GeneratorEngine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tegarnization
 */
public class GeneratorDriver {
    public static void main(String[] args) {
        try {
             System.out.println("Working Directory = " +
              System.getProperty("user.dir"));
            GeneratorEngine generatorEngine = new GeneratorEngine("input.dsl");
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
