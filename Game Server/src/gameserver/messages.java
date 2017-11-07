/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameserver;

import java.io.Serializable;

/**
 *
 * @author mike
 */
public class messages implements Serializable {

    private static char type;
    // type can consist of 
    // c = chat; g = game; k = kick; u = username;
    private static String message;
    // message can consist of any string. Note for type k, emtpy string is sent

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
