/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;

/**
 *
 * @author Dina Ashraf
 */
public class ImagesUrl {

    private ArrayList<String> ImagesUrl = new ArrayList<String>();

    /**
     * @return the ImagesUrl
     */
    public boolean addItem(String url) {
        return ImagesUrl.add(url);
    }

    public ArrayList<String> getImagesUrl() {
        return ImagesUrl;
    }

    // updates
    //end of updates
}
