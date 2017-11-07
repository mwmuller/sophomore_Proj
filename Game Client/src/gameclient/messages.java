/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameclient;

import java.io.Serializable;

/**
 *
 * @author mike
 */
public class messages implements Serializable {

    private static char type;
    private static String message;

    public messages(char type, String message) {
        this.type = type;
        this.message = message;
    }

    public String get_message() {
        return message;
    }

    public char get_type() {
        return type;
    }

}
