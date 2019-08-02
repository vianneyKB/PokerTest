/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer;

import com.google.gson.Gson;
import java.util.*;
/**
 *
 * @author MISHIKO
 */
// class to help convert values to Json format
public class Convert 
{
    private String item;
    private ArrayList<String> itemList;
    //constracter that tak String arraylist
    public Convert(String item) 
    {
        this.item = item;
    }
    public Convert(ArrayList<String> itemList) 
    {
        this.itemList = itemList;
    }
    //converting a String or String ArrayList to Json format
    public String ConvertToJSON ()
    {
       Gson gs = new Gson();
       String str;
       if (item != null) 
       {
           str = gs.toJson(item);
       }
       else
       {
         str = gs.toJson(itemList);
       }
        return str;
    }
}