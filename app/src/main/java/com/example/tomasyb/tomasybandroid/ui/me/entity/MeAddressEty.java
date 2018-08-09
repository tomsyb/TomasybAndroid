package com.example.tomasyb.tomasybandroid.ui.me.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人通讯录
 *
 * @author 严博
 * @version 1.0.0
 * @date 2018-8-9.15:46
 * @since JDK 1.8
 */

public class MeAddressEty {
    private String index;
    private String name;

    public MeAddressEty(String index, String name) {
        this.index = index;
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
    public static List<MeAddressEty> getEnglishContacts() {
        List<MeAddressEty> contacts = new ArrayList<>();
        contacts.add(new MeAddressEty("A", "Abbey"));
        contacts.add(new MeAddressEty("A", "Alex"));
        contacts.add(new MeAddressEty("A", "Amy"));
        contacts.add(new MeAddressEty("A", "Anne"));
        contacts.add(new MeAddressEty("B", "Betty"));
        contacts.add(new MeAddressEty("B", "Bob"));
        contacts.add(new MeAddressEty("B", "Brian"));
        contacts.add(new MeAddressEty("C", "Carl"));
        contacts.add(new MeAddressEty("C", "Candy"));
        contacts.add(new MeAddressEty("C", "Carlos"));
        contacts.add(new MeAddressEty("C", "Charles"));
        contacts.add(new MeAddressEty("C", "Christina"));
        contacts.add(new MeAddressEty("D", "David"));
        contacts.add(new MeAddressEty("D", "Daniel"));
        contacts.add(new MeAddressEty("E", "Elizabeth"));
        contacts.add(new MeAddressEty("E", "Eric"));
        contacts.add(new MeAddressEty("E", "Eva"));
        contacts.add(new MeAddressEty("F", "Frances"));
        contacts.add(new MeAddressEty("F", "Frank"));
        contacts.add(new MeAddressEty("I", "Ivy"));
        contacts.add(new MeAddressEty("J", "James"));
        contacts.add(new MeAddressEty("J", "John"));
        contacts.add(new MeAddressEty("J", "Jessica"));
        contacts.add(new MeAddressEty("K", "Karen"));
        contacts.add(new MeAddressEty("K", "Karl"));
        contacts.add(new MeAddressEty("K", "Kim"));
        contacts.add(new MeAddressEty("L", "Leon"));
        contacts.add(new MeAddressEty("L", "Lisa"));
        contacts.add(new MeAddressEty("P", "Paul"));
        contacts.add(new MeAddressEty("P", "Peter"));
        contacts.add(new MeAddressEty("S", "Sarah"));
        contacts.add(new MeAddressEty("S", "Steven"));
        contacts.add(new MeAddressEty("R", "Robert"));
        contacts.add(new MeAddressEty("R", "Ryan"));
        contacts.add(new MeAddressEty("T", "Tom"));
        contacts.add(new MeAddressEty("T", "Tony"));
        contacts.add(new MeAddressEty("W", "Wendy"));
        contacts.add(new MeAddressEty("W", "Will"));
        contacts.add(new MeAddressEty("W", "William"));
        contacts.add(new MeAddressEty("Z", "Zoe"));
        return contacts;
    }
}
