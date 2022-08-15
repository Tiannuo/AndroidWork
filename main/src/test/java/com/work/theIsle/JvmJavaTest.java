package com.work.theIsle;

import java.io.IOException;
import java.util.Map;

/**
 * @Author TIKOU
 * @Date 2022/8/15-12:57
 * @Email 1320917731@qq.com & wangweitikou1994@gmail.com
 * @Description
 *
 */
public class JvmJavaTest {
    public void main() {
        Hero.testName();
        SpellBook spellBook = new SpellBook();
        //spellBook.getSpells();
        Map<String, Integer> spells = spellBook.spells;
        spellBook.spellUp("JavaA");

        try {
            extendShip();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            spellBook.accept();
        }catch (IOException e){

        }

    }

    public void extendShip() throws IOException{
        throw new IOException();
    }
}
